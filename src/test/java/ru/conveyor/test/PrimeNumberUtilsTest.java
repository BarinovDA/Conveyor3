package ru.conveyor.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.conveyor.util.PrimeNumberUtils;

import javax.validation.constraints.AssertTrue;

import static org.junit.jupiter.api.AssertTrue.assertTrue;

public class PrimeNumberUtilsTest {

    //todo: где аннотация @Test? Почему тут только негативные сценарии?
    //todo: тестовые методы называются хххtest, а не наоборот
    //todo: где тест для метода generatePrimeNumber()?
    @Test
    public void testUtils (){

        // test PrimeNumbersUtils.isPrime
        boolean result = PrimeNumberUtils.isPrime(0);
        //todo: есть метод assertFalse/assertTrue
        //MatcherAssert.assertFalse();
        MatcherAssert.assertThat(false, CoreMatchers.is(result));
        result = PrimeNumberUtils.isPrime(-5);
        MatcherAssert.assertThat(false, CoreMatchers.is(result));

        Assertions.assertTrue(PrimeNumberUtils.isPrime(2));
    }
}
