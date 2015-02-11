package org.nardogames.fastmath;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

public class BufferedImageUtils
{
	public static BufferedImage createFromArray(int[] data, int imageWidth, int imageHeight)
	{
		return createFromArray(data,imageWidth,imageHeight,false);
	}
	
	public static BufferedImage createFromArray(int[] data, int imageWidth, int imageHeight, boolean useAlpha)
	{
		BufferedImage image = new BufferedImage(imageWidth,imageHeight, useAlpha ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
		DataBufferInt buffer = ((DataBufferInt)image.getRaster().getDataBuffer());
		int[] rasterData = buffer.getData();
		System.arraycopy(data, 0, rasterData,0, rasterData.length);
		return image;
	}
	
	public static int[] getDataCopy(BufferedImage image) {
	    DataBufferInt buffer = ((DataBufferInt)image.getData().getDataBuffer());
        return buffer.getData();
	}
	
	public static BufferedImage makeCompatible(BufferedImage image) {
	    BufferedImage compatibleImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = compatibleImage.createGraphics();
	    g.drawImage(image, 0,  0,  null);
	    g.dispose();
	    return compatibleImage;
	}

    public static BufferedImage loadCompatibleImage(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        return makeCompatible(image);
    }
}
