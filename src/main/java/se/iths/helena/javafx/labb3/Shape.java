package se.iths.helena.javafx.labb3;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape implements DrawableInJavaFx {
    private final Color color;
    private final double x;
    private final double y;
    private final double size;

    public Shape(Color color, double size, double x, double y) {
        this.color = color;
        this.size = size;
        this.x = x;
        this.y = y;
    }

    public double getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public abstract boolean coordinatesInShapesArea(double x, double y);
    public abstract Shape changeLook(Color color, double size);
    public abstract void draw(GraphicsContext gc);
    public abstract String getAsSvg();
}
