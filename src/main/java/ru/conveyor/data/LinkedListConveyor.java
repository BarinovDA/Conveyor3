package ru.conveyor.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//todo: тут надо подумать как всё это спрятать (перенести conveyor на package-default уровень),
// public доступ не подходит.
// конвеер это внутренняя кухня для FactoryManager доступа извне быть не должно

public class LinkedListConveyor implements Conveyor {

    private LinkedList<Integer> queue;
    private List<Integer> intersectionIndices;

    public LinkedListConveyor(int length, List<Integer> intersectionIndices) {
        this.queue = new LinkedList<>();
        this.intersectionIndices = new ArrayList<>(intersectionIndices);

        // pre-fill with zeroes
        for (int i = 0; i < length; i++) {
            queue.add(0);
        }
    }

    @Override
    public int pushValue(int value) {
        // add to head
        queue.addFirst(value);

        // delete and return tail
        return queue.removeLast();
    }


    @Override
    public List<Integer> getIntersectionValues() {
        List<Integer> result = new ArrayList<>();

        for (Integer intersectionIndex : intersectionIndices) {
            result.add(queue.get(intersectionIndex));
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
