package ru.conveyor.test;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import ru.conveyor.util.PrimeNumberUtils;

public class PrimeNumberUtilsTest {

    public void testUtils (){

        // test PrimeNumbersUtils.isPrime
        boolean resault = PrimeNumberUtils.isPrime(0);
        Assert.assertThat(false, CoreMatchers.is(resault));

        resault = PrimeNumberUtils.isPrime(-5);
        Assert.assertThat(false, CoreMatchers.is(resault));

    }
}
