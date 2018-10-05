package utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by DJ Yuhn on 10/2/2018
 */
public class RandomNumberGeneratorTest {
    @Test
    public void testToSeeIfRandomNumbersGeneratedReturnNumbersBetweenOneAndFour100Times () {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        for(int i = 0; i < 100; i++) {
            int randomNumber = randomNumberGenerator.randomNumberInRangeInclusive(1, 4);
            assert (randomNumber >= 1 && randomNumber <= 4);
        }
    }

}