package ru.conveyor.util;

import org.apache.commons.math3.primes.Primes;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumberUtils {

    /**
     * Return List of a few hundred prime numbers
     * */
    public static List<Integer> generatePrimeNumber() {
        List<Integer> list = new ArrayList<>();

        int lastPrime = 1;

        for (int i = 1; i <= 200; i += 1) {
            lastPrime = Primes.nextPrime(lastPrime);
            list.add(lastPrime);
        }

        return list;
    }

    public static boolean isPrime(int number) {
       return Primes.isPrime(number);
    }

}
