package org.nardogames.rattlesnake.common.gl;

import org.lwjgl.opengl.GL11;

public class BufferInformation implements IContainBufferInformation {

    private int maximumEntities;

    public BufferInformation(int maximumEntities) {
        this.maximumEntities = maximumEntities;
    }
    @Override
    public int getVertexSize() {
        return 2;
    }

    @Override
    public int getColorSize() {
        return 4;
    }

    @Override
    public int getTextureCoordsSize() {
        return 2;
    }

    @Override
    public int getVerticeCountForQuad() {
        return 6;
    }

    @Override
    public int getMaximumEntities() {
        return maximumEntities;
    }

    @Override
    public int getDrawMode() {
        return GL11.GL_TRIANGLES;
    }
}
