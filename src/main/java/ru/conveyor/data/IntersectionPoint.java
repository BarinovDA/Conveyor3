package ru.conveyor.data;

public class IntersectionPoint {

    private int indexA;
    private int indexB;

    public int getIntersectionForConveyorA() {
        return indexA;
    }

    public int getIntersectionForConveyorB() {
        return indexB;
    }

    public IntersectionPoint(int indexA, int indexB) throws IllegalArgumentException {
        if (indexA > 0 && indexB > 0) {
            this.indexA = indexA;
            this.indexB = indexB;
        } else {
            //поменять на IllegalArgumentException
            throw new IllegalArgumentException("You can make only positive intersection point");
        }
    }
}
