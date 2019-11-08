package ru.conveyor.test;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import ru.conveyor.FactoryManager;
import ru.conveyor.config.FactoryConfig;
import ru.conveyor.data.CrossingIndex;

import java.util.LinkedList;
import java.util.List;

public class ConveyorTest {

    @Test
    public void factoryManagerTest() {
        // Prepare factory manager
        List<CrossingIndex> crossingIndices = new LinkedList<CrossingIndex>();
        crossingIndices.add(new CrossingIndex(3, 4));
        crossingIndices.add(new CrossingIndex(6, 8));

        FactoryConfig factoryConfig = new FactoryConfig(crossingIndices, 9, 15);

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

        // InterSection verify
        int lastIndexOfConveyorA = statusConveyorA.get(statusConveyorA.size() - 1);
        int lastIndexOfConveyorB = statusConveyorB.get(statusConveyorB.size() - 1);

        Assert.assertThat(lastIndexOfConveyorA, CoreMatchers.is(lastIndexOfConveyorB));

        // InterSection verify in middle
        //todo: переделать после изменений
        for (int i = 0; i < factoryConfig.getLengthOfCrossing(); i++) {

            int intersectionIndexA = factoryConfig.getIntersectionA(i) - 1;
            int intersectionIndexB = factoryConfig.getIntersectionB(i) - 1;

            valueToBeReturned = factoryManager.getStatusConveyorA().get(intersectionIndexA);
            returnedValue = factoryManager.getStatusConveyorB().get(intersectionIndexB);

            Assert.assertThat(returnedValue, CoreMatchers.is(valueToBeReturned));
        }
/*
        // Negative test not Prime namber
        int notPrimeNumber = 2;
        Assert.assertThat(notPrimeNumber, CoreMatchers.is(IllegalArgumentException));


        // Push null
        Assert.assertThat(null, CoreMatchers.is(IllegalArgumentException));
*/

    }

    // Intersection pooin out of length conveyor
    @Test(expected = IllegalArgumentException.class)
    public void outOfLength() throws IllegalArgumentException {
        // Prepare factory manager
        List<CrossingIndex> crossingIndices = new LinkedList<CrossingIndex>();
        crossingIndices.add(new CrossingIndex(3, 4));
        crossingIndices.add(new CrossingIndex(6, 8));

        new FactoryConfig(crossingIndices, 3, 2);
    }

    // пока не разобрался как писать все негативне тесты в одном методе
    @Test(expected = IllegalArgumentException.class)
    public void pushNegative() throws IllegalArgumentException {
        // Prepare factory manager
        List<CrossingIndex> crossingIndices = new LinkedList<CrossingIndex>();
        crossingIndices.add(new CrossingIndex(3, 4));
        crossingIndices.add(new CrossingIndex(6, 8));

        FactoryConfig factoryConfig = new FactoryConfig(crossingIndices, 10, 12);
        FactoryManager factoryManager = new FactoryManager(factoryConfig);

        // Start factory
        factoryManager.startFactory();

        factoryManager.pushA(-1);// не уверен то так можно
        factoryManager.pushB(-10);// сразу два в одном
    }

    @Test(expected = IllegalArgumentException.class)
    public void pushNotPrimeNumber() throws IllegalArgumentException {
        // Prepare factory manager
        List<CrossingIndex> crossingIndices = new LinkedList<CrossingIndex>();
        crossingIndices.add(new CrossingIndex(3, 4));
        crossingIndices.add(new CrossingIndex(6, 8));

        FactoryConfig factoryConfig = new FactoryConfig(crossingIndices, 10, 12);
        FactoryManager factoryManager = new FactoryManager(factoryConfig);

        // Start factory
        factoryManager.startFactory();

        factoryManager.pushA(2);
        factoryManager.pushB(6);
    }
    //todo: добавить ещё один тест (негативный) в этом классе на попытку pushA отрицательное число/не простое число/null
    //todo: на передачу в конфиг отрицательной длины конвееров
    //todo: на передачу индексов пересечения больше длины конвееров (в методе FactoryConfig.validateParameters(...) есть проверка)
    //todo: и заассертить что ожидаемо выбрасывается нужный эксепшн
}
