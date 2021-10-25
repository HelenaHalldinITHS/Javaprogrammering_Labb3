package se.iths.helena.javafx.labb3;


import javafx.scene.paint.Color;

public class ShapeFactory {
    public static Shape getShape(ShapeType shapeType) {
        return switch (shapeType) {
            case Circle -> new Circle();
            case Square -> new Square();
        };
    }

    public static Shape getShape(ShapeType shapeType, Color color, double size, double x, double y) {
        return switch (shapeType) {
            case Circle -> new Circle(color, size, x, y);
            case Square -> new Square(color, size, x, y);
        };
    }


}