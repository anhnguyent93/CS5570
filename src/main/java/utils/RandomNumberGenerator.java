package utils;

import java.util.Random;

/**
 * Created by DJ Yuhn on 10/2/2018
 */
public class RandomNumberGenerator {

    public static int randomNumberInRangeInclusive(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
