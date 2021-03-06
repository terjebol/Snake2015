package org.nardogames.rattlesnake.domain.enemies;


import org.nardogames.fastmath.easing.Linear;
import org.nardogames.rattlesnake.common.particles.DefaultParticleSet;
import org.nardogames.rattlesnake.common.particles.ParticleEmitterPosition;
import org.nardogames.rattlesnake.common.particles.ParticleSystem;
import org.nardogames.rattlesnake.common.particles.SinglePositionParticleEmitter;
import org.nardogames.rattlesnake.common.util.Collision;
import org.nardogames.rattlesnake.common.util.TextureUtils;
import org.nardogames.rattlesnake.domain.RattleSnake;
import org.nardogames.rattlesnake.domain.player.Player;
import org.newdawn.slick.geom.Vector2f;

public class Comet implements IAmEnemy {
    private SinglePositionParticleEmitter particleEmitter;
    private ParticleEmitterPosition position;

    protected static Comet create(Vector2f vector) {
        Vector2f positionVector = createRandomizedPosition(vector);

        Comet comet = new Comet();
        comet.position = new ParticleEmitterPosition(positionVector.getX(), positionVector.getY(), vector);
        comet.initializeParticleEmitter();
        return comet;
    }

    private void initializeParticleEmitter() {

        particleEmitter = new SinglePositionParticleEmitter(
                TextureUtils.getTexture("textures/sphere.png"),
                new CompetParticleCreator(position),
                new FireParticleUpdater(Linear.easeIn),
                DefaultParticleSet.get().getBlender(), 200);

        particleEmitter.setPosition(position);
        particleEmitter.initializeParticles(0);
        ParticleSystem.globalInstance().addEmitter(particleEmitter);
    }

    private static Vector2f createRandomizedPosition(Vector2f velocity) {

        Vector2f normalisedCopy = velocity.copy().normalise();

        float w = RattleSnake.getInstance().getDisplayWidth();
        float h = RattleSnake.getInstance().getDisplayHeight();
        float posX = (0.5f*w) - w * normalisedCopy.getX();
        float posY = (0.5f*h) - h * normalisedCopy.getY();
        if(posX < 0f) posX = 0f;
        if(posX >= w) posX = w;
        if(posY < 0f) posY = 0f;
        if(posY >= h) posY = h;
        return new Vector2f(posX, posY);
    }

    @Override
    public boolean collidesWithSnake(Player player) {
        if(player.isInvulnerable()) {
            return false;
        }
        float x = player.getX();
        float y = player.getY();
        float snakeRadius = player.getSnakeRadius();
        return Collision.circleIntersectsCircle(x, y, snakeRadius, position.getX(), position.getY(), 16f);
    }

    @Override
    public void notifyCollidedWithSnake(Player player) {
        player.hitByEnemy(this);
    }

    @Override
    public boolean isRemovedAfterCollision() {
        return false;
    }

    @Override
    public float getX() {
        return position.getX();
    }

    @Override
    public float getY() {
        return position.getY();
    }

    @Override
    public void dispose() {
        ParticleSystem.globalInstance().removeEmitter(particleEmitter);
    }

    @Override
    public float getDamage() {
        return 5f;
    }

    private static class CompetParticleCreator extends DefaultParticleSet.DefaultParticleCreator {
        private ParticleEmitterPosition position;

        public CompetParticleCreator(ParticleEmitterPosition position) {

            this.position = position;
        }

        @Override
        public float getInitialXPosition() {
            return position.getX();
        }

        @Override
        public float getInitialYPosition() {
            return position.getY();
        }

        @Override
        public float getInitialVelocityX() {
            return position.getVelocity().negate().scale(0.25f).getX();
        }

        @Override
        public float getInitialVelocityY() {
            return position.getVelocity().negate().scale(0.25f).getY();
        }

        @Override
        public float getInitialParticleWidth() {
            return 32f;
        }

        @Override
        public float getInitialParticleHeight() {
            return 32f;
        }

        @Override
        public int getMinimumTimeToLive() {
            return 1500;
        }

        @Override
        public int getMaximumTimeToLive() {
            return 2000;
        }
    }
}
