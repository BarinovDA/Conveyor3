package ru.conveyor.data;

import java.util.List;


// todo: здесь будет потокобезопасная реализация конвеера,
//  одновременные вызовы pushA/pushB/getStatusA/getStatusB методов должны быть потокобезопасные
//  делать в конце
public class ThreadSafeConveyor implements Conveyor {

    // todo: реализовать
    @Override
    public int pushValue(int value) {
        return 0;
    }

    //todo: реализовать
    @Override
    public List<Integer> getStatus() {
        return null;
    }
}
