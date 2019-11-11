package ru.conveyor.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.conveyor.util.PrimeNumberUtils;

public class PrimeNumberUtilsTest {

    //todo: где аннотация @Test? Почему тут только негативные сценарии?
    //todo: тестовые методы называются хххtest, а не наоборот
    //todo: где тест для метода generatePrimeNumber()?
    @Test
    public void testUtils (){

        // test PrimeNumbersUtils.isPrime
        //todo: что за слово такое resault?
        boolean resault = PrimeNumberUtils.isPrime(0);
        //todo: есть метод assertFalse/assertTrue
        MatcherAssert.assertThat(false, CoreMatchers.is(resault));

        resault = PrimeNumberUtils.isPrime(-5);
        MatcherAssert.assertThat(false, CoreMatchers.is(resault));

        //todo: почему тест падает на этой строчке?
        Assertions.assertTrue(PrimeNumberUtils.isPrime(2));
    }
}
