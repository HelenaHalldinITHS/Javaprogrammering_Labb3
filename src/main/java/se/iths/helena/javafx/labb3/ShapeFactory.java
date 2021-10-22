package se.iths.helena.javafx.labb3;



public class ShapeFactory {
    public Shape getShape(ShapeType shapeType) {
        return switch (shapeType) {
            case Circle -> new Circle();
            case Rectangle -> new Rectangle();
        };
    }
}