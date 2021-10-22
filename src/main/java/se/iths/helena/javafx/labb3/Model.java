package se.iths.helena.javafx.labb3;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Model {
    private final ObjectProperty<Color> color;
    private final FloatProperty size;
    private String currentShape;
    private List<Shape> shapes = new ArrayList<>();
    Deque<Shape> lastAddedShapes = new ArrayDeque<>();

    public Model() {
        this.color = new SimpleObjectProperty<>();
        this.size = new SimpleFloatProperty();
        this.currentShape = "RECTANGLE";
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public String getCurrentShape() {
        return currentShape;
    }

    public void setCurrentShape(String currentShape) {
        this.currentShape = currentShape;
    }

    public Color getColor() {
        return color.get();
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public Float getSize() {
        return size.get();
    }

    public FloatProperty sizeProperty() {
        return size;
    }

    public void setSize(Float size) {
        this.size.set(size);
    }

}
