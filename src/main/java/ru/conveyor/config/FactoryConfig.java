package ru.conveyor.config;

import ru.conveyor.data.CrossingIndex;

import java.util.ArrayList;
import java.util.List;

//todo: сделать новый класс ConveyorType типа enum. В нём перечислить типы конвееров.
// Добавить в конструктор конфига этот класс. В config.properties файле параметр уже есть.
// Выбирать реализацию конвеера для работы исходя из этого параметра.
public class FactoryConfig {

    private List<CrossingIndex> crossingIndex;
    private int convAlength;
    private int convBlength;

    //todo: конфиг не должен принимать двумерный массив в конструкторе, должен принимать List<CrossingIndex>
    public FactoryConfig(List<CrossingIndex> listOfInetersection, int lenA, int lenB) throws IllegalArgumentException {
        if (validateParameters(listOfInetersection, lenA, lenB)) {
            crossingIndex = new ArrayList<>();
            crossingIndex.addAll(listOfInetersection); //todo: лист можно сразу в конструкторе Arraylist передавать
            this.convAlength = lenA;
            this.convBlength = lenB;
        } else {
            throw new IllegalArgumentException(); //todo: надо текст ошибки передавать что именно не так с параметрами
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

    //todo: что за InEteresection? идея зря что ль тебе слова подчеркивает?
    private boolean validateParameters(List<CrossingIndex> listOfInetersection, int lenA, int lenB) {
        //todo: что за имя переменной obj? назвал бы просто 'o'
        for (CrossingIndex obj : listOfInetersection) {
            if (obj.getIntersectionForConveyorA() > lenA || obj.getIntersectionForConveyorB() > lenB) {
                return false;
            }
        }
        return true;
    }
}
