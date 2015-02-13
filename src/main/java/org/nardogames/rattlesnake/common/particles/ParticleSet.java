package org.nardogames.rattlesnake.common.particles;

public class ParticleSet {

    private AbstractParticleCreator particleCreator;
    private AbstractParticleUpdater particleUpdater;
    private IBlendParticles particleBlender;

    public ParticleSet(AbstractParticleCreator particleCreator, AbstractParticleUpdater particleUpdater, IBlendParticles particleBlender) {
        this.particleCreator = particleCreator;
        this.particleUpdater = particleUpdater;
        this.particleBlender = particleBlender;
    }

    public AbstractParticleCreator getCreator() {
        return particleCreator;
    }

    public AbstractParticleUpdater getUpdater() {
        return particleUpdater;
    }

    public IBlendParticles getBlender() {
        return particleBlender;
    }
}
