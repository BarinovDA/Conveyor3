package ru.conveyor.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.conveyor.util.PrimeNumberUtils;

import java.util.List;

public class PrimeNumberUtilsTest {

    //todo: где тест для метода generatePrimeNumber()?
    @Test
    public void utilsTest() {
        Assertions.assertFalse(PrimeNumberUtils.isPrime(-5));
        Assertions.assertFalse(PrimeNumberUtils.isPrime(0));
        Assertions.assertFalse(PrimeNumberUtils.isPrime(1));
        Assertions.assertFalse(PrimeNumberUtils.isPrime(99));

        Assertions.assertTrue(PrimeNumberUtils.isPrime(2));
        Assertions.assertTrue(PrimeNumberUtils.isPrime(5));
        Assertions.assertTrue(PrimeNumberUtils.isPrime(17));
        Assertions.assertTrue(PrimeNumberUtils.isPrime(83));
    }

    @Test
    public void primeNumberTest() {
        List<Integer> primeNumberUtils = PrimeNumberUtils.generatePrimeNumber();

        Assertions.assertTrue(primeNumberUtils.size() > 0);

        for (Integer element : primeNumberUtils) {
            Assertions.assertTrue(PrimeNumberUtils.isPrime(element));
        }
    }
}
