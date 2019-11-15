package ru.conveyor.data.conveyor.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrimitiveArrayConveyor extends AbstractConveyor {

    private int[] queue;

    public PrimitiveArrayConveyor() {
    }

    @Override
    public void fillConveyorWithZeroes() {
        this.queue = new int[length];
    }

    @Override
    public int pushValue(int value) {
        int returnValue = queue[queue.length - 1];

        int[] both = Arrays.copyOf(queue, queue.length);
        System.arraycopy(queue, 0, both, 1, queue.length - 1);
        both[0] = value;

        queue = both;

        return returnValue;
    }

    @Override
    public List<Integer> getIntersectionValues() {
        List<Integer> result = new ArrayList<>();

        for (Integer intersectionIndex : intersectionIndices) {
            result.add(queue[intersectionIndex]);
        }

        return result;
    }

    @Override
    public void updateIntersectionPoints(List<Integer> values) {
        if (values.size() != intersectionIndices.size()) {
            throw new IllegalArgumentException("Incoming values size should match config");
        }

        for (int i = 0; i < intersectionIndices.size(); i++) {
            queue[intersectionIndices.get(i)] = values.get(i);
        }
    }

    @Override
    public List<Integer> getStatus() {
        return Arrays.stream(queue).boxed().collect(Collectors.toUnmodifiableList());
    }
}
