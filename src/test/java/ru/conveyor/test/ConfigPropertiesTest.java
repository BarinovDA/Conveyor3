package ru.conveyor.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.conveyor.config.ConveyorType;
import ru.conveyor.config.FactoryConfig;
import ru.conveyor.data.IntersectionPoint;

import java.util.ArrayList;
import java.util.List;

public class ConfigPropertiesTest {

    @Test
    public void configPropertiesTest() throws Exception {
        FactoryConfig factoryConfig = new FactoryConfig();

        int conveyorAlen = factoryConfig.getConveyorALength();
        int conveyorBlen = factoryConfig.getConveyorBLength();
        ConveyorType conveyorType = factoryConfig.getConveyorType();
        List<IntersectionPoint> intersectionPoints = new ArrayList<>(factoryConfig.getIntersectionPoints());

        //check length
        MatcherAssert.assertThat(conveyorAlen, CoreMatchers.is(5));
        MatcherAssert.assertThat(conveyorBlen, CoreMatchers.is(6));

        //check type of conveyor
        MatcherAssert.assertThat(conveyorType, CoreMatchers.is(ConveyorType.SIMPLE));

        // Check intersection points exist
        Assertions.assertFalse(intersectionPoints.isEmpty());
    }
}
