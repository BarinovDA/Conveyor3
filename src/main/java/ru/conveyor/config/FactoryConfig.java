package ru.conveyor.config;

import ru.conveyor.data.IntersectionPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//todo: сделать новый класс ConveyorType типа enum. В нём перечислить типы конвееров.
// Добавить в конструктор конфига этот класс. В config.properties файле параметр уже есть.
// Выбирать реализацию конвеера для работы исходя из этого параметра.
public class FactoryConfig {

    private List<IntersectionPoint> crossingIndex;
    private int convAlength;
    private int convBlength;
    private ConveyorType conveyorType;

    public ConveyorType getConveyorType() {
        return conveyorType;
    }

    public FactoryConfig(List<IntersectionPoint> intersectionPoints,
                         int lenA, int lenB,
                         ConveyorType conveyorType) throws IllegalArgumentException {

        if (validateParameters(intersectionPoints, lenA, lenB, conveyorType)) {
            crossingIndex = new ArrayList<>();
            crossingIndex.addAll(intersectionPoints); //todo: лист можно сразу в конструкторе Arraylist передавать
            this.convAlength = lenA;
            this.convBlength = lenB;
            this.conveyorType = conveyorType;
        } else {
            throw new IllegalArgumentException(); //todo: надо текст ошибки передавать что именно не так с параметрами
        }
    }

    public List<Integer> getIntersectionIndicesForA() {
        List<Integer> list = new ArrayList<>();

        for (IntersectionPoint intersectionPoint : crossingIndex) {
            Integer intersectionForConveyorA = intersectionPoint.getIntersectionForConveyorA();
            list.add(intersectionForConveyorA);
        }

        return list;
    }

    public List<Integer> getIntersectionIndicesForB() {
        List<Integer> list = new ArrayList<>();

        for (IntersectionPoint intersectionPoint : crossingIndex) {
            Integer intersectionForConveyorA = intersectionPoint.getIntersectionForConveyorB();
            list.add(intersectionForConveyorA);
        }

        return list;
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

    //todo: что за InEteresection? идея зря что ль тебе слова подчеркивает?
    private boolean validateParameters(List<IntersectionPoint> listOfInetersection, int lenA, int lenB, ConveyorType conveyorType) {
        //todo: что за имя переменной obj? назвал бы просто 'o'
        for (IntersectionPoint obj : listOfInetersection) {
            if (obj.getIntersectionForConveyorA() > lenA || obj.getIntersectionForConveyorB() > lenB) {
                return false;
            }
        }
        return true;
    }
}
