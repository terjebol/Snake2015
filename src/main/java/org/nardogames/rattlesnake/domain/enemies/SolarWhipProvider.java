package org.nardogames.rattlesnake.domain.enemies;

import org.nardogames.rattlesnake.domain.IAmEntity;
import org.nardogames.rattlesnake.domain.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SolarWhipProvider implements IProvideEnemies {
    private Random randomizer;
    private List<SolarWhip> solarWhips;

    public SolarWhipProvider() {
        randomizer = new Random();
        solarWhips = new ArrayList<>();
    }

    @Override
    public IAmEntity createEntity() {
        return SolarWhip.create();
    }

    @Override
    public boolean isInactive() {
        return false;
    }

    @Override
    public boolean shouldProvideMore() {
        return randomizer.nextFloat() > 0.998f && getCurrentEntities().size() < 2;
    }

    @Override
    public List<? extends IAmEntity> getCurrentEntities() {
        return solarWhips;
    }

    @Override
    public void update(float deltaTime, Player player) {
        if (shouldProvideMore()) {
            solarWhips.add((SolarWhip) createEntity());
        }

    }

    @Override
    public void dispose() {

    }


}
