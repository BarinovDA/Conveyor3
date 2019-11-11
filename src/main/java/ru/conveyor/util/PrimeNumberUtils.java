package ru.conveyor.util;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumberUtils {
    /**
     * Method return List of prime number, length of list 108 elements.
     * */
    public static List<Integer> generatePrimeNumber() {
        List<Integer> list = new ArrayList<>();

        // до 600 потому, опытным путем подобранно, для создания Листа на ~ 100 эл-ов

        //todo: подключить через Maven библиотеку Apache Commons Math и поменять цикл на
        // Primes.nextPrime(...) в аргументах передавать предыдущее сгенерированное простое число
        for (int i = 3; i <= 600; i += 2) {
            if (isPrime(i)) {
                list.add(i);
            }
        }
        return list;
    }

    //todo: подключить через Maven библиотеку Apache Commons Math поменять тело метода на
    // return Primes.isPrime(number);
    public static boolean isPrime(int number) {
        if (number < 3) return false;
            for (int i = 2; i <= number / 2; i++) {
                if (number % i == 0) return false;
            }
        return true;
    }
}
