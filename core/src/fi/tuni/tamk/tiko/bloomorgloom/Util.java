package fi.tuni.tamk.tiko.bloomorgloom;

import java.security.SecureRandom;
import java.util.Random;

/**
 * A util class for various methods.
 */
public class Util {
    /**
     * Randomizes a float between given values.
     * @param min Minimum value of randomized number.
     * @param max Maximum value of randomized number.
     * @return Returns the randomized float.
     */
    public static float randomFloat(float min, float max) {
        Random random = new SecureRandom();
        return min + random.nextFloat() * (max - min);
    }
}
