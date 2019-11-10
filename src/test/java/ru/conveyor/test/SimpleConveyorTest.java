package ru.conveyor.test;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import ru.conveyor.FactoryManager;
import ru.conveyor.config.ConveyorType;
import ru.conveyor.config.FactoryConfig;
import ru.conveyor.data.IntersectionPoint;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SimpleConveyorTest {

    @Test
    public void factoryManagerTest() {
        // Prepare factory manager
        List<IntersectionPoint> crossingIndices = new LinkedList<IntersectionPoint>();
        crossingIndices.add(new IntersectionPoint(3, 4));
        crossingIndices.add(new IntersectionPoint(6, 8));

        FactoryConfig factoryConfig = new FactoryConfig(crossingIndices, 9, 15, ConveyorType.SIMPLE);

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

        //todo: это можно удалять
/*
        // Negative test not Prime namber
        int notPrimeNumber = 2;
        Assert.assertThat(notPrimeNumber, CoreMatchers.is(IllegalArgumentException));


        // Push null
        Assert.assertThat(null, CoreMatchers.is(IllegalArgumentException));
*/

    }

    //todo: pooin?

    // Intersection pooin out of length conveyor
    @Test(expected = IllegalArgumentException.class)
    public void outOfLengthTest() throws IllegalArgumentException {
        // Prepare factory manager
        List<IntersectionPoint> crossingIndices = new LinkedList<IntersectionPoint>();
        crossingIndices.add(new IntersectionPoint(3, 4));
        crossingIndices.add(new IntersectionPoint(6, 8));

        new FactoryConfig(crossingIndices, 3, 2, ConveyorType.SIMPLE);
    }

    // пока не разобрался как писать все негативне тесты в одном методе
    @Test(expected = IllegalArgumentException.class)
    public void pushNegativeTest() throws IllegalArgumentException {
        // Prepare factory manager
        List<IntersectionPoint> crossingIndices = new LinkedList<>();
        crossingIndices.add(new IntersectionPoint(3, 4));
        crossingIndices.add(new IntersectionPoint(6, 8));

        FactoryConfig factoryConfig = new FactoryConfig(crossingIndices, 10, 12, ConveyorType.SIMPLE);
        FactoryManager factoryManager = new FactoryManager(factoryConfig);

        // Start factory
        factoryManager.startFactory();

        //todo: у тебя до строчки pushB(-10) тест никогда не доходит
        factoryManager.pushA(-1);// не уверен то так можно
        factoryManager.pushB(-10);// сразу два в одном
    }

    @Test(expected = IllegalArgumentException.class)
    public void pushNotPrimeNumber() throws IllegalArgumentException {
        // Prepare factory manager
        List<IntersectionPoint> crossingIndices = new LinkedList<IntersectionPoint>();
        crossingIndices.add(new IntersectionPoint(3, 4));
        crossingIndices.add(new IntersectionPoint(6, 8));

        FactoryConfig factoryConfig = new FactoryConfig(crossingIndices, 10, 12, ConveyorType.SIMPLE);
        FactoryManager factoryManager = new FactoryManager(factoryConfig);

        // Start factory
        factoryManager.startFactory();
        //todo: предыдущие подготовительные строки дублируются из теста в тест
        // вынести в отдельный метод prepareFactory() который вернёт конфиг

        factoryManager.pushA(2);
        factoryManager.pushB(6);
    }

    //todo: Не падает, а должен
    @Test
    public void pushIllegalValuesTest() throws IllegalArgumentException {
        boolean exceptionIsThrown = false;

        // Negative case Length A
        try {
            new FactoryManager(new FactoryConfig(Collections.emptyList(), -5, 5, ConveyorType.SIMPLE));
        } catch (IllegalArgumentException exception) {
            exceptionIsThrown = true;
        }

        Assert.assertTrue(exceptionIsThrown);
        exceptionIsThrown = false;

        // Negative case Length B
        try {
            new FactoryManager(new FactoryConfig(Collections.emptyList(), 5, -5, ConveyorType.SIMPLE));
        } catch (IllegalArgumentException exception) {
            exceptionIsThrown = true;
        }

        Assert.assertTrue(exceptionIsThrown);
        exceptionIsThrown = false;


        // Negative case null variables
        try {
            new FactoryManager(new FactoryConfig(null, 5, 5, ConveyorType.SIMPLE));
        } catch (IllegalArgumentException exception) {
            exceptionIsThrown = true;
        }

        Assert.assertTrue(exceptionIsThrown);
        exceptionIsThrown = false;


        // Negative case null variables
        try {
            List<IntersectionPoint> crossingIndices = Arrays.asList(new IntersectionPoint(2, 2), null);
            new FactoryManager(new FactoryConfig(crossingIndices, 5, 5, ConveyorType.SIMPLE));
        } catch (IllegalArgumentException exception) {
            exceptionIsThrown = true;
        }

        Assert.assertTrue(exceptionIsThrown);
        exceptionIsThrown = false;

        // Negative case negative intersection values
        try {
            List<IntersectionPoint> crossingIndices = Arrays.asList(new IntersectionPoint(-2, 2), null);
            new FactoryManager(new FactoryConfig(crossingIndices, 5, 5, ConveyorType.SIMPLE));
        } catch (IllegalArgumentException exception) {
            exceptionIsThrown = true;
        }

        Assert.assertTrue(exceptionIsThrown);
        exceptionIsThrown = false;

        // Negative case negative intersection values
        try {
            List<IntersectionPoint> crossingIndices = Arrays.asList(new IntersectionPoint(2, -2), null);
            new FactoryManager(new FactoryConfig(crossingIndices, 5, 5, ConveyorType.SIMPLE));
        } catch (IllegalArgumentException exception) {
            exceptionIsThrown = true;
        }

        Assert.assertTrue(exceptionIsThrown);

        //todo: отрефакторить этот тест, слишкмо много копипасты
    }

}
