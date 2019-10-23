package Main;
import java.util.Arrays;

public class MainConveyor {
    private static Conveyor conveyorA = new Conveyor(9);
    private static Conveyor conveyorB = new Conveyor(11);

    //      ---Config---
               private static int[][] indexOfCrossing = {{3, 4},{6, 8}};
    //    ---End config---

    public static void main(String[] args) {
        generateList();



    }

    public static int pushA (int a){
        int numFoRetur = -1;
        conveyorA.add(a);
        //cut to normal size
        while (conveyorA.arrayLength < conveyorA.size()){
            numFoRetur = conveyorA.get(conveyorA.size()-1);
            conveyorA.remove(conveyorA.size()-1);
        }
        //crossing
        for (int i = 0; i < indexOfCrossing.length; i++ ){
           conveyorB.add(indexOfCrossing[i][0]-1, conveyorA.get(indexOfCrossing[i][0]-1));
        }
        //last crossin
        conveyorB.add(conveyorB.size(), conveyorA.get(conveyorA.size()-1));
        return numFoRetur;
    }

    public static int pushB (int b){
        int numFoRetur = -1;
        conveyorB.add(b);
        //cut to normal size
        while (conveyorB.arrayLength < conveyorB.size()){
            numFoRetur = conveyorB.get(conveyorB.size()-1);
            conveyorB.remove(conveyorB.size()-1);
        }
        //crossing
        for (int i = 0; i < indexOfCrossing.length; i++ ){
            conveyorA.add(indexOfCrossing[i][1]-1, conveyorB.get(indexOfCrossing[i][1]-1));
        }
        //last crossin
        conveyorA.add(conveyorA.size(), conveyorB.get(conveyorB.size()-1));
        return numFoRetur;
    }

    public static void getStatus () {
        System.out.println("Состояние конвеера А: " + conveyorA);
        System.out.println("Состояние конвеера B: " + conveyorB);
        System.out.println("Точки пересечений" + indexOfCrossing);
    }

    private static void generateList(){
       conveyorA.addAll(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13));
       conveyorB.addAll(Arrays.asList(11,22,33,44,55,66,77,88,99,101,111,121,131));
    }
}
