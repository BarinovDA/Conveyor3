package Main;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TestFactory {
    public static void main(String[] args) {
        int[][] cross = {{3,4},{6,8}};
        new FactoryConfig(cross, 9,15);
        MainConveyor mainConveyor = new MainConveyor();
        mainConveyor.main();
        test(cross);
    }

    private static void test (int[][] cross){
        Boolean work = true;
        List<Integer> statusConvA = MainConveyor.getStatus(MainConveyor.conveyorA);
        List<Integer> statusConvB = MainConveyor.getStatus(MainConveyor.conveyorB);
        int eq = statusConvA.get(statusConvA.size()-1);
        int eq2 = MainConveyor.pushA(1);

            if (eq != eq2) work = false;

            for (int i = 0; i < cross.length; i++){
                if (!statusConvA.get(cross[i][0] - 1).equals(statusConvB.get(cross[i][1] - 1))) work = false;
            }
            if (!statusConvA.get(MainConveyor.conveyorA.size() - 1).equals(statusConvB.get(MainConveyor.conveyorB.size() - 1))) work = false;

            if(work) System.out.println("it's moving");
            else System.out.println("dose not worK");
    }
}
