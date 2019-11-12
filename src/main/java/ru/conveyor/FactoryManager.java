package ru.conveyor;

import org.apache.commons.math3.primes.Primes;
import org.apache.commons.math3.util.MathUtils;
import ru.conveyor.config.FactoryConfig;
import ru.conveyor.data.ApacheTreeListConveyor;
import ru.conveyor.data.ComplexConveyor;
import ru.conveyor.data.Conveyor;
import ru.conveyor.data.SimpleConveyor;
import ru.conveyor.data.ThreadSafeConveyor;
import ru.conveyor.util.PrimeNumberUtils;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class FactoryManager {

    private final FactoryConfig config;

    private final Conveyor conveyorA;
    private final Conveyor conveyorB;

    private List<Integer> primeNumbers;

    //todo: перегрузить конструктор, во втором случае не принимать аргументов.
    // Если аргументы не были переданы использовать класс PropertiesReader и читать конфиг из файла
    public FactoryManager(FactoryConfig config) {
        this.config = config;

        switch (config.getConveyorType()) {
            case SIMPLE:
                this.conveyorA = new SimpleConveyor(config.getConveyorALength(), config.getIntersectionIndicesForA());
                this.conveyorB = new SimpleConveyor(config.getConveyorBLength(), config.getIntersectionIndicesForB());
                break;
            case COMPLEX:
                this.conveyorA = new ComplexConveyor(config.getConveyorALength(), config.getIntersectionIndicesForA());
                this.conveyorB = new ComplexConveyor(config.getConveyorBLength(), config.getIntersectionIndicesForB());
                break;
            case APACHE_TREE_LIST:
                this.conveyorA = new ApacheTreeListConveyor(config.getConveyorALength(), config.getIntersectionIndicesForA());
                this.conveyorB = new ApacheTreeListConveyor(config.getConveyorBLength(), config.getIntersectionIndicesForB());
                break;
            case THREAD_SAFE:
                this.conveyorA = new ThreadSafeConveyor(config.getConveyorALength());
                this.conveyorB = new ThreadSafeConveyor(config.getConveyorBLength());
                break;
            default: throw new IllegalArgumentException("Unknown conveyor type");
        }
    }

   /* public FactoryManager() {
        //Properties properties = new Properties();

    }*/

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

        // todo: вся эта логика должна быть внутри класса конвеера
        //crossing
//        int convLength = config.getLengthOfCrossing();
//        if (conveyorToPush == conveyorA) {
//            for (int i = 0; i < convLength; i++) {
//                conveyorToUpdate.set(config.getIntersectionB(i) - 1, conveyorToPush.get(config.getIntersectionA(i) - 1));
//            }
//        } else {
//            for (int i = 0; i < convLength; i++) {
//                conveyorToUpdate.set(config.getIntersectionA(i) - 1, conveyorToPush.get(config.getIntersectionB(i) - 1));
//            }
//        }
//
//        //last crossing
//        conveyorToPush.removeLast();
//        conveyorToUpdate.set(conveyorToUpdate.length - 1, conveyorToPush.get(conveyorToPush.length - 1));

        return numForReturn;
    }

    // todo: это надо в тесты унести
    private void fillConveyor(Conveyor conveyor) {
        for (int i = 0; i < conveyor.getStatus().size(); i++) {
            //todo: ниже бесполезный коммент там и так понятно что получается рандомное число от 1 до 100
            // коммент нужен не что там, а зачем это там и какую нагрузку несёт.
            // Например, "Getting random prime number from pre-filled collection size of 100'
            // названия переменных меньше чем из 3 букв не принимаются по всем конвенциям

            // случайное число от 1 до 100
            int randomNumber = (int) (Math.random() * 100);
            //int rnd = Primes.nextPrime(1);

            //todo: доступ к филдам должны идти через геттеры
            conveyor.pushValue(primeNumbers.get(randomNumber));
        }
    }

}
