package ru.conveyor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.conveyor.api.dto.FactoryStatusDto;
import ru.conveyor.config.FactoryConfig;
import ru.conveyor.data.ConveyorStrategy;
import ru.conveyor.data.conveyor.Conveyor;
import ru.conveyor.util.PrimeNumberUtils;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public final class FactoryService {

    private final FactoryConfig config;

    private final Conveyor conveyorA;
    private final Conveyor conveyorB;

    @Autowired
    public FactoryService(FactoryConfig config) {
        this.config = config;

        conveyorA = ConveyorStrategy.getConveyorStrategy(config.getConveyorType(), config.getConveyorALength(), config.getIntersectionIndicesForA());
        conveyorB = ConveyorStrategy.getConveyorStrategy(config.getConveyorType(), config.getConveyorBLength(), config.getIntersectionIndicesForB());

        if (config.isPrefillConveyors()) {
            prefillConveyors();
        }
    }

    public FactoryStatusDto getFactoryStatus() {
        return new FactoryStatusDto(config, conveyorA.getStatus(), conveyorB.getStatus());
    }

    public int pushA(int value) throws IllegalArgumentException {
        validateConveyorInput(value);
        return pushConveyor(value, conveyorA, conveyorB);
    }

    public int pushB(int value) throws IllegalArgumentException {
        validateConveyorInput(value);
        return pushConveyor(value, conveyorB, conveyorA);
    }

    public List<Integer> getStatusConveyorA() {
        return Collections.unmodifiableList(conveyorA.getStatus());
    }

    public List<Integer> getStatusConveyorB() {
        return Collections.unmodifiableList(conveyorB.getStatus());
    }

    public FactoryConfig getConfig() {
        return config;
    }

    private void prefillConveyors() {
        List<Integer> primes = PrimeNumberUtils.generatePrimeNumber();

        Random r = new Random();
        int low = 1;
        int high = 200;

        for (int i = 0; i < conveyorA.getStatus().size(); i++) {
            conveyorA.pushValue(primes.get(r.nextInt(high - low) + low));
        }

        for (int i = 0; i < conveyorB.getStatus().size(); i++) {
            conveyorB.pushValue(primes.get(r.nextInt(high - low) + low));
        }
    }

    private void validateConveyorInput(int value) {
        if (!PrimeNumberUtils.isPrime(value)) {
            throw new IllegalArgumentException(value + " - is not a prime number!");
        }
    }

    private int pushConveyor(int num, Conveyor conveyorToPush, Conveyor conveyorToUpdate) {
        int numForReturn = conveyorToPush.pushValue(num);

        conveyorToUpdate.updateIntersectionPoints(conveyorToPush.getIntersectionValues());

        return numForReturn;
    }

}
