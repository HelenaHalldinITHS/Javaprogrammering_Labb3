package se.iths.helena.javafx.labb3;


import javafx.scene.paint.Color;

public class ShapeFactory {

    public static Shape getSquare(Color color, double size, double x, double y){
        return new Square(color, size, x, y);
    }

    public static Shape getCircle(Color color, double size, double x, double y){
        return new Circle(color, size, x, y);
    }

}