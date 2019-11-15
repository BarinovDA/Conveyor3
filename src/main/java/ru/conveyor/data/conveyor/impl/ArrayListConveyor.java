package ru.conveyor.data.conveyor.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListConveyor extends AbstractConveyor {

    private List<Integer> queue;

    public ArrayListConveyor() {
        this.queue = new ArrayList<>(length);
    }

    @Override
    public void fillConveyorWithZeroes() {
        for (int i = 0; i < length; i++) {
            queue.add(0);
        }
    }

    @Override
    public int pushValue(int value) {
        queue.add(0, value);
        return queue.remove(queue.size() - 1);
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
