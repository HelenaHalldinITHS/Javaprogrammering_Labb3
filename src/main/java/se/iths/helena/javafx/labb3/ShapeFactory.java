package se.iths.helena.javafx.labb3;


import javafx.scene.paint.Color;

public class ShapeFactory {

    public static Shape getSquare(Color color, double size, double x, double y){
        return new Square(color, size, x, y);
    }

    public static Shape getCircle(Color color, double size, double x, double y){
        return new Circle(color, size, x, y);
    }

    public static Shape getShapeFromSvg(String svg){
        if (svg.contains("circle"))
            return getCircleFromSvg(svg);
        else
            return getSquareFromSvg(svg);
    }


    private static Shape getCircleFromSvg(String svg){
        String[] strings = svg.split("\"");
        double x = Double.parseDouble(strings[1]);
        double y = Double.parseDouble(strings[3]);
        double size = Double.parseDouble(strings[5])*2;
        Color color = Color.valueOf(strings[7].substring(1));

        return getCircle(color, size, x, y);
    }

    private static Shape getSquareFromSvg(String svg){
        String[] strings = svg.split("\"");
        double x = Double.parseDouble(strings[1]);
        double y = Double.parseDouble(strings[3]);
        double size = Double.parseDouble(strings[5])*2;
        Color color = Color.valueOf(strings[9].substring(1));

        return getSquare(color, size, x, y);
    }
}