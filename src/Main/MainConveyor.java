package Main;

import java.util.*;

class MainConveyor {
    public static Conveyor conveyorA = new Conveyor(9);
    public static Conveyor conveyorB = new Conveyor(11);
    static List<Integer> primeNum = new ArrayList<>();
    //      ---Config---
    private static int[][] indexOfCrossing = {{3, 4}, {6, 8}};
    //    ---End config---

    public static void main() {
        generatePrimeNumber();

        fillConveyor(conveyorA);
        fillConveyor(conveyorB);

        System.out.println(getStatus(conveyorA));
        System.out.println(getStatus(conveyorB));
        System.out.println("================================================");
        pushB(1);
        System.out.println(getStatus(conveyorA));
        System.out.println(getStatus(conveyorB));

    }

    private static void fillConveyor(Conveyor conveyor) {
        for (int i = 0; i < conveyor.arrayLength; i++) {
            int x = (int) (Math.random() * 100);
            conveyor.arrList.add(primeNum.get(x));
        }
    }

    public static int pushA(int a) {
        return pushConveyor(a, conveyorA, conveyorB);
    }

    public static int pushB(int b) {
        return pushConveyor(b, conveyorB, conveyorA);
    }

    public static LinkedList<Integer> getStatus(Conveyor conveyor) {
        return conveyor.arrList;
    }

    private static int pushConveyor(int num, Conveyor conveyorToPush, Conveyor conveyorToUp) {
        int numForReturn = 0;
        conveyorToPush.add(num);
        //crossing
        if (conveyorToPush == conveyorA) {
            for (int i = 0; i < indexOfCrossing.length; i++) {
                conveyorToUp.set(indexOfCrossing[i][1] - 1, conveyorToPush.get(indexOfCrossing[i][0] - 1));
            }
        } else {
            for (int i = 0; i < indexOfCrossing.length; i++) {
                conveyorToUp.set(indexOfCrossing[i][0] - 1, conveyorToPush.get(indexOfCrossing[i][1] - 1));
            }
        }
        //last crossin
        conveyorToPush.remove(conveyorToPush.arrayLength);
        conveyorToUp.set(conveyorToUp.arrayLength - 1, conveyorToPush.get(conveyorToPush.arrayLength - 1));
        return numForReturn;
    }

    private static void generatePrimeNumber() {
        // до 600 потому, что так исторически сложилось (опытным путем подобранно, для создания Листа на ~ 100 эл-ов)
        for (int i = 3; i <= 600; i += 2) {
            if (isPrime(i)) primeNum.add(i);
        }
    }

    private static boolean isPrime(int number) {
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    /*
    *
    *сделай класс FactoryConfig в котором будут точки пересечения
        и из теста напрямую запускай его
        startFactory(FactoryConfig config)
        и там можно определять любые точки
    * */
}















