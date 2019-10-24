package Main;

import java.util.Arrays;
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
        generateList();

        getStatus();
        pushA(1);
        getStatus();
        pushB(55);
        getStatus();
    }

    public static int pushA(int a) {
        return pushConveyor(a, conveyorA);
    }

    public static int pushB(int b) {
        return pushConveyor(b, conveyorA);
    }

    private static int pushConveyor(int a, Conveyor conveyor) {
        Conveyor convB;
        Conveyor convA;
        if (conveyor == conveyorA) {
            convA = conveyor;
            convB = conveyorB;
        } else {
            convA = conveyorB;
            convB = conveyorA;
        }
        int numForReturn = -1;
        convA.add(a);
        //cut to normal size
        while (convA.arrayLength < convA.size()) {
            numForReturn = convA.get(convA.size() - 1);
            convA.remove(convA.size() - 1);
        }
        //crossing
        for (int i = 0; i < indexOfCrossing.length; i++) {
            convB.add(indexOfCrossing[i][1] - 1, convA.get(indexOfCrossing[i][0] - 1));
        }
        //last crossin
        convB.remove(convB.size() - 1);
        convB.add(convB.size(), convA.get(convA.arrayLength - 1));
        return numForReturn; //все буковки?)

    }

    public static void getStatus() {
        System.out.println("Состояние конвеера А: " + conveyorA);
        System.out.println("Состояние конвеера B: " + conveyorB);
        for (int[] arr : indexOfCrossing) {
            System.out.println("Точки пересечений - " + Arrays.toString(arr));
        }
    }

    private static void generateList() {
        conveyorA.addAll(generatePrimeNumber(conveyorA.arrayLength, conveyorA));
        conveyorB.addAll(generatePrimeNumber(conveyorB.arrayLength, conveyorB));
    }

    private static List<Integer> generatePrimeNumber(int len, Conveyor conveyor) {
        List<Integer> collection = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < len; i++) {
            int randomNum = (int) Math.random() * 1000;
            for (int j = 1; j <= randomNum; j++) {
                if (randomNum % j == 0) count++;
            }
            if (count <= 2) collection.add(randomNum);
        }
        if (len < conveyor.arrayLength){
            for (int i = len; len <  conveyor.arrayLength; i ++){
                conveyor.add(0);
            }
        }else if (len > conveyor.arrayLength){
            for (int i = len; len <  conveyor.arrayLength; i ++){
                conveyor.remove(i);
            }
        }
        return collection;
    }
}















