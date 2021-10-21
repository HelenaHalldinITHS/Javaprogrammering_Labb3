package se.iths.helena.javafx.labb3;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final ObjectProperty<Color> color;
    private final FloatProperty size;
    private final ObjectProperty<Shape> shape;

    List<Shape> shapes = new ArrayList<>();

    public Model() {
        this.color = new SimpleObjectProperty<> ();
        this.size = new SimpleFloatProperty();
        this.shape = new SimpleObjectProperty<> ();
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

    public Shape getShape() {
        return shape.get();
    }

    public ObjectProperty<Shape> shapeProperty() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape.set(shape);
    }
}
