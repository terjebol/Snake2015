package org.nardogames.fastmath;

public class FastRandom {

    public static int randSeed;

    public static final int random() {
        // this makes a 'nod' to being potentially called from multiple threads
        int seed = randSeed;

        seed *= 1103515245;
        seed += 12345;
        randSeed = seed;
        return seed;
    }

    public static final int random(int range) {
        return ((random() >>> 15) * range) >>> 17;
    }

    public static final boolean randomBoolean() {
        return random() > 0;
    }

    public static final float randomFloat() {
        return (random() >>> 8) * (1.f / (1 << 24));
    }

    public static final double randomDouble() {
        return (random() >>> 8) * (1.0 / (1 << 24));
    }
}
