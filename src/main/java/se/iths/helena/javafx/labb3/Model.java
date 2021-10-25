package se.iths.helena.javafx.labb3;

import javafx.beans.property.*;
import javafx.scene.paint.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Model {
    private final ObjectProperty<Color> color;
    private final FloatProperty size;
    private final ObjectProperty<ShapeType> selectedShapeType;
    private List<Shape> shapes = new ArrayList<>();
    private Deque<Shape> lastAddedShapes = new ArrayDeque<>();
    private final BooleanProperty inSelectMode;

    public Model() {
        this.color = new SimpleObjectProperty<>();
        this.size = new SimpleFloatProperty();
        inSelectMode = new SimpleBooleanProperty(false);
        this.selectedShapeType = new SimpleObjectProperty<>(ShapeType.Rectangle);
    }

    public void setInitialValues(ShapeType shape, Color color, Float size){
        this.selectedShapeType.setValue(shape);
        this.color.setValue(color);
        this.size.setValue(size);
    }

    public void setSelectedShapeType(ShapeType selectedShapeType){
        this.selectedShapeType.setValue(selectedShapeType);
    }

    public ShapeType getSelectedShapeType(){
        return this.selectedShapeType.getValue();
    }

    public ObjectProperty<ShapeType> selectedShapeTypeProperty(){
        return this.selectedShapeType;
    }

    public void setInSelectMode(boolean inSelectMode){
        this.inSelectMode.setValue(inSelectMode);
    }

    public boolean isInSelectMode(){
        return inSelectMode.getValue();
    }

    public BooleanProperty inSelectModeProperty(){
        return inSelectMode;
    }


    public List<Shape> getShapes() {
        return shapes;
    }

    public Deque<Shape> getLastAddedShapes() {
        return lastAddedShapes;
    }

    public void setLastAddedShapes(Deque<Shape> lastAddedShapes) {
        this.lastAddedShapes = lastAddedShapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
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
