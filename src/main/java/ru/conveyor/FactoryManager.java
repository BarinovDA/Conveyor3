package ru.conveyor;

import ru.conveyor.config.FactoryConfig;
import ru.conveyor.data.Conveyor;
import ru.conveyor.util.PrimeNumberUtils;

import java.util.Collections;
import java.util.List;

public final class FactoryManager {

    private final FactoryConfig config;

    private final Conveyor conveyorA;
    private final Conveyor conveyorB;

    private List<Integer> primeNumbers;

    public FactoryManager(FactoryConfig config) {
        this.config = config;
        this.conveyorA = new Conveyor(config.getConvAlength());
        this.conveyorB = new Conveyor(config.getConvBlength());
    }

    public void startFactory() {
        primeNumbers = PrimeNumberUtils.generatePrimeNumber();
        fillConveyor(conveyorA);
        fillConveyor(conveyorB);
    }

    //todo: не отформатирован код. Нужно crlt+alt+L перед каждым пушем по всем файлам!!!
    private void fillConveyor(Conveyor conveyor) {
        for (int i = 0; i < conveyor.length; i++) {
            int x = (int) (Math.random() * 100); //todo: что такое 100? нужен коммент
            conveyor.list.add(primeNumbers.get(x));
        }
    }

    public int pushA(int value) {
        validateConveyorInput(value);

        return pushConveyor(value, conveyorA, conveyorB);
    }

    public int pushB(int value) {
        validateConveyorInput(value);

        return pushConveyor(value, conveyorB, conveyorA);
    }

    //todo: метод похоже больше нигде не используется
    public List<Integer> getStatus(Conveyor conveyor) {
        return Collections.unmodifiableList(conveyor.list);
    }

    public List<Integer> getStatusConveyorA() {
        return Collections.unmodifiableList(conveyorA.list);
    }

    public List<Integer> getStatusConveyorB() {
        return Collections.unmodifiableList(conveyorB.list);
    }

    private void validateConveyorInput(int value) {
        if (!PrimeNumberUtils.isPrime(value)) {
            throw new IllegalArgumentException(value + " - is not a prime number!");
        }
    }

    private int pushConveyor(int num, Conveyor conveyorToPush, Conveyor conveyorToUp) {
        int numForReturn = conveyorToPush.get(conveyorToPush.size() - 1);
        conveyorToPush.add(num);
        //crossing
        int convLength = config.getlengthOfCrossing();
        if (conveyorToPush == conveyorA) {
            for (int i = 0; i < convLength; i++) {
                conveyorToUp.set(config.getIntersectionB(i) - 1, conveyorToPush.get(config.getIntersectionA(i) - 1));
            }
        } else {
            for (int i = 0; i < convLength; i++) {
                conveyorToUp.set(config.getIntersectionA(i) - 1, conveyorToPush.get(config.getIntersectionB(i) - 1));
            }
        }
        //last crossin
        conveyorToPush.remove(conveyorToPush.length);
        conveyorToUp.set(conveyorToUp.length - 1, conveyorToPush.get(conveyorToPush.length - 1));
        return numForReturn;
    }

}















