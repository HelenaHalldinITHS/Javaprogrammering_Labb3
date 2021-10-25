package se.iths.helena.javafx.labb3;

import javafx.event.ActionEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class DrawingController {
    Model model;

    public ComboBox<ShapeType> comboBox;
    public CheckBox selectModeCheckBox;
    public ResizableCanvas canvas;
    public ColorPicker colorPicker;
    public Text sizeText;
    public Button undoButton;
    public Slider sizeSlider;


    public DrawingController() {
    }

    public DrawingController(Model model) {
        this.model = model;
    }

    public void initialize() {
        model = new Model();
        model.setInitialValues(ShapeType.Square, Color.ROSYBROWN, 100f);

        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        sizeSlider.valueProperty().bindBidirectional(model.sizeProperty());
        comboBox.valueProperty().bindBidirectional(model.selectedShapeTypeProperty());
        selectModeCheckBox.selectedProperty().bindBidirectional(model.inSelectModeProperty());

        canvas.widthProperty().addListener(observable -> draw());
        canvas.heightProperty().addListener(observable -> draw());

    }

    public void onCanvasClicked(MouseEvent mouseEvent) {
        if (!model.isInSelectMode())
            canvasClickedInNormalMode(mouseEvent);
        else
            canvasClickedInSelectMode(mouseEvent);
        draw();
    }


    private void canvasClickedInSelectMode(MouseEvent mouseEvent) {
        model.getShapes().stream()
                .filter(shape -> shape.coordinatesInShapesArea(mouseEvent.getX(), mouseEvent.getY()))
                .reduce((first, second) -> second)
                .ifPresent(this::setNewShapeFromSelectMode);
    }


    private void setNewShapeFromSelectMode(Shape shape) {
        Shape newShape = shape.changeLook(model.getColor(), model.getSize());
        model.getShapes().add(newShape);
        model.getShapes().remove(shape);
        model.getLastAddedShapes().push(newShape);
        model.getReplacements().put(newShape,shape);
    }

    private void canvasClickedInNormalMode(MouseEvent mouseEvent) {
        Shape newShape = getNewShape(mouseEvent);
        model.getLastAddedShapes().push(newShape);
        model.getShapes().add(newShape);
    }

    private Shape getNewShape(MouseEvent mouseEvent){
        return switch (model.getSelectedShapeType()){
            case Square -> ShapeFactory.getSquare(model.getColor(),model.getSize(),mouseEvent.getX(),mouseEvent.getY());
            case Circle -> ShapeFactory.getCircle(model.getColor(),model.getSize(),mouseEvent.getX(),mouseEvent.getY());
        };
    }

    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (var shape : model.getShapes()) {
            shape.draw(gc);
        }
    }

    public void onUndoButtonClicked(ActionEvent actionEvent) {
        Shape lastAddedShape = model.getLastAddedShapes().pop();

        model.getShapes().remove(lastAddedShape);
        if (model.getReplacements().containsKey(lastAddedShape)){
            model.getShapes().add(model.getReplacements().get(lastAddedShape));
            model.getReplacements().remove(lastAddedShape);
        }
        draw();
    }

}