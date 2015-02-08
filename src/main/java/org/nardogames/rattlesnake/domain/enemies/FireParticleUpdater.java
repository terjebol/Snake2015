package org.nardogames.rattlesnake.domain.enemies;

import org.fastmath.easing.IEasingMethod;
import org.nardogames.rattlesnake.common.particles.DefaultParticleUpdater;

/**
 * Created by Terje on 07.02.2015.
 */
public class FireParticleUpdater extends DefaultParticleUpdater {
    public FireParticleUpdater(IEasingMethod easingMethod) {
        super(easingMethod);
    }

    @Override
    public float[] getColor(float factorOfTtl) {
        float factor = getEasingMethod().execute(factorOfTtl, 0f, 1f, 1f);
        return new float[]{
                1f, Math.max(0.5f - factor, 0f), 0f //0.5f - (0.5f * factor)
        };
    }
}

