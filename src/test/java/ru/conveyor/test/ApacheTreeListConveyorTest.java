package ru.conveyor.test;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import ru.conveyor.FactoryManager;
import ru.conveyor.config.ConveyorType;
import ru.conveyor.config.FactoryConfig;
import ru.conveyor.data.IntersectionPoint;

import java.util.LinkedList;
import java.util.List;

public class ApacheTreeListConveyorTest {

    @Test
    public void apacheTreeListConveyorTest() {
        // Prepare factory manager
        List<IntersectionPoint> crossingIndices = new LinkedList<>();
        crossingIndices.add(new IntersectionPoint(3, 4));
        crossingIndices.add(new IntersectionPoint(6, 8));

        FactoryConfig factoryConfig = new FactoryConfig(crossingIndices, 9, 15, ConveyorType.APACHE_TREE_LIST);

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

        // InterSection verify in middle
        for (IntersectionPoint point : factoryConfig.getIntersectionPoints()) {
            int valueA = factoryManager.getStatusConveyorA().get(point.getIntersectionForConveyorA());
            int valueB = factoryManager.getStatusConveyorB().get(point.getIntersectionForConveyorB());

            Assert.assertThat(valueA, CoreMatchers.is(valueB));
        }
    }
}
