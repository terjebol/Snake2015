package org.nardogames.fastmath.easing;


public class Cubic {

    public final static EaseIn easeIn = new EaseIn();
    public final static EaseOut easeOut = new EaseOut();
    public final static EaseInOut easeInOut = new EaseInOut();

    public static class EaseIn implements IEasingMethod {
        public float execute (float t,float b , float c, float d) {
            return c*(t/=d)*t*t + b;
        }
    }

    public static class EaseOut implements IEasingMethod {
        public float execute (float t,float b , float c, float d) {
            return c*((t=t/d-1)*t*t + 1) + b;
        }
    }

    public static class EaseInOut implements IEasingMethod {
        public float execute (float t,float b , float c, float d) {
            if ((t/=d/2) < 1) return c/2*t*t*t + b;
            return c/2*((t-=2)*t*t + 2) + b;
        }
    }

}
