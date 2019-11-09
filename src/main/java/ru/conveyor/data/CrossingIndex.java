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

    //todo: опять сократил имена переменных
    public CrossingIndex(int intSectionA, int intSectionB) throws RuntimeException {
        if (intSectionA > 0 && intSectionB > 0) {
            interSectionA = intSectionA;
            interSectionB = intSectionB;
        } else {
            //todo: поменять на IllegalArgumentException
            throw new RuntimeException("You can make only positive intersection point");
        }
    }
}
