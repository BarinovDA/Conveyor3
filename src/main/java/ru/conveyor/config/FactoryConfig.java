package ru.conveyor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import ru.conveyor.data.ConveyorType;
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

    public FactoryConfig() {
        FactoryConfig returnedConfig = null;
        try {
            returnedConfig = PropertiesReader.getConfigFromProperties();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

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
            Integer intersectionForConveyorA = intersectionPoint.getIndexA();
            list.add(intersectionForConveyorA);
        }

        return list;
    }

    public List<Integer> getIntersectionIndicesForB() {
        List<Integer> list = new ArrayList<>();

        for (IntersectionPoint intersectionPoint : intersectionPoints) {
            Integer intersectionForConveyorA = intersectionPoint.getIndexB();
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

        if (lenA <= 0 || lenB <= 0) throw new IllegalArgumentException("Negative length of conveyor");
        if (CollectionUtils.isEmpty(listOfIntersection)) {
            throw new IllegalArgumentException("Invalid parameters");
        }
        for (IntersectionPoint object : listOfIntersection) {
            if (object == null) throw new IllegalArgumentException("Invalid parameters");
            if ((object.getIndexA() > lenA) || (object.getIndexB() > lenB) || (object.getIndexA() < 0) || (object.getIndexB() < 0)) {
                throw new IllegalArgumentException("Invalid parameters");
            }
        }
    }

    public boolean isPrefillConveyors() {
        return prefillConveyors;
    }
}
