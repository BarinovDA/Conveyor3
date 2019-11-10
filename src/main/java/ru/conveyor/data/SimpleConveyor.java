package ru.conveyor.data;

import java.util.LinkedList;
import java.util.List;

//todo: тут надо подумать как всё это спрятать (перенести conveyor на package-default уровень),
// public доступ не подходит.
// конвеер это внутренняя кухня для FactoryManager доступа извне быть не должно

public class SimpleConveyor implements Conveyor {

    public int length;
    public LinkedList<Integer> list = new LinkedList<>(); //todo: инициализировать в конструкторе

    public SimpleConveyor(int length) {
        this.length = length;
    }

    public void add(int index) {
        list.addFirst(index);
    }

    public int size() {
        return list.size();
    }

    public void removeLast() {
        list.removeLast();
    }

    public int get(int index) {
        return list.get(index);
    }

    public void set(int index, int elem) {
        list.set(index, elem);
    }

    @Override
    public String toString() {
        return String.valueOf(list);
    }

    // todo: реализовать
    @Override
    public int pushValue(int value) {
        return 0;
    }

    // todo: реализовать

    @Override
    public List<Integer> getIntersectionValues() {
        return null;
    }

    // todo: реализовать
    @Override
    public void updateIntersectionPoints(List<Integer> values) {

    }

    //todo: реализовать
    @Override
    public List<Integer> getStatus() {
        return null;
    }
}
