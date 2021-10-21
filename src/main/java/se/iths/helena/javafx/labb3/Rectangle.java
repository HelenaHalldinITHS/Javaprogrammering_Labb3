package se.iths.helena.javafx.labb3;

import javafx.scene.paint.Color;

public class Rectangle extends Shape {
    double ratio = 0.7;

    public Rectangle(Color color, double size, double x, double y) {
        super(color, size, x, y);
    }

    public Rectangle(){
        super();
    }

    @Override
    public boolean coordinatesInShapesArea(double x, double y){
        return between(x,getX(),getX()+getSize()) &&
                between(y,getY(),getY()+getSize()*ratio);
    }

    private boolean between(double variable, double minValueInclusive, double maxValueInclusive) {
        return variable >= minValueInclusive && variable <= maxValueInclusive;
    }

    @Override
    public Shape copyOf(){
        return new Rectangle(this.getColor(),this.getSize(),this.getX(),this.getY());
    }
}
