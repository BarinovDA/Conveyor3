package ru.conveyor.data;

import java.util.List;

//todo: интерфейс для конвееров все 3 реализации (2 обычные + 1 потокобезопасная) должны реализовывать этот интерфейс
// во внешнем коде (из FactoryManager) работать с конвеерами только через интерфейс
public interface Conveyor {

    /**
     * Pushes a value in the beginning of conveyor and removes the value from the end.
     *
     * @param value - value to push (add as first)
     * @return - value to be returned (removed last)
     */
    int pushValue(int value);

    /**
     * Returns current conveyor status (numbers within)
     *
     * @return unmodifiable list of current values
     */
    List<Integer> getStatus();
}
