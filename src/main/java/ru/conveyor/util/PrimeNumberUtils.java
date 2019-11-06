package ru.conveyor.util;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumberUtils {

    public static List<Integer> generatePrimeNumber() {
        List<Integer> list = new ArrayList<>();

        list.add(2);

        // до 600 потому, что так исторически сложилось (опытным путем подобранно, для создания Листа на ~ 100 эл-ов)
        for (int i = 3; i <= 600; i += 2) {
            if (isPrime(i)) {
                list.add(i);
            }
        }

        return list;
    }

    public static boolean isPrime(int number) {
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
