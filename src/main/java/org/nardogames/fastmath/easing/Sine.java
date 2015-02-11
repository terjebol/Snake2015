package org.nardogames.fastmath.easing;


public class Sine {
    
    public final static EaseIn easeIn = new EaseIn();
    public final static EaseOut easeOut = new EaseOut();
    public final static EaseInOut easeInOut = new EaseInOut();

    public static class EaseIn implements IEasingMethod {
        public float execute (float t,float b , float c, float d) {
            return -c * (float)Math.cos(t/d * (Math.PI/2)) + c + b;
        }
    }

    public static class EaseOut implements IEasingMethod {
        public float execute (float t,float b , float c, float d) {
            return c * (float)Math.sin(t/d * (Math.PI/2)) + b; 
        }
    }

    public static class EaseInOut implements IEasingMethod {
        public float execute (float t,float b , float c, float d) {
            return -c/2 * ((float)Math.cos(Math.PI*t/d) - 1) + b;
        }
    }

}
