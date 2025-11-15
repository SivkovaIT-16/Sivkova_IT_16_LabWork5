package ru.sivkova.point;

import ru.sivkova.validator.*;

import java.util.ArrayList;
import java.util.List;

public class Polyline {
    private List<Point> points;

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        Validator.validateNullList(points);
        this.points = points;
    }

    public Polyline() {
        this.points = new ArrayList<>();
    }

    public Polyline(List<Point> points) {
        Validator.validateNullList(points);
        this.points = points;
    }

    public void addPoint(Point point) {
        if (point != null) {
            points.add(point);
        }
    }

    @Override
    public String toString() {
        String result = "Линия [ ";
        for (Point point : points) {
            result += point.toString() + ", ";
        }
        return result;
    }
}
