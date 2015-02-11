package org.nardogames.fastmath.easing;

public class Linear {

    public final static EaseIn easeIn = new EaseIn();
    public final static EaseOut easeOut = new EaseOut();
    public final static EaseInOut easeInOut = new EaseInOut();

    public static class EaseIn implements IEasingMethod {
        public float execute (float t,float b , float c, float d) {
            return c*t/d + b;
        }
    }

    public static class EaseOut implements IEasingMethod {
        public float execute (float t,float b , float c, float d) {
            return c*t/d + b;
        }
    }

    public static class EaseInOut implements IEasingMethod {
        public float execute (float t,float b , float c, float d) {
            return c*t/d + b;
        }
    }
}
