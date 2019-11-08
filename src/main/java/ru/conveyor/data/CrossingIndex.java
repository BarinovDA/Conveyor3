package ru.conveyor.data;

public class CrossingIndex {

    private int interSectionA;
    private int interSectionB;

    public int getIntersectionForConveyorA() {
        return interSectionA;
    }

    public int getIntersectionForConveyorB() {
        return interSectionB;
    }

    public CrossingIndex(int intSectionA, int intSectionB) throws RuntimeException {
        if (intSectionA > 0 && intSectionB > 0) {
            interSectionA = intSectionA;
            interSectionB = intSectionB;
        } else {
            throw new RuntimeException("You can make only positive intersection point");
        }
    }
}
