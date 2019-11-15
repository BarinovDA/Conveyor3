package ru.conveyor.data.conveyor.impl;

import ru.conveyor.data.conveyor.Conveyor;

import java.util.Collection;
import java.util.List;

/**
 * Created by Mikhail Kholodkov 
 *         on 15.11.2019
 */
public abstract class AbstractConveyor implements Conveyor {

    protected List<Integer> intersectionIndices;
    protected int length;

    public AbstractConveyor() {
    }

    protected void fillConveyorWithZeroes(Collection<Integer> collection) {
        for (int i = 0; i < length; i++) {
            collection.add(0);
        }
    }

    public List<Integer> getIntersectionIndices() {
        return intersectionIndices;
    }

    public void setIntersectionIndices(List<Integer> intersectionIndices) {
        this.intersectionIndices = intersectionIndices;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
