package org.nardogames.rattlesnake.domain.enemies;

import org.nardogames.rattlesnake.domain.IAmEntity;
import org.nardogames.rattlesnake.domain.RattleSnake;
import org.nardogames.rattlesnake.domain.player.Player;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CometProvider implements IProvideEnemies {
    private Random randomizer;
    private List<Comet> comets;

    public CometProvider() {
        randomizer = new Random();
        comets = new ArrayList<>();
    }

    @Override
    public IAmEntity createEntity() {
        return Comet.create(createRandomizedVector());
    }

    @Override
    public boolean isInactive() {
        return false;
    }

    @Override
    public boolean shouldProvideMore() {
        return getCurrentEntities().size() < 5;
    }

    @Override
    public List<? extends IAmEntity> getCurrentEntities() {
        return comets;
    }

    @Override
    public void update(float deltaTime, Player player) {
        if (shouldProvideMore()) {
            comets.add((Comet) createEntity());
        }
        handleCollisions(player);
    }

    private void handleCollisions(Player player) {
        for(int i = comets.size()-1; i >= 0; i--) {
            Comet comet = comets.get(i);
            if(comet.collidesWithSnake(player)) {
                comet.notifyCollidedWithSnake();
                if(comet.isRemovedAfterCollision()) {
                    comets.remove(i);
                }
            }

            if (isOutsideBounds(comet)) {
                comets.remove(comet);
                comet.dispose();
            }
        }
    }

    // TODO: This is a naive check - FIX
    private boolean isOutsideBounds(IAmEntity comet) {
        if (comet.getX() < -200f) return true;
        if (comet.getX() > RattleSnake.getInstance().getDisplayWidth() + 200) return true;
        if (comet.getY() < -200f) return true;
        if (comet.getY() > RattleSnake.getInstance().getDisplayHeight() + 200) return true;
        return false;

    }

    @Override
    public void dispose() {

    }

    private Vector2f createRandomizedVector() {
        return new Vector2f(
                2f * (-0.5f + randomizer.nextFloat()),
                2f * (-0.5f + randomizer.nextFloat())).normalise().scale(0.25f);
    }


}
