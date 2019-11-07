package Main;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CrossIndex {
    private static List<Integer> crossConvA = new LinkedList<Integer>();
    private static List<Integer> crossConvB = new LinkedList<Integer>();

    public static List<Integer> getCrossConvA() {
        return Collections.unmodifiableList(crossConvA);
    }

    public static List<Integer> getCrossConvB() {
        return Collections.unmodifiableList(crossConvB);
    }

    public static void setCrossConvA(int element) {
        CrossIndex.crossConvA.add(element);
    }

    public static void setCrossConvB(int element) {
        CrossIndex.crossConvB.add(element);
    }
}
