package ru.conveyor.config;

public class FactoryConfig {

    private int[][] crossingIndex;

    private int convAlength;
    private int convBlength;

    public FactoryConfig(int[][] arr, int lenA, int lenB) throws IllegalArgumentException {
        if (validateParameters(arr, lenA, lenB)) {
            this.convAlength = lenA;
            this.convBlength = lenB;

            this.crossingIndex = new int[arr.length][arr.length];

            for (int i = 0; i < arr.length; i++) {
                this.crossingIndex[i][0] = arr[i][0];
                this.crossingIndex[i][1] = arr[i][1];
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int[][] getCrossingIndex() {
        return crossingIndex;
    }

    public int getlengthOfCrossing() {
        return crossingIndex.length;
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
