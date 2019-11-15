package ru.conveyor.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.conveyor.config.FactoryConfig;
import ru.conveyor.data.ConveyorType;
import ru.conveyor.data.IntersectionPoint;
import ru.conveyor.service.FactoryService;

import java.util.LinkedList;
import java.util.List;

public class ConveyorTest {

    @ParameterizedTest
    @EnumSource(ConveyorType.class)
    public void conveyorTest(ConveyorType conveyorType) {
        if (conveyorType == ConveyorType.THREAD_SAFE) {
            return; // todo: not implemented yet
        }

        // Prepare factory manager
        List<IntersectionPoint> crossingIndices = new LinkedList<>();
        crossingIndices.add(new IntersectionPoint(3, 4));
        crossingIndices.add(new IntersectionPoint(6, 8));

        FactoryConfig factoryConfig = new FactoryConfig(crossingIndices, 9, 15, conveyorType, true);

        FactoryService factoryManager = new FactoryService(factoryConfig);

        // Get Conveyors status
        List<Integer> statusConveyorA = factoryManager.getStatusConveyorA();
        List<Integer> statusConveyorB = factoryManager.getStatusConveyorB();

        // Assert size
        MatcherAssert.assertThat(statusConveyorA.size(), CoreMatchers.is(9));
        MatcherAssert.assertThat(statusConveyorB.size(), CoreMatchers.is(15));

        // Push values
        int valueToBeReturned = statusConveyorA.get(statusConveyorA.size() - 1);
        int returnedValue = factoryManager.pushA(17);

        // Assert values
        MatcherAssert.assertThat(returnedValue, CoreMatchers.is(valueToBeReturned));

        // Update conveyor status
        statusConveyorA = factoryManager.getStatusConveyorA();

        // Assert pushed value
        MatcherAssert.assertThat(statusConveyorA.get(0), CoreMatchers.is(17));

        // Push values
        valueToBeReturned = statusConveyorB.get(statusConveyorB.size() - 1);
        returnedValue = factoryManager.pushB(19);

        // Assert values
        MatcherAssert.assertThat(returnedValue, CoreMatchers.is(valueToBeReturned));

        // Update conveyor status
        statusConveyorB = factoryManager.getStatusConveyorB();

        // Assert pushed value
        MatcherAssert.assertThat(statusConveyorB.get(0), CoreMatchers.is(19));

        // InterSection verification
        for (IntersectionPoint point : factoryConfig.getIntersectionPoints()) {
            int valueA = factoryManager.getStatusConveyorA().get(point.getIndexA());
            int valueB = factoryManager.getStatusConveyorB().get(point.getIndexB());

            MatcherAssert.assertThat(valueA, CoreMatchers.is(valueB));
        }
    }
}
