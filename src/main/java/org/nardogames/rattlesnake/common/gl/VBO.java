package org.nardogames.rattlesnake.common.gl;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.nardogames.rattlesnake.common.util.Vertex2f;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.nardogames.rattlesnake.common.util.DataTypes.BYTES_PER_FLOAT;

public class VBO {

    private float[] vertices;
    private int vertexIndex;
    private float[] colors;
    private int colorIndex;
    private float[] textureCoords;
    private int textureIndex;
    private FloatBuffer buffer;
    private int bufferId;
    private FloatBuffer textureBuffer;
    private int textureBufferId;
    private boolean textureBufferBuffered;
    private int vertexSize;
    private int colorSize;
    private int textureSize;
    private int maxNumEntities;
    private int verticesPrEntity;
    private int drawMode;
    private boolean hasUpdatedData;

    public VBO(int numEntities) {
        this(new BufferInformation(numEntities));
    }

    public VBO(IContainBufferInformation bufferInformation) {
        vertexSize = bufferInformation.getVertexSize();
        colorSize = bufferInformation.getColorSize();
        textureSize = bufferInformation.getTextureCoordsSize();

        maxNumEntities = bufferInformation.getMaximumEntities();
        verticesPrEntity = bufferInformation.getVerticeCountForQuad();
        drawMode = bufferInformation.getDrawMode();

        vertices = new float[verticesPrEntity * vertexSize * maxNumEntities];
        colors = new float[verticesPrEntity * colorSize * maxNumEntities];
        textureCoords = new float[verticesPrEntity * textureSize * maxNumEntities];
        buffer = BufferUtils.createFloatBuffer(vertices.length + colors.length);
        textureBuffer = BufferUtils.createFloatBuffer(textureCoords.length);
        // create our vertex buffer objects
        IntBuffer idbuffer = BufferUtils.createIntBuffer(2);
        GL15.glGenBuffers(idbuffer);
        bufferId = idbuffer.get(0);
        textureBufferId = idbuffer.get(1);
    }

    public void putVertices(Vertex2f... vertices) {
        for (Vertex2f vertex : vertices) {
            putVertex(vertex);
        }
    }

    public void putVertex(Vertex2f vertex) {
        putVertex(vertex.getValues());
    }

    public void putVertex(float[] vertex) {
        for (int i = 0; i < vertex.length; i++) {
            vertices[vertexIndex++] = vertex[i];
        }
    }

    public void putColor(float[] color) {
        for (int i = 0; i < color.length; i++) {
            colors[colorIndex++] = color[i];
        }
    }

    public void putTextureCoords(TextureCoord2f textureCoord2f) {
        float[] coord = textureCoord2f.getValues();
        for(int i = 0; i < coord.length; i++) {
            textureCoords[textureIndex++] = coord[i];
        }
    }

    public void putTextureCoord(float coord) {
        textureCoords[textureIndex++] = coord;
    }

    public float[] getVertexData() {
        float[] copy = new float[vertices.length];
        System.arraycopy(vertices, 0, copy, 0, copy.length);
        return copy;
    }

    public float[] getColorData() {
        float[] copy = new float[colors.length];
        System.arraycopy(colors, 0, copy, 0, copy.length);
        return copy;
    }

    public float[] getTextureData() {
        float[] copy = new float[textureCoords.length];
        System.arraycopy(textureCoords, 0, copy, 0, copy.length);
        return copy;
    }

    public void resetData() {
        vertexIndex = 0;
        colorIndex = 0;
        textureIndex = 0;
        buffer.clear();
        hasUpdatedData = true;
    }

    public void notifyDataUpdated() {
        hasUpdatedData = true;
    }

    private void interleaveBuffer() {
        int stride = colorSize + vertexSize;
        buffer.clear();
        addValuesToBuffer(vertices, vertexSize, stride, 0);
        addValuesToBuffer(colors, colorSize, stride, vertexSize);
        buffer.rewind();
    }

    private void addValuesToBuffer(float[] arr, int sz, int stride, int offset) {
        int valuesToAdd = arr.length / sz;
        int valuesAdded = 0;
        while (valuesAdded < valuesToAdd) {
            for (int i = 0; i < sz; i++) {
                buffer.put(valuesAdded * stride + i + offset, arr[valuesAdded * sz + i]);
            }
            valuesAdded++;
        }
    }

    public void render() {
        render(0f, 0f);
    }
    //public void render(float x, float y, float scale, float alpha)
    private void render(float x, float y) {

        GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
        if(!textureBufferBuffered) {
            textureBuffer.put(textureCoords);
            textureBuffer.rewind();
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, textureBufferId);
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, textureBuffer, GL15.GL_STATIC_DRAW);
            textureBufferBuffered = true;
        } else {
            // bind texture buffer, - does not need to buffer data since it is static
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, textureBufferId);
        }

        GL11.glTexCoordPointer(2, GL11.GL_FLOAT, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,0);


        // bind and buffer vertex data. Bind needs to be done, but bufferData
        // should optimally not be done here on every loop.
        // TODO: Find way to optimize. glMapBuffer? glBufferSubData?
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferId);
        if(hasUpdatedData) {
            interleaveBuffer();
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_DYNAMIC_DRAW);
            hasUpdatedData = false;
        }
        int stride = BYTES_PER_FLOAT * (vertexSize + colorSize);
        int numEntities = vertexIndex / vertexSize / verticesPrEntity;
        GL11.glVertexPointer(vertexSize, GL11.GL_FLOAT, stride, 0);
        GL11.glColorPointer(colorSize, GL11.GL_FLOAT, stride, BYTES_PER_FLOAT * vertexSize);
        GL11.glTranslatef(x, y, 0f);
        GL11.glDrawArrays(drawMode, 0, numEntities * verticesPrEntity);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);

    }
}
