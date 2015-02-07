package org.nardogames.rattlesnake.common.particles;

import org.lwjgl.opengl.GL11;
import org.nardogames.rattlesnake.common.gl.TextureCoord2f;
import org.nardogames.rattlesnake.common.gl.VBO;
import org.nardogames.rattlesnake.common.util.Vertex2f;
import org.nardogames.rattlesnake.common.util.VertexUtils;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Terje on 24.01.2015.
 */
public abstract class AbstractParticleEmitter {

    protected List<ParticleEmitterPosition> positionList;
    private List<Particle> particleList;
    private Texture particleTexture;
    private AbstractParticleCreator particleCreator;
    private AbstractParticleUpdater particleUpdater;
    private IBlendParticles particleBlender;
    private int maximumParticles;
    private boolean active;
    private static float[] colorPlaceholder = new float[4];
    private VBO buffer;

    public AbstractParticleEmitter(Texture particleTexture, AbstractParticleCreator particlePlacer, AbstractParticleUpdater particleUpdater, IBlendParticles particleBlender, int maximumParticles) {
        this.maximumParticles = maximumParticles;
        this.particleCreator = particlePlacer;
        this.particleUpdater = particleUpdater;
        this.particleBlender = particleBlender;
        this.particleTexture = particleTexture;
        particleList = new ArrayList<>();
        positionList = new ArrayList<>();
        active = true;
    }

    public List<Particle> getParticleList() {
        return particleList;
    }
    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }

    public void activate() {
        active = true;
    }

    public List<ParticleEmitterPosition> getPositions() {
        return positionList;
    }

    public int getMaximumParticles() {
        return maximumParticles;
    }

    public boolean hasRoomForMoreParticles() {
        return particleList.size() < maximumParticles;
    }

    public void addParticle() {
        particleList.add(particleCreator.createParticle());
    }

    public void initializeParticles(int initialNumParticles) {
        int particlesToInitiallyCreate = Math.min(initialNumParticles, maximumParticles);
        for (int i = 0; i < particlesToInitiallyCreate; i++) {
            addParticle();
        }
        createParticleBuffer();
        createTextureBuffer();
    }

    private void createParticleBuffer() {
        buffer = new VBO(maximumParticles);
        updateBuffer();
    }

    private void updateBuffer() {
        buffer.resetData();
        for (Particle p : particleList) {
            Vertex2f[] vertices = VertexUtils.createDefaultQuad(p.x, p.y, p.width, p.height);
            for (int j = 0; j < vertices.length; j++) {
                buffer.putVertex(vertices[j]);
                colorPlaceholder[0] = p.red;
                colorPlaceholder[1] = p.green;
                colorPlaceholder[2] = p.blue;
                colorPlaceholder[3] = p.alpha;
                buffer.putColor(colorPlaceholder);
            }
        }
    }

    private void createTextureBuffer() {
        for (int i = 0; i < maximumParticles; i++) {
            buffer.putTextureCoords(new TextureCoord2f());
        }
    }

    public void update(float delta) {
        for (ParticleEmitterPosition position : positionList) {
            position.update(delta);
        }
        particleUpdater.updateParticles(particleList, delta);
        int maxParticlesToAdd = particleUpdater.getMaxParticlesAddedOnUpdate();
        if (particleCreator.doesRespawnParticles()) {
            for (int i = 0; i < maxParticlesToAdd; i++) {
                if (particleList.size() < maximumParticles) {
                    addParticle();
                }
            }
        }
        updateBuffer();
    }

    public void render() {
        particleBlender.applyBlendFunc();
        particleTexture.bind();
        onRenderBuffer(buffer);
    }

    protected abstract void onRenderBuffer(VBO buffer);

}
