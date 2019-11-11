package ru.conveyor.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// todo: реализовать (как того требует задание) другой вариант работы конвеера
//  проблема с уже реализованным конвееров в том, что он использует LinkedList
//  у LinkedList O(1) вставка и удаление. То есть запись в голову и удаление из хвоста происходит за 2 операции
//  но вот поиск и замена значений по точкам пересечения O(n) * на количество пересечений.
//  Доступа по индексу у LinkedList же нет. Поэтому это очень медленная реализация,
//  если точек много и они в конце совсем неэффективно.
//  у ArrayList быстрый поиск по индексу, но вставка в начало очень медленная,
//  идёт проход по всей коллекции гарантированно. Но даже он возможно будет быстрее LinkedList..

// todo: Здесь нужно воспользовать другой структурой данных (не LinkedList, не ArrayList),
//  более оптимальной с точки зрения поиска элементов по индексу и быстрой вставкой в голову и в хвост.
public class ComplexConveyor implements Conveyor {

    private List<Integer> queue;
    private List<Integer> intersectionIndices;

    public ComplexConveyor(int length, List<Integer> intersectionIndices) {
        this.queue = new ArrayList<>(length);
        this.intersectionIndices = new ArrayList<>(intersectionIndices);

        for (int i = 0; i < length; i++) {
            queue.add(0);
        }
    }

    @Override
    public int pushValue(int value) {
        int result = queue.get(queue.size() - 1);

        for (int i = queue.size() - 1; i > 0; i--) {
            queue.set(i, queue.get(i - 1));
        }

        return result;
    }

    @Override
    public List<Integer> getIntersectionValues() {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < intersectionIndices.size(); i++) {
            result.add(queue.get(intersectionIndices.get(i)));
        }
        return result;
    }

    @Override
    public void updateIntersectionPoints(List<Integer> values) {
        if (values.size() != intersectionIndices.size()) {
            throw new IllegalArgumentException("Incoming values size should match config");
        }

        for (int i = 0; i < intersectionIndices.size(); i++) {
            queue.set(intersectionIndices.get(i), values.get(i));
        }
    }

    @Override
    public List<Integer> getStatus() {
        return Collections.unmodifiableList(queue);
    }
}
