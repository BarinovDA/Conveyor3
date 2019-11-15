package ru.conveyor.data.conveyor.impl;

import org.apache.commons.collections4.list.TreeList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApacheTreeListConveyor extends AbstractConveyor {

    private TreeList<Integer> queue;

    public ApacheTreeListConveyor() {
        this.queue = new TreeList<>();
        fillConveyorWithZeroes(queue);
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
