package org.nardogames.fastmath;

public class ColorUtils
{
	public static int packARGB(int r, int g, int b)
	{
		return packARGB(r,g,b,255);
	}
	
	public static int packARGB(int r, int g, int b, int a)
	{
		return (a << 24) + (r << 16) + (g << 8) + b;
	}
	
	public static int unpackAlpha(int argb)
	{
		return (argb & 0xFF000000) >>> 24;
	}
	
	public static int unpackRed(int argb)
	{
		return (argb & 0xFF0000) >>> 16;
	}
	
	public static int unpackGreen(int argb)
	{
		return (argb & 0xFF00) >>> 8;
	}
	
	public static int unpackBlue(int argb)
	{
		return (argb & 0xFF);
	}
	
	public static int[] unpack(int argb)
	{
		return new int[]
				{
					(argb & 0xFF000000) >>> 24,
					(argb & 0xFF0000) >>> 16,
					(argb & 0xFF00) >>> 8,
					(argb & 0xFF)
				};
	}
	
	public static int Blend(int backColor, int color, float amount)
    {
	    //cRed = (tRed * tAlpha + bRed * (255 - tAlpha)) / 255;
	    
	    int r = (int)((unpackRed(color) * amount) + unpackRed(backColor) * (1 - amount));
	    int g = (int)((unpackGreen(color) * amount) + unpackGreen(backColor) * (1 - amount));
	    int b = (int)((unpackBlue(color) * amount) + unpackBlue(backColor) * (1 - amount));
	    return packARGB(r, g, b);
	    
//        byte r = (byte)((color.R * amount) + backColor.R * (1 - amount));
//        byte g = (byte)((color.G * amount) + backColor.G * (1 - amount));
//        byte b = (byte)((color.B * amount) + backColor.B * (1 - amount));
//        return Color.FromArgb(r, g, b);
    }
	
	
	/** Takes a color, add R, G, B together and divide by 3.
	 * @param argb
	 * @return
	 */
	public static int unpackGrayscale(int argb) {
	    return (unpackRed(argb) + unpackGreen(argb) + unpackBlue(argb)) / 3;
	}
	
	
}
