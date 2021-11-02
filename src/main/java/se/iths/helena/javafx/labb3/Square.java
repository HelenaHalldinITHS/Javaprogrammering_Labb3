package se.iths.helena.javafx.labb3;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape {

    public Square(Color color, double size, double x, double y) {
        super(color, size, x, y);
    }

    @Override
    public boolean coordinatesInShapesArea(double x, double y){
        double halfSize = this.getSize()/2;
        return isBetween(x,getX()-halfSize,getX()+halfSize) &&
                isBetween(y,getY()-halfSize,getY()+halfSize);
    }

    private boolean isBetween(double value, double minValueInclusive, double maxValueInclusive) {
        return value >= minValueInclusive && value <= maxValueInclusive;
    }

    @Override
    public Shape changeLook(Color color, double size) {
        return new Square(color,size,this.getX(),this.getY());
    }

    @Override
    public void draw(GraphicsContext gc) {
        double halfSize = this.getSize()/2;
        gc.setFill(this.getColor());
        gc.fillRect(this.getX()-halfSize, this.getY()-halfSize, this.getSize(), this.getSize());
    }

    @Override
    public String getAsSvg() {
        String color = "#" + getColor().toString().subSequence(2,10);

        return  "<"
                + "rect"
                + " x=\"" + getX() + "\""
                + " y=\"" + getY() + "\""
                + " width=\"" + getSize() + "\""
                + " height=\"" + getSize() + "\""
                + " fill=\"" + color + "\""
                + " />";
    }


}
