package org.nardogames.rattlesnake.common.util;

public class VertexUtils {

    public static Vertex2f[] createDefaultQuad(float width, float height) {
        return createDefaultQuad(0f, 0f, width, height);
    }

    public static Vertex2f[] createDefaultQuad(float x, float y, float width, float height) {
        float w = width / 2;
        float h = height / 2;
        return new Vertex2f[]
                {
                        new Vertex2f(x - w, y + h),
                        new Vertex2f(x - w, y - h),
                        new Vertex2f(x + w, y - h),

                        new Vertex2f(x + w, y - h),
                        new Vertex2f(x + w, y + h),
                        new Vertex2f(x - w, y + h),
                };
    }

    public static void translate(Vertex2f[] vertices, float x, float y) {
        for(Vertex2f vertex : vertices) {
            vertex.translate(x, y);
        }
    }
}
