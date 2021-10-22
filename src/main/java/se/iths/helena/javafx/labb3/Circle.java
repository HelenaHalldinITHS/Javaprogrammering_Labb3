package se.iths.helena.javafx.labb3;

import javafx.scene.paint.Color;

public class Circle extends Shape {

    public Circle(Color color, double size, double x, double y) {
        super(color, size, x, y);
    }

    public Circle(){
        super();
    }

     // square around circle.....
    @Override
    public boolean coordinatesInShapesArea(double x, double y) {
        return between(x,getX(),getX()+getSize()) &&
                between(y,getY(),getY()+getSize());
    }


    @Override
    public Shape copyOf(){
        return new Circle(this.getColor(),this.getSize(),this.getX(),this.getY());
    }
}
