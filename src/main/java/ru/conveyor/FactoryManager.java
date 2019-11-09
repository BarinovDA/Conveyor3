package ru.conveyor;

import ru.conveyor.config.FactoryConfig;
import ru.conveyor.data.SimpleConveyor;
import ru.conveyor.util.PrimeNumberUtils;

import java.util.Collections;
import java.util.List;

public final class FactoryManager {

    private final FactoryConfig config;

    private final SimpleConveyor conveyorA;
    private final SimpleConveyor conveyorB;

    private List<Integer> primeNumbers;

    //todo: перегрузить конструктор, во втором случае не принимать аргументов.
    // Если аргументы не были переданы использовать класс PropertiesReader и читать конфиг из файла
    public FactoryManager(FactoryConfig config) {
        this.config = config;
        this.conveyorA = new SimpleConveyor(config.getConvAlength());
        this.conveyorB = new SimpleConveyor(config.getConvBlength());
    }

    public void startFactory() {
        primeNumbers = PrimeNumberUtils.generatePrimeNumber();
        fillConveyor(conveyorA);
        fillConveyor(conveyorB);
    }

    //todo: метод должен реализовать следующее (согласно требованиям)
    // 3.Получение состояния всей системы: очередей, точек пересечения.
    //
    public Object getFactoryStatus() {
        return null; //todo: добавить отдельный класс представление, чтоб в нем был конфиг + оба конвеера
    }

    //todo: приватные методы должны идти по порядку в классе после публичных
    private void fillConveyor(SimpleConveyor conveyor) {
        for (int i = 0; i < conveyor.length; i++) {
            //todo: ниже бесполезный коммент там и так понятно что получается рандомное число от 1 до 100
            // коммент нужен не что там, а зачем это там и какую нагрузку несёт.
            // Например, "Getting random prime number from pre-filled collection size of 100'
            // названия переменных меньше чем из 3 букв не принимаются по всем конвенциям

            // случайное число от 1 до 100
            int x = (int) (Math.random() * 100);

            //todo: доступ к филдам должны идти через геттеры
            conveyor.list.add(primeNumbers.get(x));
        }
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

    // todo: вся эта логика должна быть внутри класса конвеера
    private int pushConveyor(int num, SimpleConveyor conveyorToPush, SimpleConveyor conveyorToUp) {
        int numForReturn = conveyorToPush.get(conveyorToPush.size() - 1);
        conveyorToPush.add(num);
        //crossing
        int convLength = config.getLengthOfCrossing();
        if (conveyorToPush == conveyorA) {
            for (int i = 0; i < convLength; i++) {
                conveyorToUp.set(config.getIntersectionB(i) - 1, conveyorToPush.get(config.getIntersectionA(i) - 1));
            }
        } else {
            for (int i = 0; i < convLength; i++) {
                conveyorToUp.set(config.getIntersectionA(i) - 1, conveyorToPush.get(config.getIntersectionB(i) - 1));
            }
        }

        //last crossing
        conveyorToPush.removeLast();
        conveyorToUp.set(conveyorToUp.length - 1, conveyorToPush.get(conveyorToPush.length - 1));

        return numForReturn;
    }

}
