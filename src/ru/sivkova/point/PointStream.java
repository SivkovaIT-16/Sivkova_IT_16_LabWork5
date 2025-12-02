package ru.sivkova.point;

import ru.sivkova.validator.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import static java.lang.Math.abs;

public class PointStream {
    public static void collectPolyline(List<Point> points) {
        Validator.validateNullList(points);
        List<Point> pointStream = points.stream()
                .sorted(Comparator.comparingDouble(Point::getX))
                .map(PointStream::mapY)
                .distinct()
                .collect(Collectors.toList());
        Polyline polyline = new Polyline(pointStream);
        System.out.println("Точки: " + points + "\n" +
                "Ломанная: " + polyline);
    }

    private static Point mapY(Point point) {
        Double newY = abs(point.getY());
        return new Point(point.getX(), newY);
    }
}
