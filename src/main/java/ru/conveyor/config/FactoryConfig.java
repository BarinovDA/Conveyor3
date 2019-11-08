package ru.conveyor.config;

import ru.conveyor.data.CrossingIndex;

public class FactoryConfig {

    private CrossingIndex crossingIndex = new CrossingIndex();
    private int convAlength;
    private int convBlength;

    public FactoryConfig(int[][] arr, int lenA, int lenB) throws IllegalArgumentException {
        if (validateParameters(arr, lenA, lenB)) {
            this.convAlength = lenA;
            this.convBlength = lenB;

            for (int i = 0; i < arr.length; i++) {
                crossingIndex.getInterConvA().add(arr[i][0]);
                crossingIndex.getInterConvB().add(arr[i][1]);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getIntersectionA(int i) {
        return crossingIndex.getInterConvA().get(i);
    }

    public int getIntersectionB(int i) {
        return crossingIndex.getInterConvB().get(i);
    }

    public int getlengthOfCrossing() {
        return crossingIndex.size();
    }

    public int getConvAlength() {
        return convAlength;
    }

    public int getConvBlength() {
        return convBlength;
    }

    private boolean validateParameters(int[][] arr, int lenA, int lenB) {
        for (int[] arrElem : arr) if (arrElem[0] > lenA || arrElem[1] > lenB) return false;
        return true;
    }
}
