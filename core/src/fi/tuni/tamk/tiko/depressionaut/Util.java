package fi.tuni.tamk.tiko.depressionaut;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    /**
     * Rounds a double to specified decimals.
     *
     * @param value Given double
     * @param places Desired number of decimals
     * @return Rounded double
     */
    public static double roundDouble(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
