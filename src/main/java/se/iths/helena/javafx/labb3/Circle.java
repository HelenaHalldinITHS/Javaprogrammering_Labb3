package se.iths.helena.javafx.labb3;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {

    public Circle(Color color, double size, double x, double y) {
        super(color, size, x, y);
    }

    @Override
    public boolean coordinatesInShapesArea(double x, double y) {
        double radie = this.getSize()/2;
        double distance = Math.sqrt(Math.pow(x - getX(),2) + Math.pow(y - getY(),2));
        return distance < radie;
    }

    @Override
    public Shape changeLook(Color color, double size) {
        return new Circle(color,size,this.getX(),this.getY());
    }

    @Override
    public void draw(GraphicsContext gc) {
        double radie = getSize()/2;
        gc.setFill(this.getColor());
        gc.fillOval(this.getX()-radie,this.getY()-radie,this.getSize(),this.getSize());
    }

    @Override
    public String getAsSvg() {
        return "<"
                + "circle"
                + " cx=\"" + getX() + "\""
                + " cy=\"" + getY() + "\""
                + " r=\"" + getSize() / 2 + "\""
                + " fill=\"rgb(" + getColor().getRed()*100 + "%, " + getColor().getGreen()*100 + "%, " + getColor().getBlue()*100 + "%)\""
                + " />";
    }
}
