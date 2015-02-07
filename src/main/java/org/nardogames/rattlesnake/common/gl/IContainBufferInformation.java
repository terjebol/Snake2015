package org.nardogames.rattlesnake.common.gl;

public interface IContainBufferInformation {

    /**
     * How many components to decide a vertex position? Typically (x,y) for 2D, (x,y,z) for 3D.
     * @return number of components.
     */
    public int getVertexSize();

    /**
     * How many components composes a color.
     * @return number of components.
     */
    public int getColorSize();

    public int getTextureCoordsSize();

    /**
     * How many vertices makes one quad (two triangles)?
     * @return
     */
    public int getVerticeCountForQuad();

    /**
     * Maximum entities the buffer can contain.
     * @return
     */
    public int getMaximumEntities();

    /**
     * OpenGL draw-mode. Eg: GL_TRIANGLES, GL_LINES
     * @return
     */
    public int getDrawMode();

}
