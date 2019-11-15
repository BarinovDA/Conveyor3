package ru.conveyor.data.conveyor.impl;

import java.util.List;


// todo: здесь будет потокобезопасная реализация конвеера,
//  одновременные вызовы pushA/pushB/getStatusA/getStatusB методов должны быть потокобезопасные
//  делать в конце
public class ThreadSafeConveyor extends AbstractConveyor {

    public ThreadSafeConveyor() {

    }

    // todo: реализовать
    @Override
    public int pushValue(int value) {
        return 0;
    }

    @Override
    public List<Integer> getIntersectionValues() {
        return null;
    }

    @Override
    public void updateIntersectionPoints(List<Integer> values) {

    }

    //todo: реализовать
    @Override
    public List<Integer> getStatus() {
        return null;
    }
}
