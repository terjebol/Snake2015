package org.nardogames.rattlesnake.common.particles;

import org.nardogames.fastmath.easing.IEasingMethod;
import org.nardogames.fastmath.easing.Linear;

public class FoodSet extends ParticleSet {

    public FoodSet() {
        super(DefaultParticleSet.get().getCreator(), new FoodParticleUpdater(Linear.easeIn), DefaultParticleSet.get().getBlender());
    }

    public static class FoodParticleUpdater extends DefaultParticleSet.DefaultParticleUpdater {

        public FoodParticleUpdater(IEasingMethod easingMethod) {
            super(easingMethod);
        }

        @Override
        public float[] getColor(float factorOfTtl) {
            // bare en eksempelsvis fargeovergang ...
            return new float[]{
                    easingMethod.execute(factorOfTtl, 0f, 1f, 1f),
                    0.25f * easingMethod.execute(factorOfTtl, 0f, 1f, 1f),
                    1f - easingMethod.execute(factorOfTtl, 0f, 1f, 1f)
            };
        }

        @Override
        public float[] getSize(float factorOfTtl, float initialWidth, float initialHeight) {
            // linjær reduksjon i størrelse etterhvert som levetiden til partiklen går mot slutten.
            return new float[]{(1f - factorOfTtl) * initialWidth, (1f - factorOfTtl) * initialHeight};
        }
    }
}
