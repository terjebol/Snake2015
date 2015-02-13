package org.nardogames.rattlesnake.common.particles;

import java.util.List;

/**
 * Created by tebaol on 01.09.2014.
 */
public abstract class AbstractParticleUpdater {
    private static float[] colorPlaceholder = new float[3];
    private static float[] sizePlaceholder = new float[2];

    public void updateParticles(List<Particle> particleList, float delta) {
        for(int i = particleList.size()-1; i>= 0; i--) {
            Particle p = particleList.get(i);
            p.currentTtl += delta;

            // TODO: Veldig dårlig måte å håndtere resirkulering av partikler ... Å fjerne fra lista her kan potensielt bety rydding og ny sortering av lista mange ganger i en loop cycle.
            if(p.currentTtl >= p.originalTtl) {
                p.active = false;
                particleList.remove(i);
                continue;
            }
            float factorOfTtl = p.currentTtl / p.originalTtl;

            if(!hasConstantVelocity()) {
                p.x += getVelocityX(factorOfTtl, p.vx) * delta;
                p.y += getVelocityY(factorOfTtl, p.vy) * delta;
            }
            else {
                p.x += (p.vx * delta);
                p.y += (p.vy * delta);
            }
            p.alpha = getAlpha( factorOfTtl );

            if(!hasConstantColor()) {
                colorPlaceholder = getColor(factorOfTtl);
                p.red = colorPlaceholder[0];
                p.green = colorPlaceholder[1];
                p.blue = colorPlaceholder[2];
            }
            if(!hasConstantSize()) {
                sizePlaceholder = getSize(factorOfTtl, p.initialWidth, p.initialHeight);
                p.width = sizePlaceholder[0];
                p.height = sizePlaceholder[1];
            }
        }

    }

    public abstract int getMaxParticlesAddedOnUpdate();
    public abstract float getAlpha(float factorOfTtl);
    public abstract float[] getColor(float factorOfTtl);
    public abstract float[] getSize(float factorOfTtl, float initialWidth, float initialHeight);
    public abstract boolean hasConstantSize();
    public abstract boolean hasConstantColor();
    public abstract boolean hasConstantVelocity();
    public abstract float getVelocityX(float factorOfTtl, float initialVelocityX);
    public abstract float getVelocityY(float factorOfTtl, float initialVelocityY);
}
