package ru.conveyor.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.conveyor.config.FactoryConfig;
import ru.conveyor.data.ConveyorType;
import ru.conveyor.data.IntersectionPoint;
import ru.conveyor.service.FactoryService;
import ru.conveyor.util.PrimeNumberUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ConveyorTest {



    @ParameterizedTest
    @EnumSource(ConveyorType.class)
    @Timeout(value = 1, unit = TimeUnit.MINUTES)
    public void conveyorTest(ConveyorType conveyorType) throws InterruptedException {
        // Prepare factory manager
        List<IntersectionPoint> crossingIndices = new LinkedList<>();

        for (int i = 20; i < 1000; i += 20) {
            crossingIndices.add(new IntersectionPoint(i, i - 5));
        }

        FactoryConfig factoryConfig = new FactoryConfig(crossingIndices, 1000, 1000, conveyorType, true) ;

        FactoryService factoryManager = new FactoryService(factoryConfig);

        // Get Conveyors status
        List<Integer> statusConveyorA = factoryManager.getStatusConveyorA();
        List<Integer> statusConveyorB = factoryManager.getStatusConveyorB();

        // Assert size
        MatcherAssert.assertThat(statusConveyorA.size(), CoreMatchers.is(1000));
        MatcherAssert.assertThat(statusConveyorB.size(), CoreMatchers.is(1000));

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

        if (conveyorType == ConveyorType.THREAD_SAFE) {
            startThreadSafeConveyorTest(factoryManager);
        }
    }

    private void startThreadSafeConveyorTest(FactoryService factoryManager) throws InterruptedException {
        List<Integer> primes = PrimeNumberUtils.generatePrimeNumber();

        Random random = new Random();
        int low = 1;
        int high = 200;

        ExecutorService service = Executors.newFixedThreadPool(10);

        List<Callable<Integer>> callables = new ArrayList<>();

        for (int i = 0; i < 10_000; i++) {

            callables.add(() -> {
                    int returnedValue = factoryManager.pushA(primes.get(random.nextInt(high - low) + low));

                    List<Integer> statusConveyorA = factoryManager.getStatusConveyorA();
                    Assertions.assertTrue(statusConveyorA.contains(returnedValue));

                    return returnedValue;
            });
        }

        List<Future<Integer>> futures = service.invokeAll(callables);


        futures.forEach(future -> {
            try {
                Integer integer = future.get();
                System.out.println("Future pushA only returned value: " + integer);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        callables = new ArrayList<>();

        for (int i = 0; i < 10_000; i++) {

            callables.add(() -> {
                int returnedValue = factoryManager.pushB(primes.get(random.nextInt(high - low) + low));

                List<Integer> statusConveyorB = factoryManager.getStatusConveyorB();
                Assertions.assertTrue(statusConveyorB.contains(returnedValue));

                return returnedValue;
            });
        }

        futures = service.invokeAll(callables);


        futures.forEach(future -> {
            try {
                Integer integer = future.get();
                System.out.println("Future pushB only returned value: " + integer);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

//        for (int i = 0; i < 10_000; i++) {
//
//            callables.add(() -> {
//                if (random.nextBoolean()) {
//                    int returnedValue = factoryManager.pushA(primes.get(random.nextInt(high - low) + low));
//
//                    List<Integer> statusConveyorA = factoryManager.getStatusConveyorA();
//
//                    return returnedValue;
//                } else {
//                    int returnedValue = factoryManager.pushB(primes.get(random.nextInt(high - low) + low));
//
//                    List<Integer> statusConveyorB = factoryManager.getStatusConveyorB();
//
//                    return returnedValue;
//                }
//            });
//        }
//
//        futures = service.invokeAll(callables);
//
//
//        futures.forEach(future -> {
//            try {
//                Integer integer = future.get();
//                System.out.println("Future X returned value: " + integer);
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        });

    }
}
