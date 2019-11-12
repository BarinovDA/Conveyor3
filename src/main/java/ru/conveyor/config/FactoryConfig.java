package ru.conveyor.config;

import org.junit.jupiter.api.Test;
import ru.conveyor.data.IntersectionPoint;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//сделать новый класс ConveyorType типа enum. В нём перечислить типы конвееров.
// Добавить в конструктор конфига этот класс. В config.properties файле параметр уже есть.
// Выбирать реализацию конвеера для работы исходя из этого параметра.
public class FactoryConfig {

    private List<IntersectionPoint> intersectionPoints;

    private int conveyorALength;
    private int conveyorBLength;

    private ConveyorType conveyorType;

    public FactoryConfig(List<IntersectionPoint> intersectionPoints,
                         int conveyorALength, int conveyorBLength,
                         ConveyorType conveyorType) throws IllegalArgumentException {

        validateParameters(intersectionPoints, conveyorALength, conveyorBLength, conveyorType);

        this.intersectionPoints = new ArrayList<>(intersectionPoints);
        this.conveyorALength = conveyorALength;
        this.conveyorBLength = conveyorBLength;
        this.conveyorType = conveyorType;
    }

    public FactoryConfig() throws Exception {
        getPropertiesValue();
    }

    public ConveyorType getConveyorType() {
        return conveyorType;
    }

    public List<IntersectionPoint> getIntersectionPoints() {
        return intersectionPoints;
    }

    public List<Integer> getIntersectionIndicesForA() {
        List<Integer> list = new ArrayList<>();

        for (IntersectionPoint intersectionPoint : intersectionPoints) {
            Integer intersectionForConveyorA = intersectionPoint.getIntersectionForConveyorA();
            list.add(intersectionForConveyorA);
        }

        return list;
    }

    public List<Integer> getIntersectionIndicesForB() {
        List<Integer> list = new ArrayList<>();

        for (IntersectionPoint intersectionPoint : intersectionPoints) {
            Integer intersectionForConveyorA = intersectionPoint.getIntersectionForConveyorB();
            list.add(intersectionForConveyorA);
        }

        return list;
    }

    public int getIntersectionA(int i) {
        return intersectionPoints.get(i).getIntersectionForConveyorA();
    }

    public int getIntersectionB(int i) {
        return intersectionPoints.get(i).getIntersectionForConveyorB();
    }

    public int getLengthOfCrossing() {
        return intersectionPoints.size();
    }

    public int getConveyorALength() {
        return conveyorALength;
    }

    public int getConveyorBLength() {
        return conveyorBLength;
    }

    private void validateParameters(List<IntersectionPoint> listOfIntersection, int lenA, int lenB, ConveyorType conveyorType) {
        for (IntersectionPoint object : listOfIntersection) {
            if (object.getIntersectionForConveyorA() > lenA || object.getIntersectionForConveyorB() > lenB) {
                throw new IllegalArgumentException("Invalid parameters");
            }
        }
    }

    //todo: дописать тест
    private void getPropertiesValue() throws Exception {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("./../config.properties");
        properties.load(fileInputStream);

        conveyorALength = Integer.valueOf(properties.getProperty("conveyors.a.length"));
        conveyorBLength = Integer.valueOf(properties.getProperty("conveyors.b.length"));
        conveyorType = ConveyorType.valueOf(properties.getProperty("conveyors.type"));

        String semicolinDelimited = ";";
        String[] subStr = properties.getProperty("intersections").split(semicolinDelimited);

        for (String str : subStr) {
            int a = Integer.valueOf(str.charAt(0));
            int b = Integer.valueOf(str.charAt(2));
            intersectionPoints.add(new IntersectionPoint(a, b));
        }

        fileInputStream.close();
    }
}
