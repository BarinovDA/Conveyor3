package ru.conveyor.util;

import ru.conveyor.data.IntersectionPoint;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PropertiesParser {

    public static List<IntersectionPoint> parseIntersectionPoints(String property) {
        String[] points = property.split(";");

        return Arrays.stream(points)
            .map(point -> point.split(","))
            .map(indices -> new IntersectionPoint(Integer.parseInt(indices[0]), Integer.parseInt(indices[1])))
            .collect(Collectors.toList());
    }
}
