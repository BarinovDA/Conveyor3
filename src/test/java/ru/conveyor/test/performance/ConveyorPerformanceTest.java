package ru.conveyor.test.performance;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;
import ru.conveyor.FactoryManager;
import ru.conveyor.config.ConveyorType;
import ru.conveyor.config.FactoryConfig;
import ru.conveyor.data.IntersectionPoint;
import ru.conveyor.util.PrimeNumberUtils;

import java.util.LinkedList;
import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Warmup(iterations = 5, time = 300, timeUnit = MILLISECONDS)
@Measurement(iterations = 5, time = 500, timeUnit = MILLISECONDS)
@BenchmarkMode(Mode.Throughput)
public class ConveyorPerformanceTest {

    @Benchmark
    public void simpleConveyorPerformanceTest() {
        FactoryManager factoryManager = prepareFactory(ConveyorType.SIMPLE);
        startFactoryLoad(factoryManager);
    }


    @Benchmark
    public void complexConveyorPerformanceTest() {
        FactoryManager factoryManager = prepareFactory(ConveyorType.COMPLEX);
        startFactoryLoad(factoryManager);
    }

    private FactoryManager prepareFactory(ConveyorType conveyorType) {
        List<IntersectionPoint> crossingIndices = new LinkedList<>();

        for (int i = 5; i < 100; i += 5) {
            crossingIndices.add(new IntersectionPoint(i, i - 1));
        }

        FactoryConfig factoryConfig = new FactoryConfig(crossingIndices, 100, 100, conveyorType) ;

        FactoryManager factoryManager = new FactoryManager(factoryConfig);

        // Start factory
        factoryManager.startFactory();

        return factoryManager;
    }

    private void startFactoryLoad(FactoryManager factoryManager) {
        List<Integer> primes = PrimeNumberUtils.generatePrimeNumber();

        for (int i = 0; i < 100; i++) {
            factoryManager.pushA(primes.get(i));
            factoryManager.pushB(primes.get(i));
        }
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}