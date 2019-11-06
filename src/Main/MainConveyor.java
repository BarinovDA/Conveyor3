package Main;

import java.util.*;

class MainConveyor {
    public static Conveyor conveyorA = new Conveyor(FactoryConfig.getConvAlength());
    public static Conveyor conveyorB = new Conveyor(FactoryConfig.getConvBlength());
    public static List<Integer> primeNum = new ArrayList<>();

    public static void main() {
        generatePrimeNumber();
        fillConveyor(conveyorA);
        fillConveyor(conveyorB);
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

    public static List<Integer> getStatus(Conveyor conveyor) {
        return Collections.unmodifiableList(conveyor.arrList);
    }

    private static int pushConveyor(int num, Conveyor conveyorToPush, Conveyor conveyorToUp) {
        int numForReturn = conveyorToPush.get(conveyorToPush.size()-1);
        conveyorToPush.add(num);
        //crossing
        int convLength = FactoryConfig.getCrossIndex().length;
        if (conveyorToPush == conveyorA) {
            for (int i = 0; i < convLength; i++) {
                conveyorToUp.set(FactoryConfig.getCrossIndex()[i][1] - 1, conveyorToPush.get(FactoryConfig.getCrossIndex()[i][0] - 1));
            }
        } else {
            for (int i = 0; i < convLength; i++) {
                conveyorToUp.set(FactoryConfig.getCrossIndex()[i][0] - 1, conveyorToPush.get(FactoryConfig.getCrossIndex()[i][1] - 1));
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

}















