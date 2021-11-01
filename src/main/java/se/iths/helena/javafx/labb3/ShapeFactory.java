package se.iths.helena.javafx.labb3;


import javafx.scene.paint.Color;

public class ShapeFactory {

    public static Shape getSquare(Color color, double size, double x, double y){
        return new Square(color, size, x, y);
    }

    public static Shape getCircle(Color color, double size, double x, double y){
        return new Circle(color, size, x, y);
    }

    /*
    OBS!!! EJ KLAR
     */
    public static Shape getShapeFromSvg(String svg){

        double x = 300;
        double y = 300;
        double size = 40;
        Color color = Color.BLUE;
        ShapeType shapeType = getShapeType(svg);

        return switch (shapeType) {
            case Circle -> ShapeFactory.getCircle(color, size, x, y);
            case Square -> ShapeFactory.getSquare(color, size, x, y);
        };
    }

    private static ShapeType getShapeType(String svg) {
       if (svg.contains("circle"))
            return ShapeType.Circle;
       else
           return ShapeType.Square;
    }
}