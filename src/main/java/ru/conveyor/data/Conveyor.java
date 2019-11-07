package ru.conveyor.data;

import java.util.LinkedList;

//todo: тут надо подумать как всё это спрятать (перенести conveyor на package-default уровень),
// public доступ не подходит.
// конвеер это внутренняя кухня для FactoryManager доступа извне быть не должно

public class Conveyor {
    public int length;
    public LinkedList<Integer> list = new LinkedList<>();

    public Conveyor(int length) {
        this.length = length;
    }

    public void add(int index) {
        list.addFirst(index);
    }

    public int size() {
        return list.size();
    }

    // todo: у LinkedList есть метод removeLast(), надо его заюзать вместо удаления по индексу
    public void remove(int index) {
        list.remove(index);
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
}
