package ru.conveyor.util;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumberUtils {

    public static List<Integer> generatePrimeNumber() {
        List<Integer> list = new ArrayList<>();
        /**
         * Method return List of prime number, lienth of list 108 elemets.
         * */
        // до 600 потому, опытным путем подобранно, для создания Листа на ~ 100 эл-ов
        for (int i = 3; i <= 600; i += 2) {
            if (isPrime(i)) {
                list.add(i);
            }
        }
        return list;
    }

    public static boolean isPrime(int number) {
        if (number < 3) return false;
            for (int i = 2; i <= number / 2; i++) {
                if (number % i == 0) return false;
            }
        return true;
    }
}
