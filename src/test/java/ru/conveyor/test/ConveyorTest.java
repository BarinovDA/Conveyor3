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
        int[][] crossingPoints = {{3, 4}, {6, 8}}; //todo: избавиться от двумерного массива
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

        // InterSection verify
        valueToBeReturned = statusConveyorA.get(statusConveyorA.size() - 1);
        //todo: returnedValue - переменная в которую пишется результат после push, а не то что ты тут написал
        //todo: заведи отдельную для чисел из конвеера B
        returnedValue = statusConveyorB.get(statusConveyorB.size() - 1);

        Assert.assertThat(returnedValue, CoreMatchers.is(valueToBeReturned));

        // InterSection verify in middle
        //todo: переделать после изменений
        for (int i = 0; i < factoryConfig.getlengthOfCrossing(); i++) {

            int intersectionIndexA = factoryConfig.getIntersectionA(i) - 1;
            int intersectionIndexB = factoryConfig.getIntersectionB(i) - 1;

            valueToBeReturned = factoryManager.getStatusConveyorA().get(intersectionIndexA);
            returnedValue = factoryManager.getStatusConveyorB().get(intersectionIndexB);

            Assert.assertThat(returnedValue, CoreMatchers.is(valueToBeReturned));
        }
    }

    //todo: добавить ещё один тест (негативный) в этом классе на попытку pushA отрицательное число/не простое число/null
    //todo: на передачу в конфиг отрицательной длины конвееров
    //todo: на передачу индексов пересечения больше длины конвееров
    //todo: и заассертить что ожидаемо выбрасывается нужный эксепшн

    //todo: добавить ещё один тестовый класс PrimeNumberUtilsTest и проверить его (этого класса) методы
}
