package ru.conveyor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.conveyor.api.dto.FactoryStatusDto;
import ru.conveyor.config.FactoryConfig;
import ru.conveyor.data.ApacheTreeListConveyor;
import ru.conveyor.data.ArrayListConveyor;
import ru.conveyor.data.Conveyor;
import ru.conveyor.data.LinkedListConveyor;
import ru.conveyor.data.PrimitiveArrayConveyor;
import ru.conveyor.data.ThreadSafeConveyor;
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

        switch (config.getConveyorType()) {
            case LINKED_LIST:
                this.conveyorA = new LinkedListConveyor(config.getConveyorALength(), config.getIntersectionIndicesForA());
                this.conveyorB = new LinkedListConveyor(config.getConveyorBLength(), config.getIntersectionIndicesForB());
                break;
            case ARRAY_LIST:
                this.conveyorA = new ArrayListConveyor(config.getConveyorALength(), config.getIntersectionIndicesForA());
                this.conveyorB = new ArrayListConveyor(config.getConveyorBLength(), config.getIntersectionIndicesForB());
                break;
            case APACHE_TREE_LIST:
                this.conveyorA = new ApacheTreeListConveyor(config.getConveyorALength(), config.getIntersectionIndicesForA());
                this.conveyorB = new ApacheTreeListConveyor(config.getConveyorBLength(), config.getIntersectionIndicesForB());
                break;
            case PRIMITIVE_ARRAY:
                this.conveyorA = new PrimitiveArrayConveyor(config.getConveyorALength(), config.getIntersectionIndicesForA());
                this.conveyorB = new PrimitiveArrayConveyor(config.getConveyorBLength(), config.getIntersectionIndicesForB());
                break;
            case THREAD_SAFE:
                this.conveyorA = new ThreadSafeConveyor(config.getConveyorALength());
                this.conveyorB = new ThreadSafeConveyor(config.getConveyorBLength());
                break;
            default:
                throw new IllegalArgumentException("Unknown conveyor type");
        }

        if (config.isPrefillConveyors()) {
            List<Integer> primes = PrimeNumberUtils.generatePrimeNumber();

            for (int i = 0; i <  conveyorA.getStatus().size(); i++) {
                conveyorA.pushValue(primes.get((int) (Math.random() * 100)));
            }

            for (int i = 0; i < conveyorB.getStatus().size(); i++) {
                conveyorB.pushValue(primes.get((int) (Math.random() * 100)));
            }
        }
    }

    //todo: метод должен реализовать следующее (согласно требованиям)
    // 3.Получение состояния всей системы: очередей, точек пересечения.
    //
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

    // todo: это надо в тесты унести
    private void fillConveyor(Conveyor conveyor) {
        for (int i = 0; i < conveyor.getStatus().size(); i++) {

            // случайное число от 1 до 100
            Random r = new Random();
            int low = 1;
            int high = 100;
            int result = r.nextInt(high-low) + low;

            conveyor.pushValue(primeNumbers.get(result));
        }
    }

}
