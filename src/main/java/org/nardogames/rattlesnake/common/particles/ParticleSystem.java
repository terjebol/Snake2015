package org.nardogames.rattlesnake.common.particles;

import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

/**
 * En samling av ParticleEmitters. Håndterer felles innstillinger ifm GL,
 * og der Emitters håndterer spawning/fjerning av enkeltpartikler, håndterer
 * hele systemet livssyklusen til emitters.
 */
public class ParticleSystem {

    private static ParticleSystem _instance;
    public static ParticleSystem globalInstance() {
        if(_instance == null) {
            _instance = new ParticleSystem();
        }
        return _instance;
    }


    private List<AbstractParticleEmitter> emitters;


    private ParticleSystem() {
        emitters = new ArrayList<>();
    }

    public void addEmitter(AbstractParticleEmitter emitter) {
        emitters.add(emitter);
    }

    public void removeEmitter(AbstractParticleEmitter emitter) {
        emitters.remove(emitter);
    }

    public void update(float deltaTime) {

//        System.out.println("Emitters: "+emitters.size());
        for(int i = emitters.size()-1; i >= 0; i--) {
            AbstractParticleEmitter emitter = emitters.get(i);
            if(!emitter.isActive()) {
                emitters.remove(i);
            }
            else {
                emitter.update(deltaTime);
            }
        }
    }


    public void render() {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);

        for (AbstractParticleEmitter emitter : emitters) {
            emitter.render();
        }

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }
}
