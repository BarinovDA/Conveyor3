package ru.conveyor.util;


import ru.conveyor.config.ConveyorType;
import ru.conveyor.config.FactoryConfig;
import ru.conveyor.data.IntersectionPoint;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

// реализовать класс который будет парсить конфиг из файла /resources/config.properties
public class PropertiesReader {

    public static FactoryConfig getConfigFromProperties() throws Exception{

        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("./../config.properties");
        properties.load(fileInputStream);

        int conveyorALength = Integer.valueOf(properties.getProperty("conveyors.a.length"));
        int conveyorBLength = Integer.valueOf(properties.getProperty("conveyors.b.length"));
        ConveyorType conveyorType = ConveyorType.valueOf(properties.getProperty("conveyors.type"));

        String semicolinDelimited = ";";
        String[] subStr = properties.getProperty("intersections").split(semicolinDelimited);
        List<IntersectionPoint> intersectionPoints =  new LinkedList<>();
        for (String str : subStr) {
            int a = Integer.valueOf(str.charAt(0));
            int b = Integer.valueOf(str.charAt(2));
            intersectionPoints.add(new IntersectionPoint(a, b));
        }

        FactoryConfig factoryConfig = new FactoryConfig(intersectionPoints, conveyorALength,
                                                           conveyorBLength, conveyorType);
        fileInputStream.close();
        return factoryConfig;
    }
}
