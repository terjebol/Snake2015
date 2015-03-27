package org.nardogames.rattlesnake.common.font;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FontLoader {

    private List<FontCharacter> fontChars;

    private FontLoader() {
        fontChars = new ArrayList<>();
    }

    public static List<FontCharacter> loadFontFromPath(Path path) throws IOException{
        FontLoader fontLoader = new FontLoader();
        fontLoader.addAllFilesInPath(path);
        return fontLoader.fontChars;
    }

    public void addAllFilesInPath(Path path) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    addAllFilesInPath(entry);
                }
                fontChars.add(createCharacterImageFromFile(entry));
            }
        }
    }

    private FontCharacter createCharacterImageFromFile(Path file) throws IOException {

        // requires image files of name char_XXX.png where XXX is the ascii value of the char.
        String fileName = file.toFile().getName();
        int asciiValueIndex = fileName.lastIndexOf("_");
        int extensionIndex = fileName.lastIndexOf(".");
        int asciiValue = Integer.parseInt(fileName.substring(asciiValueIndex + 1, extensionIndex));
        BufferedImage image = ImageIO.read(file.toFile());
        System.out.println("Created image for "+((char)asciiValue));
        return new FontCharacter(image, asciiValue);
    }
}
