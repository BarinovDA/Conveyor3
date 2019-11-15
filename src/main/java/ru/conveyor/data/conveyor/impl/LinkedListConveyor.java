package ru.conveyor.data.conveyor.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LinkedListConveyor extends AbstractConveyor {

    private LinkedList<Integer> queue;

    public LinkedListConveyor() {
        this.queue = new LinkedList<>();
    }

    @Override
    public void fillConveyorWithZeroes() {
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
