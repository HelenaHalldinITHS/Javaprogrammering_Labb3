package se.iths.helena.javafx.labb3;

import javafx.beans.property.*;
import javafx.scene.paint.Color;
import java.util.*;

public class Model {
    private final ObjectProperty<Color> color;
    private final FloatProperty size;
    private final ObjectProperty<ShapeType> selectedShapeType;
    private final BooleanProperty inSelectMode;

    private final List<Shape> shapes = new ArrayList<>();
    private final Deque<Shape> history = new ArrayDeque<>();
    private final Map<Shape, Shape> replacements = new HashMap<>();


    public Model() {
        this.color = new SimpleObjectProperty<>();
        this.size = new SimpleFloatProperty();
        inSelectMode = new SimpleBooleanProperty();
        this.selectedShapeType = new SimpleObjectProperty<>();
    }

    public void setInitialValues(ShapeType shape, Color color, Float size) {
        this.selectedShapeType.setValue(shape);
        this.color.setValue(color);
        this.size.setValue(size);
    }

    public void setSelectedShapeType(ShapeType selectedShapeType) {
        this.selectedShapeType.setValue(selectedShapeType);
    }

    public ShapeType getSelectedShapeType() {
        return selectedShapeType.getValue();
    }

    public ObjectProperty<ShapeType> selectedShapeTypeProperty() {
        return selectedShapeType;
    }

    public void setInSelectMode(boolean inSelectMode) {
        this.inSelectMode.setValue(inSelectMode);
    }

    public boolean isInSelectMode() {
        return inSelectMode.getValue();
    }

    public BooleanProperty inSelectModeProperty() {
        return inSelectMode;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public Color getColor() {
        return color.getValue();
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public void setColor(Color color) {
        this.color.setValue(color);
    }

    public Float getSize() {
        return size.getValue();
    }

    public FloatProperty sizeProperty() {
        return size;
    }

    public void setSize(float size) {
        this.size.setValue(size);
    }

    public void replaceShape(Shape newShape, Shape oldShape) {
        shapes.add(newShape);
        shapes.remove(oldShape);
        history.push(newShape);
        replacements.put(newShape,oldShape);
    }

    public void addShape(Shape newShape) {
        shapes.add(newShape);
        history.push(newShape);
    }

    public void undo() {
        Shape lastAddedShape = history.pop();
        shapes.remove(lastAddedShape);

        if (replacements.containsKey(lastAddedShape)) {
            shapes.add(replacements.get(lastAddedShape));
            replacements.remove(lastAddedShape);
        }
    }

    public Optional<Shape> getShapeByCoordinates(double x,double y) {
        return shapes.stream()
                .filter(shape -> shape.coordinatesInShapesArea(x, y))
                .reduce((first, second) -> second);
    }
}
