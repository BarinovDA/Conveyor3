package ru.conveyor.data.conveyor.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadSafeConveyor extends AbstractConveyor {
    private final Object lock = new Object();
    private List<Integer> queue;

    public ThreadSafeConveyor() {
        this.queue = Collections.synchronizedList(new ArrayList<>(length));
    }

    @Override
    public int pushValue(int value) {
        synchronized (lock) {
            queue.add(0, value);
            return queue.remove(queue.size() - 1);
        }
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
        synchronized (lock) {
            if (values.size() != intersectionIndices.size()) {
                throw new IllegalArgumentException("Incoming values size should match config");
            }

            for (int i = 0; i < intersectionIndices.size(); i++) {
                queue.set(intersectionIndices.get(i), values.get(i));
            }
        }
    }

    //todo: реализовать
    @Override
    public List<Integer> getStatus() {
        return Collections.unmodifiableList(queue);
    }
}
