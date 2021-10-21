package se.iths.helena.javafx.labb3;

import javafx.scene.paint.Color;

public class Shape {
    private Color color;
    private double x;
    private double y;
    private double size;

    public Shape(Color color, double size, double x, double y) {
        this.color = color;
        this.size = size;
        this.x = x;
        this.y = y;
    }

    public Shape(){
    }

    public double getSize() {
        return size;
    }

    public Shape setSize(double size) {
        this.size = size;
        return this;
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


    //default square around shape
    public boolean coordinatesInShapesArea(double x, double y){
        return between(x,getX(),getX()+getSize()) &&
                between(y,getY(),getY()+getSize());
    }

    public Shape copyOf(){
        return new Shape(this.getColor(),this.getSize(),this.getX(),this.getY());
    }

    protected boolean between(double variable, double minValueInclusive, double maxValueInclusive) {
        return variable >= minValueInclusive && variable <= maxValueInclusive;
    }
}
