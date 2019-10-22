package Main;
import java.util.ArrayList;

public class MainConveyor {
    static ArrayList<Integer> conveyorA = new ArrayList<Integer>();
    static ArrayList<Integer> conveyorB = new ArrayList<Integer>();
    //public static ConveyorA conveyorA = new ConveyorA();

    //      ---Config---
        static int ArrayLengthA = 9;
        static int ArrayLengthB = 11;
        static int countCrossing = 2;
        // index of crossin {1st conveyor, 2nd conveyor} ... - {common end};
        static int[][] indexOfCrossing = {{3, 4},{6,8}};
    //    ---End config---

    public static void main(String[] args) {

        conveyorA.add(1);
        conveyorA.add(2);
        conveyorA.add(3);
        conveyorA.add(4);
        conveyorA.add(5);
        conveyorA.add(6);
        conveyorA.add(7);
        conveyorA.add(8);
        conveyorA.add(9);
        conveyorA.add(10);
        conveyorA.add(11);
        conveyorA.add(11);
        conveyorA.add(11);
        conveyorA.add(11);
        conveyorA.add(11);
        conveyorA.add(11);
        conveyorA.add(11);
        conveyorA.add(11);
        conveyorA.add(11);
        conveyorA.add(11);
        conveyorA.add(11);

        conveyorB.add(11);
        conveyorB.add(21);
        conveyorB.add(31);
        conveyorB.add(41);
        conveyorB.add(51);
        conveyorB.add(61);
        conveyorB.add(71);
        conveyorB.add(81);
        conveyorB.add(91);
        conveyorB.add(101);
        conveyorB.add(111);

        System.out.println(conveyorA);
        System.out.println("выпало - " + MainConveyor.pushA(15));
        System.out.println("конвеер А - " + conveyorA);
        System.out.println("конвеер Б - " + conveyorB);

    }
    static int pushA (int a){
        int convSize = conveyorA.size();
        // cut to ArrayLengthA
        for (int x = convSize-1; x >= ArrayLengthA; x-- ) {
            conveyorA.remove(x);
        }
        convSize = conveyorA.size();
        int tmpNum;
        int tmpSet;
        int numForReturn;
        // push through all element
        for (int i = convSize-1; i > 1; i--){
            tmpNum = conveyorA.get(i-1);
            tmpSet = tmpNum;
            tmpNum = conveyorA.get(i-1);
            conveyorA.set(i-1,tmpSet);
        }
        conveyorA.add(0,a);
        //assign cross
        for(int j= 0; j < indexOfCrossing.length; j++){
            int indxConvA = indexOfCrossing[j][0]-1;
            int indxConvB =  indexOfCrossing[j][1]-1;
            conveyorB.set(indxConvB, conveyorA.get(indxConvA));
        }
        numForReturn = conveyorA.get(convSize);
        if (conveyorA.size() >= ArrayLengthA) conveyorA.remove(convSize);
        conveyorB.set(conveyorB.size()-1, conveyorA.get(conveyorA.size()-1));
        return numForReturn;
    }
}
