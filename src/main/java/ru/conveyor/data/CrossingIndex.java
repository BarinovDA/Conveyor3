package ru.conveyor.data;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CrossingIndex {

    private static List<Integer> interConvA = new LinkedList<Integer>();
    private static List<Integer> interConvB = new LinkedList<Integer>();

    public static List<Integer> getInterConvA() {
        return Collections.unmodifiableList(interConvA);
    }

    public static List<Integer> getInterConvB() {
        return Collections.unmodifiableList(interConvB);
    }

    public static void setInterConvA(int element) {
        CrossingIndex.interConvA.add(element);
    }

    public static void setInterConvB(int element) {
        CrossingIndex.interConvB.add(element);
    }

    public static int size() {
        return interConvA.size();
    }
}
