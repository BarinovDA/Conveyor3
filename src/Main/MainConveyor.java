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
        getStatus();
        pushA(1);
        getStatus();
        pushB(55);
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
        for (int i = 0; i < indexOfCrossing.length; i++) {
            conveyorToUp.add(indexOfCrossing[i][1] - 1, conveyorToPush.get(indexOfCrossing[i][0] - 1));
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
            randomNum = (int) Math.random() * 1000;
            if (isPrime(randomNum)) return randomNum;
        }
        return randomNum;
    }

    private static boolean isPrime (int number){
        int count =0;
        for (int i = 2; i <= number; i++){
            if (number%i == 0) count++;
            if (count > 1) return false
        }
        return true;
    }
    /*
    возвращать 0 если ничего не упало
    генератор без аргументов
    генератор не добавляет ничего сам в массив
    логика проверки числа на простоту
    метод пуш с двумя агрументами (ленка куда добавить и лента пересчения)
    -1 что за число
    заполнять через пуш в цикле
    тест на junit
    метод получения всех чисел (из условия)
     */
}















