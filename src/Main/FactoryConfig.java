package Main;

import java.util.Arrays;

public class FactoryConfig {
    private static int[][] crossIndex;
    private static int convAlength;
    private static int convBlength;

    public FactoryConfig(int[][] arr, int lenA, int lenB) throws IllegalArgumentException {
        //int[][] cross = {{3,4},{6,8}};
        if (validateParameters(arr, lenA, lenB)) {
            convAlength = lenA;
            convBlength = lenB;
            crossIndex = new int[arr.length][arr.length];
            for (int i = 0; i < arr.length; i++) {
                crossIndex[i][0] = arr[i][0];
                crossIndex[i][1] = arr[i][1];
            }
        }
        else throw new IllegalArgumentException();
    }

    public static int[][] getCrossIndex() {
        return crossIndex;
    }

    public static int getlengthOfCross() {
        return crossIndex.length;
    }

    private boolean validateParameters(int[][] arr, int lenA, int lenB) {
        for (int[] arrElem : arr) if (arrElem[0] > lenA || arrElem[1] > lenB) return false;
        return true;
    }

    public static int getConvAlength() {
        return convAlength;
    }

    public static int getConvBlength() {
        return convBlength;
    }
}
