package ru.conveyor.test;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
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
        Assert.assertThat(false, CoreMatchers.is(resault));

        resault = PrimeNumberUtils.isPrime(-5);
        Assert.assertThat(false, CoreMatchers.is(resault));

        //todo: почему тест падает на этой строчке?
        Assert.assertTrue(PrimeNumberUtils.isPrime(2));
    }
}
