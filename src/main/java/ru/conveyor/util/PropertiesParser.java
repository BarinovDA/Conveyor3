package ru.conveyor.util;

import ru.conveyor.data.IntersectionPoint;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PropertiesParser {

    public static final String SEMICOLON = ";";
    public static final String COMMA = ",";

    public static List<IntersectionPoint> parseIntersectionPoints(String property) {
        return Arrays.stream(property.split(SEMICOLON))
            .map(point -> point.split(COMMA))
            .map(indices -> new IntersectionPoint(Integer.parseInt(indices[0]), Integer.parseInt(indices[1])))
            .collect(Collectors.toList());
    }
}
