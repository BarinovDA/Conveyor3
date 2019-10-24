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

        getStatus();
        pushA(1);
        getStatus();
        pushB(55);
        getStatus();
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
           conveyorB.add(indexOfCrossing[i][1]-1, conveyorA.get(indexOfCrossing[i][0]-1));
        }
        //last crossin
        conveyorB.remove(conveyorB.size()-1);
        conveyorB.add(conveyorB.size(), conveyorA.get(conveyorA.arrayLength-1));
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
            conveyorA.add(indexOfCrossing[i][0]-1, conveyorB.get(indexOfCrossing[i][1]-1));
        }
        //last crossin
        conveyorA.remove(conveyorA.size()-1);
        conveyorA.add(conveyorA.size(), conveyorB.get(conveyorB.arrayLength-1));
        return numFoRetur;
    }

    public static void getStatus () {
        System.out.println("Состояние конвеера А: " + conveyorA);
        System.out.println("Состояние конвеера B: " + conveyorB);
        for (int[] arr : indexOfCrossing){
            System.out.println("Точки пересечений - " + Arrays.toString(arr));
        }
    }

    private static void generateList(){
       conveyorA.addAll(Arrays.asList(9,2,3,4,5,6,7,8,9));
       if (conveyorA.arrList.size() < conveyorA.arrayLength){
           for (int i = conveyorA.arrList.size(); i < conveyorA.arrayLength ;i++){
               conveyorA.add(i, 0);
           }
       }
        while (conveyorA.arrayLength < conveyorA.size()){
            conveyorA.remove(conveyorA.size()-1);
        }
       conveyorB.addAll(Arrays.asList(11,22,33,44,55,66,77,88,99,101,111,121,131));
        if (conveyorB.arrList.size() < conveyorB.arrayLength){
            for (int i = conveyorB.arrList.size(); i < conveyorB.arrayLength ;i++){
                conveyorA.add(i, 0);
            }
        }
        while (conveyorB.arrayLength < conveyorB.size()){
            conveyorB.remove(conveyorB.size()-1);
        }

    }
}
