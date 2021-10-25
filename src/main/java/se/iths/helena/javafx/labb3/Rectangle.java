package se.iths.helena.javafx.labb3;

import javafx.scene.canvas.GraphicsContext;
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
        return isBetween(x,getX(),getX()+getSize()) &&
                isBetween(y,getY(),getY()+getSize()*ratio);
    }

    private boolean isBetween(double value, double minValueInclusive, double maxValueInclusive) {
        return value >= minValueInclusive && value <= maxValueInclusive;
    }

    @Override
    public Shape copyOf(){
        return new Rectangle(this.getColor(),this.getSize(),this.getX(),this.getY());
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(this.getColor());
        gc.fillRect(this.getX(), this.getY(), this.getSize(), this.getSize()*ratio);
    }


}
