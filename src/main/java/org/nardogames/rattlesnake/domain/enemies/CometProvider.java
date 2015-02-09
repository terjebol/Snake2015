package org.nardogames.rattlesnake.domain.enemies;

import org.nardogames.rattlesnake.domain.RattleSnake;
import org.nardogames.rattlesnake.domain.Snake;
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
    public IAmEnemy createEntity() {
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
    public List<? extends IAmEnemy> getCurrentEntities() {
        return comets;
    }

    @Override
    public void update(float deltaTime, Snake snake) {
        if (shouldProvideMore()) {
            comets.add((Comet) createEntity());
        }
        for (int i = comets.size() - 1; i >= 0; i--) {
            Comet comet = comets.get(i);

            if (comet.collidesWithSnake(snake)) {
                comet.notifyHitSnake();
            }

            if (isOutsideBounds(comet)) {
                comets.remove(comet);
                comet.dispose();
            }
        }
}

    // TODO: This is a naive check - FIX
    private boolean isOutsideBounds(IAmEnemy comet) {
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
