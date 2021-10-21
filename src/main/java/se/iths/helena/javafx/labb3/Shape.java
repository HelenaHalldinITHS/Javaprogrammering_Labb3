package se.iths.helena.javafx.labb3;

import javafx.scene.paint.Color;

public class Shape {
    private Color color;
    private double x;
    private double y;
    private double size;
    private TypeOfShape type;

    public Shape(Color color, TypeOfShape type, double size, double x, double y) {
        this.color = color;
        this.size = size;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public Shape setColor(Color color) {
        this.color = color;
        return this;
    }

    public double getX() {
        return x;
    }

    public Shape setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public Shape setY(double y) {
        this.y = y;
        return this;
    }

    public TypeOfShape getType() {
        return type;
    }

    public void setType(TypeOfShape type) {
        this.type = type;
    }
}
