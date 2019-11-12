package ru.conveyor.test;

import net.bytebuddy.build.Plugin;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import ru.conveyor.config.ConveyorType;
import ru.conveyor.config.FactoryConfig;
import ru.conveyor.data.IntersectionPoint;
import ru.conveyor.util.PropertiesReader;

import java.util.ArrayList;
import java.util.List;

public class ConveyorConfigTest {
    public void configPropertiesTest() throws Exception {

        FactoryConfig factoryConfig = new FactoryConfig();

        int conveyorAlen = factoryConfig.getConveyorALength();
        int conveyorBlen = factoryConfig.getConveyorBLength();
        ConveyorType conveyorType = factoryConfig.getConveyorType();
        List<IntersectionPoint> intersectionPoints = new ArrayList<>(factoryConfig.getIntersectionPoints());

        //todo: прочитать правильно
        //check length
        MatcherAssert.assertThat(conveyorAlen, CoreMatchers.is(5));
        MatcherAssert.assertThat(conveyorBlen, CoreMatchers.is(6));
        //check type of conveyor
        MatcherAssert.assertThat(conveyorType, CoreMatchers.is(ConveyorType.SIMPLE));

    }
}
