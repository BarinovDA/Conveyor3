package Main;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.*;

public class MainConveyor {
    private static Conveyor conveyorA = new Conveyor(9);
    private static Conveyor conveyorB = new Conveyor(11);

    //      ---Config---
    private static int[][] indexOfCrossing = {{3, 4}, {6, 8}};
    //    ---End config---

    public static void main(String[] args) {
        conveyorA.addAll(pushZero(conveyorA));
        conveyorB.addAll(pushZero(conveyorB));
        getStatus();

        for (int i = 0; i < 10; i++) System.out.println(pushA(generatePrimeNumber()));
        for (int i = 0; i < 20; i++) System.out.println(pushB(generatePrimeNumber()));

        getStatus();
    }

    public static int pushA(int a) {
        return pushConveyor(a, conveyorA, conveyorB);
    }

    public static int pushB(int b) {
        return pushConveyor(b, conveyorB, conveyorA);
    }

    private static int pushConveyor(int a, Conveyor conveyorToPush, Conveyor conveyorToUp) {
        int numForReturn = 0;
        conveyorToPush.add(a);
        //cut to normal size
        while (conveyorToPush.arrayLength < conveyorToPush.size()) {
            numForReturn = conveyorToPush.get(conveyorToPush.size() - 1);
            conveyorToPush.remove(conveyorToPush.size() - 1);
        }
        //crossing
        if (conveyorToPush == conveyorA) {
            for (int i = 0; i < indexOfCrossing.length; i++) {
                conveyorToUp.remove(indexOfCrossing[i][1] - 1);
                conveyorToUp.add(indexOfCrossing[i][1] - 1, conveyorToPush.get(indexOfCrossing[i][0] - 1));
            }
        }else{
            for (int i = 0; i < indexOfCrossing.length; i++) {
                conveyorToUp.remove(indexOfCrossing[i][0] - 1);
                conveyorToUp.add(indexOfCrossing[i][0] - 1, conveyorToPush.get(indexOfCrossing[i][1] - 1));
            }
        }
        //last crossin
        conveyorToUp.remove(conveyorToUp.size() - 1);
        conveyorToUp.add(conveyorToUp.size(), conveyorToPush.get(conveyorToPush.arrayLength - 1));
        return numForReturn; //все буковки?)
    }

    public static void getStatus() {
        System.out.println("Состояние конвеера А: " + conveyorA);
        System.out.println("Состояние конвеера B: " + conveyorB);
        for (int[] arr : indexOfCrossing) {
            System.out.println("Точки пересечений - " + Arrays.toString(arr));
        }
    }

    private static int generatePrimeNumber() {
        int randomNum = 0;
        for (int i = 0; i < 1000; i++) {
            randomNum =(int) (Math.random() * 1000);
            if (isPrime(randomNum)) return randomNum;
        }
        return randomNum;
    }

    private static boolean isPrime(int number) {
        int count = 0;
        for (int i = 2; i <= number; i++) {
            if (number % i == 0) count++;
            if (count > 1) return false;
        }
        return true;
    }

    private static Collection pushZero(Conveyor conveyor) {
        List<Integer> list = new LinkedList();
        while (list.size() < conveyor.arrayLength) {
            list.add(0);
        }
        return list;
    }
    /*
    тест на junit
     */
}















