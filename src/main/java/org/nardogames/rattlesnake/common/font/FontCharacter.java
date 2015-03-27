package org.nardogames.rattlesnake.common.font;

import java.awt.image.BufferedImage;

public class FontCharacter {
    private BufferedImage image;
    private int asciiValue;

    public FontCharacter(BufferedImage image, int asciiValue) {

        this.image = image;
        this.asciiValue = asciiValue;
    }
}
