package ru.conveyor.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.conveyor.config.FactoryConfig;
import ru.conveyor.data.ConveyorType;
import ru.conveyor.data.IntersectionPoint;
import ru.conveyor.service.FactoryService;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ConveyorNegativeTest {

    // Intersection pooin out of length conveyor
    @Test
    public void outOfLengthTest() throws IllegalArgumentException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // Prepare factory manager
            List<IntersectionPoint> crossingIndices = new LinkedList<IntersectionPoint>();
            crossingIndices.add(new IntersectionPoint(3, 4));
            crossingIndices.add(new IntersectionPoint(6, 8));

            new FactoryConfig(crossingIndices, 3, 2, ConveyorType.LINKED_LIST, false);
        });
    }

    // пока не разобрался как писать все негативне тесты в одном методе
    @Test
    public void pushNegativeTest() throws IllegalArgumentException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // Prepare factory manager
            List<IntersectionPoint> crossingIndices = new LinkedList<>();
            crossingIndices.add(new IntersectionPoint(3, 4));
            crossingIndices.add(new IntersectionPoint(6, 8));

            FactoryConfig factoryConfig = new FactoryConfig(crossingIndices, 10, 12, ConveyorType.LINKED_LIST, false);
            FactoryService factoryManager = new FactoryService(factoryConfig);

            //todo: у тебя до строчки pushB(-10) тест никогда не доходит
            factoryManager.pushA(-1);// не уверен то так можно
            factoryManager.pushB(-10);// сразу два в одном
        });
    }

    @Test
    public void pushNotPrimeNumber() throws IllegalArgumentException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // Prepare factory manager
            List<IntersectionPoint> crossingIndices = new LinkedList<IntersectionPoint>();
            crossingIndices.add(new IntersectionPoint(3, 4));
            crossingIndices.add(new IntersectionPoint(6, 8));

            FactoryConfig factoryConfig = new FactoryConfig(crossingIndices, 10, 12, ConveyorType.LINKED_LIST, false);
            FactoryService factoryManager = new FactoryService(factoryConfig);

            //todo: предыдущие подготовительные строки дублируются из теста в тест
            // вынести в отдельный метод prepareFactory() который вернёт конфиг

            factoryManager.pushA(2);
            factoryManager.pushB(6);
        });
    }

    //todo: Не падает, а должен
    @Test
    public void pushIllegalValuesTest() throws IllegalArgumentException {
        boolean exceptionIsThrown = false;

        // Negative case Length A
        try {
            new FactoryService(new FactoryConfig(Collections.emptyList(), -5, 5, ConveyorType.LINKED_LIST, false));
        } catch (IllegalArgumentException exception) {
            exceptionIsThrown = true;
        }

        Assertions.assertTrue(exceptionIsThrown);
        exceptionIsThrown = false;

        // Negative case Length B
        try {
            new FactoryService(new FactoryConfig(Collections.emptyList(), 5, -5, ConveyorType.LINKED_LIST, false));
        } catch (IllegalArgumentException exception) {
            exceptionIsThrown = true;
        }

        Assertions.assertTrue(exceptionIsThrown);
        exceptionIsThrown = false;

        //todo: обрати внимание! строка 97

        // Negative case null variables
        try {
            new FactoryService(new FactoryConfig(Arrays.asList(null), 5, 5, ConveyorType.LINKED_LIST, false));
        } catch (NullPointerException exception) {
            exceptionIsThrown = true;
        }

        Assertions.assertTrue(exceptionIsThrown);
        exceptionIsThrown = false;


        // Negative case null variables
        try {
            List<IntersectionPoint> crossingIndices = Arrays.asList(new IntersectionPoint(2, 2), null);
            new FactoryService(new FactoryConfig(crossingIndices, 5, 5, ConveyorType.LINKED_LIST, false));
        } catch (IllegalArgumentException exception) {
            exceptionIsThrown = true;
        }

        Assertions.assertTrue(exceptionIsThrown);
        exceptionIsThrown = false;

        // Negative case negative intersection values
        try {
            List<IntersectionPoint> crossingIndices = Arrays.asList(new IntersectionPoint(-2, 2), null);
            new FactoryService(new FactoryConfig(crossingIndices, 5, 5, ConveyorType.LINKED_LIST, false));
        } catch (IllegalArgumentException exception) {
            exceptionIsThrown = true;
        }

        Assertions.assertTrue(exceptionIsThrown);
        exceptionIsThrown = false;

        // Negative case negative intersection values
        try {
            List<IntersectionPoint> crossingIndices = Arrays.asList(new IntersectionPoint(2, -2), null);
            new FactoryService(new FactoryConfig(crossingIndices, 5, 5, ConveyorType.LINKED_LIST, false));
        } catch (IllegalArgumentException exception) {
            exceptionIsThrown = true;
        }

        Assertions.assertTrue(exceptionIsThrown);

        //todo: отрефакторить этот тест, слишкмо много копипасты
    }

}
