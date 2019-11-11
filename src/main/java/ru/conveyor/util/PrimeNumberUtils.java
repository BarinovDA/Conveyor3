package ru.conveyor.util;

import org.apache.commons.math3.primes.Primes;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumberUtils {
    /**
     * Method return List of prime number, length of list 108 elements.
     * */
    public static List<Integer> generatePrimeNumber() {
        List<Integer> list = new ArrayList<>();

        int lastPrime = Primes.nextPrime(3);

        // до 600 потому, опытным путем подобранно, для создания Листа на ~ 100 эл-ов
        // Primes.nextPrime(...) в аргументах передавать предыдущее сгенерированное простое число
        for (int i = 3; i <= 600; i += 2) {
            lastPrime = Primes.nextPrime(lastPrime);
            list.add(lastPrime);
        }
        return list;
    }

    //подключить через Maven библиотеку Apache Commons Math поменять тело метода на
    public static boolean isPrime(int number) {
       return Primes.isPrime(number);
    }
}
