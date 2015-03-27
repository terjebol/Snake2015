package org.nardogames.test;

import org.nardogames.rattlesnake.common.font.FontCharacter;
import org.nardogames.rattlesnake.common.font.FontLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FontLoaderTest {

    FontLoaderTest() throws URISyntaxException {

        URL foo = ResourceLoader.getResource("font/");
        Path path = Paths.get(foo.toURI());

        try {
            List<FontCharacter> fontCharacters = FontLoader.loadFontFromPath(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws URISyntaxException {
        new FontLoaderTest();
    }
}
