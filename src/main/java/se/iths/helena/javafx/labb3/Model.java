package se.iths.helena.javafx.labb3;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.*;


public class Model {
    private final ObjectProperty<Color> selectedColor;
    private final FloatProperty selectedSize;
    private final ObjectProperty<ShapeType> selectedShapeType;
    private final BooleanProperty inSelectMode;

    private final ObservableList<Shape> shapes;
    private final Deque<Shape> history = new ArrayDeque<>();
    private final Map<Shape, Shape> replacements = new HashMap<>();
    private final ServerConnector serverConnector;
    private final SvgWriter svgWriter;

    public Model() {
        this.selectedColor = new SimpleObjectProperty<>();
        this.selectedSize = new SimpleFloatProperty();
        inSelectMode = new SimpleBooleanProperty();
        this.selectedShapeType = new SimpleObjectProperty<>();
        this.shapes = FXCollections.observableArrayList();
        this.serverConnector = new ServerConnector();
        this.svgWriter = new SvgWriter();
    }

    public Model(SvgWriter svgWriter,ServerConnector serverConnector){
        this.selectedColor = new SimpleObjectProperty<>();
        this.selectedSize = new SimpleFloatProperty();
        inSelectMode = new SimpleBooleanProperty();
        this.selectedShapeType = new SimpleObjectProperty<>();
        this.shapes = FXCollections.observableArrayList();

        this.serverConnector = serverConnector;
        this.svgWriter = svgWriter;
    }


    public void setInitialValues(ShapeType shape, Color color, Float size) {
        this.selectedShapeType.setValue(shape);
        this.selectedColor.setValue(color);
        this.selectedSize.setValue(size);
    }

    public ObservableList<Shape> shapesProperty(){
        return shapes;
    }

    public ShapeType getSelectedShapeType() {
        return selectedShapeType.getValue();
    }

    public ObjectProperty<ShapeType> selectedShapeTypeProperty() {
        return selectedShapeType;
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

    public Color getSelectedColor() {
        return selectedColor.getValue();
    }

    public ObjectProperty<Color> selectedColorProperty() {
        return selectedColor;
    }


    public Float getSelectedSize() {
        return selectedSize.getValue();
    }

    public FloatProperty selectedSizeProperty() {
        return selectedSize;
    }

    public void replaceShape(Shape newShape, Shape oldShape) {
        shapes.add(newShape);
        shapes.remove(oldShape);
        history.push(newShape);
        replacements.put(newShape, oldShape);
    }

    public void addShape(Shape newShape) {
        shapes.add(newShape);
        history.push(newShape);
        sendToServer(newShape);
    }

    public void undo() {
        Shape lastAddedShape = history.pop();
        shapes.remove(lastAddedShape);

        if (replacements.containsKey(lastAddedShape)) {
            shapes.add(replacements.get(lastAddedShape));
            replacements.remove(lastAddedShape);
        }
    }

    public Optional<Shape> getShapeByCoordinates(double x, double y) {
        return shapes.stream()
                .filter(shape -> shape.coordinatesInShapesArea(x, y))
                .reduce((first, second) -> second);
    }

    public void connect(){
        serverConnector.connect(shapes);
    }

    public void sendToServer (Shape shape){
       serverConnector.sendToServer(shape);
    }

    public void writeToSvg() {
        svgWriter.save(this);
    }

    public Shape getNewShape(double x, double y) {
        return switch (getSelectedShapeType()){
            case Square -> ShapeFactory.getSquare(getSelectedColor(),getSelectedSize(),x,y);
            case Circle -> ShapeFactory.getCircle(getSelectedColor(),getSelectedSize(),x,y);
        };
    }


}
