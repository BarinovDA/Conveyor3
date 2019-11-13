package ru.conveyor.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.conveyor.util.PrimeNumberUtils;

public class PrimeNumberUtilsTest {

    //todo: где тест для метода generatePrimeNumber()?
    @Test
    public void utilsTest(){
        Assertions.assertFalse(PrimeNumberUtils.isPrime(-5));
        Assertions.assertFalse(PrimeNumberUtils.isPrime(0));
        Assertions.assertFalse(PrimeNumberUtils.isPrime(1));
        Assertions.assertFalse(PrimeNumberUtils.isPrime(99));

        Assertions.assertTrue(PrimeNumberUtils.isPrime(2));
        Assertions.assertTrue(PrimeNumberUtils.isPrime(5));
        Assertions.assertTrue(PrimeNumberUtils.isPrime(17));
        Assertions.assertTrue(PrimeNumberUtils.isPrime(83));
    }
}
