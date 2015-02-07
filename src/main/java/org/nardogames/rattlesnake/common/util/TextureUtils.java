package org.nardogames.rattlesnake.common.util;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class TextureUtils {
    private static Map<String, Texture> textures;

    static {
        textures = new HashMap<>();
    }

    public static Texture getTexture(String path) {
        if(textures.containsKey(path)) {
            return textures.get(path);
        }
        Texture texture = loadTexture(path);
        textures.put(path, texture);
        return texture;
    }

    private static Texture loadTexture(String path) {
        String[] parts = path.split("\\.");
        String format = parts[parts.length - 1].toUpperCase();
        try (InputStream in = ResourceLoader.getResource(path).openStream()) {
            return TextureLoader.getTexture(format, in);
        } catch (IOException e) {
            throw new TextureLoadException("Could not load texture: " + path, e);
        }
    }
}