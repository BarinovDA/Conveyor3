package ru.conveyor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import ru.conveyor.data.IntersectionPoint;
import ru.conveyor.util.PropertiesReader;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FactoryConfig {

    private List<IntersectionPoint> intersectionPoints;

    private int conveyorALength;
    private int conveyorBLength;

    private ConveyorType conveyorType;

    private boolean prefillConveyors;

    @Autowired
    public FactoryConfig(
        @Value(value = "#{T(ru.conveyor.util.PropertiesParser).parseIntersectionPoints('${intersections}')}")
            List<IntersectionPoint> intersectionPoints,
        @Value(value = "${conveyors.a.length}")
            int conveyorALength,
        @Value(value = "${conveyors.b.length}")
            int conveyorBLength,
        @Value(value = "${conveyors.type}")
            ConveyorType conveyorType,
        @Value(value = "${conveyors.prefill}")
            boolean prefillConveyors) throws IllegalArgumentException {

        validateParameters(intersectionPoints, conveyorALength, conveyorBLength, conveyorType);

        this.intersectionPoints = new ArrayList<>(intersectionPoints);
        this.conveyorALength = conveyorALength;
        this.conveyorBLength = conveyorBLength;
        this.conveyorType = conveyorType;
        this.prefillConveyors = prefillConveyors;
    }

    public FactoryConfig() throws Exception {
        FactoryConfig returnedConfig = PropertiesReader.getConfigFromProperties();
        conveyorType = returnedConfig.getConveyorType();
        conveyorALength = returnedConfig.getConveyorALength();
        conveyorBLength = returnedConfig.getConveyorBLength();
        intersectionPoints = new ArrayList<>(returnedConfig.getIntersectionPoints());

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

    public boolean isPrefillConveyors() {
        return prefillConveyors;
    }
}
