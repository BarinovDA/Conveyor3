package ru.conveyor.data;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CrossingIndex {

    /*todo: почему тут всё static? и поля и методы? где конструктор на два инта?
        сам класс должен содержать не лист интов, а два инта. Один объект - одна точка пересечения
        в конфиге соответственно лист таких точек пересечения. Писал всё это в вк уже.
    */
    private static List<Integer> interConvA = new LinkedList<Integer>();
    private static List<Integer> interConvB = new LinkedList<Integer>();

    // todo: называй методы полноценно getIntersectionForConveyorA.
    //  Inter это что? International? Interactive? Почему догадываться надо?
    public static List<Integer> getInterConvA() {
        return Collections.unmodifiableList(interConvA);
    }

    public static List<Integer> getInterConvB() {
        return Collections.unmodifiableList(interConvB);
    }

    //todo: сеттеры этому классу не нужны, поля инициализируются один раз в конфиге, больше не меняются
    public static void setInterConvA(int element) {
        CrossingIndex.interConvA.add(element);
    }

    public static void setInterConvB(int element) {
        CrossingIndex.interConvB.add(element);
    }

    //todo: после переделки будет не нужен
    public static int size() {
        return interConvA.size();
    }
}
