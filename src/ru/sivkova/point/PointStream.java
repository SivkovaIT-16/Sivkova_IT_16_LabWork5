package ru.sivkova.point;

import ru.sivkova.validator.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import static java.lang.Math.abs;

public class PointStream {
    private List<Point> points;
    private Polyline resultPolyline;

    public List<Point> getPoints() {
        return points;
    }

    public Polyline getResultPolyline() {
        return resultPolyline;
    }

    public void setPoints(List<Point> points) {
        Validator.validateNullList(points);
        this.points = points;
        this.resultPolyline = collectPolyline(this.points);
    }

    public PointStream(List<Point> points) {
        Validator.validateNullList(points);
        this.points = points;
        this.resultPolyline = collectPolyline(this.points);
    }

    @Override
    public String toString() {
        return "Точки: " + points.toString() + "\n" +
                "Ломанная: " + resultPolyline.toString();
    }
    private Polyline collectPolyline(List<Point> points) {
        List<Point> pointStream = points.stream()
                .sorted(Comparator.comparingDouble(Point::getX))
                .map(PointStream::mapY)
                .distinct()
                .collect(Collectors.toList());
        return new Polyline(pointStream);
    }

    private static Point mapY(Point point) {
        Double newY = abs(point.getY());
        return new Point(point.getX(), newY);
    }
}
