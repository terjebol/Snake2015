package org.nardogames.rattlesnake.domain.food;

import org.nardogames.rattlesnake.common.particles.DefaultParticleSet;
import org.nardogames.rattlesnake.common.particles.FoodSet;
import org.nardogames.rattlesnake.common.particles.MultiPositionParticleEmitter;
import org.nardogames.rattlesnake.common.particles.ParticleSystem;
import org.nardogames.rattlesnake.common.util.TextureUtils;
import org.nardogames.rattlesnake.domain.RattleSnake;
import org.nardogames.rattlesnake.domain.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnergyCloudProvider implements IProvideFood {

    private static final float DEFAULT_ENERGY_CUBE_RADIUS = 32f;
    private static final float MAX_SIMULTANEOUS_FOOD = 4f;
    private MultiPositionParticleEmitter particleEmitter;
    private List<EnergyCloud> positions;
    private Random spawnRandomizer;
    private boolean positionsNeedUpdate;
    private float totalTimeLeftInSeconds;

    public EnergyCloudProvider() {
        spawnRandomizer = new Random();
        positions = new ArrayList<>();
        initializeParticleEmitter();
        positionsNeedUpdate = true;
    }

    public void activate() {
        totalTimeLeftInSeconds = 20f;
    }
    private void initializeParticleEmitter() {
        FoodSet foodSet = new FoodSet();
        particleEmitter = new MultiPositionParticleEmitter(
                TextureUtils.getTexture("textures/sphere.png"),
                new EnergyCubeParticleCreator(DEFAULT_ENERGY_CUBE_RADIUS * 2),
                foodSet.getUpdater(),
                foodSet.getBlender(), 75);
        particleEmitter.initializeParticles(10);
        ParticleSystem.globalInstance().addEmitter(particleEmitter);
        activate();
    }



    @Override
    public void update(float deltaTime, Player player) {
        updateTimeLeft(deltaTime);
        if(shouldProvideMore()) {
            positions.add( (EnergyCloud)createEntity() );
        }
        handleCollisions(player);
        if(positionsNeedUpdate) {
            updatePositions();
            positionsNeedUpdate = false;
        }
    }

    private void handleCollisions(Player player) {
        for(int i = positions.size()-1;i>=0; i--) {
            IAmFood food = positions.get(i);
            if(food.collidesWithSnake(player)) {
                food.notifyCollidedWithSnake(player);
                if(food.isRemovedAfterCollision()) {
                    positions.remove(i);
                    positionsNeedUpdate = true;
                }
            }
        }
    }

    private void updatePositions() {
        particleEmitter.clearPositions();
        for(EnergyCloud cloud : positions) {
            particleEmitter.addPosition(cloud);
        }
    }

    protected void updateTimeLeft(float deltaTime) {
        totalTimeLeftInSeconds -= (deltaTime / 1000f);
    }

    protected boolean hasTimeLeft() {
        return totalTimeLeftInSeconds > 0f;
    }

    @Override
    public boolean isInactive() {
        return !hasTimeLeft() && positions.isEmpty();
    }

    @Override
    public void dispose() {
        ParticleSystem.globalInstance().removeEmitter(particleEmitter);
    }

    @Override
    public boolean shouldProvideMore() {
        return hasTimeLeft() && positions.size() < MAX_SIMULTANEOUS_FOOD && spawnRandomizer.nextFloat() > 0.99f;
    }

    @Override
    public List<? extends IAmFood> getCurrentEntities() {
        return positions;
    }

    @Override
    public IAmFood createEntity() {
        float x = spawnRandomizer.nextFloat() * RattleSnake.getInstance().getDisplayWidth();
        float y = spawnRandomizer.nextFloat() * RattleSnake.getInstance().getDisplayHeight();
        EnergyCloud food =  new EnergyCloud(x, y, DEFAULT_ENERGY_CUBE_RADIUS * 0.75f);
        //positions.add(food);
        positionsNeedUpdate = true;
        return food;
    }

    private static class EnergyCubeParticleCreator extends DefaultParticleSet.DefaultParticleCreator {
        private float cubeSize;

        public EnergyCubeParticleCreator(float cubeSize) {
            this.cubeSize = cubeSize;
        }

        @Override
        public float getInitialParticleWidth() {
            return cubeSize;
        }

        @Override
        public float getInitialParticleHeight() {
            return cubeSize;
        }

        @Override
        public float getVariableStartingPositionRadius() {
            return 16f;
        }

        @Override
        public boolean hasVariableStartingPosition() {
            return true;
        }
    }
}
