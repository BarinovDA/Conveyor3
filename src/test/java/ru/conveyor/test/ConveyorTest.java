package ru.conveyor.test;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import ru.conveyor.FactoryManager;
import ru.conveyor.config.FactoryConfig;

import java.util.List;

public class ConveyorTest {

    @Test
    public void factoryManagerTest() {
        // Prepare factory manager
        int[][] crossingPoints = {{3, 4}, {6, 8}};
        FactoryConfig factoryConfig = new FactoryConfig(crossingPoints, 9, 15);

        FactoryManager factoryManager = new FactoryManager(factoryConfig);

        // Start factory
        factoryManager.startFactory();

        // Get Conveyors status
        List<Integer> statusConveyorA = factoryManager.getStatusConveyorA();
        List<Integer> statusConveyorB = factoryManager.getStatusConveyorB();

        // Assert size
        Assert.assertThat(statusConveyorA.size(), CoreMatchers.is(9));
        Assert.assertThat(statusConveyorB.size(), CoreMatchers.is(15));

        // Push values
        int valueToBeReturned = statusConveyorA.get(statusConveyorA.size() - 1);
        int returnedValue = factoryManager.pushA(17);

        // Assert values
        Assert.assertThat(returnedValue, CoreMatchers.is(valueToBeReturned));

        // Update conveyor status
        statusConveyorA = factoryManager.getStatusConveyorA();

        // Assert pushed value
        Assert.assertThat(statusConveyorA.get(0), CoreMatchers.is(17));


        // Push values
        valueToBeReturned = statusConveyorB.get(statusConveyorB.size() - 1);
        returnedValue = factoryManager.pushB(19);

        // Assert values
        Assert.assertThat(returnedValue, CoreMatchers.is(valueToBeReturned));

        // Update conveyor status
        statusConveyorB = factoryManager.getStatusConveyorB();

        // Assert pushed value
        Assert.assertThat(statusConveyorB.get(0), CoreMatchers.is(19));
    }

    @Test
    public void dimaTest() {
//        int[][] cross = {{3, 4}, {6, 8}};
//        new FactoryConfig(cross, 9, 15);
//
//        Boolean work = true;
//        List<Integer> statusConvA = FactoryManager.getStatus(FactoryManager.conveyorA);
//        List<Integer> statusConvB = FactoryManager.getStatus(FactoryManager.conveyorB);
//        int eq = statusConvA.get(statusConvA.size() - 1);
//        int eq2 = FactoryManager.pushA(1);
//
//        if (eq != eq2) work = false;
//
//        for (int i = 0; i < cross.length; i++) {
//            if (!statusConvA.get(cross[i][0] - 1).equals(statusConvB.get(cross[i][1] - 1))) work = false;
//        }
//        if (!statusConvA.get(FactoryManager.conveyorA.size() - 1).equals(statusConvB.get(FactoryManager.conveyorB.size() - 1)))
//            work = false;
//
//        if (work) System.out.println("it's moving");
//        else System.out.println("dose not worK");
    }
}
