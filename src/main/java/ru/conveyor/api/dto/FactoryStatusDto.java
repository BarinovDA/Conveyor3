package ru.conveyor.api.dto;

import ru.conveyor.config.FactoryConfig;
import ru.conveyor.data.IntersectionPoint;
import ru.conveyor.service.FactoryService;

import java.util.ArrayList;
import java.util.List;

public class FactoryStatusDto {
    private FactoryConfig factoryConfig;
    private List<IntersectionPoint> intersectionPoints;
    private List<Integer> conveyorA;
    private List<Integer> conveyorB;

    public FactoryStatusDto() throws Exception {
        factoryConfig = new FactoryConfig();
        intersectionPoints = new ArrayList<>(factoryConfig.getIntersectionPoints());

    }
}
