package ru.conveyor.config;

import ru.conveyor.data.CrossingIndex;

import java.util.ArrayList;
import java.util.List;

public class FactoryConfig {

    private List<CrossingIndex> crossingIndex;
    private int convAlength;
    private int convBlength;

    //todo: конфиг не должен принимать двумерный массив в конструкторе, должен принимать List<CrossingIndex>
    public FactoryConfig(List<CrossingIndex> listOfInetersection, int lenA, int lenB) throws IllegalArgumentException {
        if (validateParameters(listOfInetersection, lenA, lenB)) {
            crossingIndex = new ArrayList<CrossingIndex>();
            crossingIndex.addAll(listOfInetersection);
            this.convAlength = lenA;
            this.convBlength = lenB;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getIntersectionA(int i) {
        return crossingIndex.get(i).getIntersectionForConveyorA();
    }

    public int getIntersectionB(int i) {
        return crossingIndex.get(i).getIntersectionForConveyorB();
    }

    public int getLengthOfCrossing() {
        return crossingIndex.size();
    }

    public int getConvAlength() {
        return convAlength;
    }

    public int getConvBlength() {
        return convBlength;
    }

    private boolean validateParameters(List<CrossingIndex> listOfInetersection, int lenA, int lenB) {
        for (CrossingIndex obj : listOfInetersection) {
            if (obj.getIntersectionForConveyorA() > lenA || obj.getIntersectionForConveyorB() > lenB) {
                return false;
            }
        }
        return true;
    }
}
