package se.iths.helena.javafx.labb3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.ArrayDeque;
import java.util.Deque;

public class DrawingController {
    Model model;
    Deque<Shape> lastAddedShapes = new ArrayDeque<>();
    Boolean normalMode = true;

    @FXML
    private ResizableCanvas canvas;
    @FXML
    private Button circleButton;
    @FXML
    private Button rectangleButton;
    @FXML
    private Button triangleButton;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Text sizeText;
    @FXML
    private Button undoButton;
    @FXML
    private Button modeButton;
    @FXML
    private Slider sizeSlider;

    public DrawingController() {
    }

    public DrawingController(Model model) {
        this.model = model;
    }

    public void initialize() {
        model = new Model();

        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        sizeSlider.valueProperty().bindBidirectional(model.sizeProperty());
    }

    public void onCanvasClicked(MouseEvent mouseEvent) {
        if (normalMode)
            canvasClickedInNormalMode(mouseEvent);
        else
            canvasClickedInSelectMode(mouseEvent);
        draw();
    }

    private void canvasClickedInSelectMode(MouseEvent mouseEvent) {
        var clickedShape = model.shapes.stream()
                .filter(shape -> isClickedOn(mouseEvent,shape))
                .findAny();
        clickedShape.ifPresent(this::setNewShapeFromSelectMode);
    }

    private void setNewShapeFromSelectMode(Shape shape) {
        model.setTypeOfShape(shape.getType());
        shape.setSize(model.getSize()).setColor(model.getColor());
    }

    private boolean isClickedOn(MouseEvent mouseEvent, Shape shape) {
        //Implement!


        // (imagain all was squars:)
        return between(mouseEvent.getX(),shape.getX(),shape.getX()+shape.getSize()) &&
        between(mouseEvent.getY(),shape.getY(),shape.getY()+shape.getSize());
    }

    private boolean between(double variable, double minValueInclusive, double maxValueInclusive) {
        return variable >= minValueInclusive && variable <= maxValueInclusive;
    }

    private void canvasClickedInNormalMode(MouseEvent mouseEvent) {
        Shape newShape = new Shape(model.getColor(), model.getTypeOfShape(), model.getSize(), mouseEvent.getX(), mouseEvent.getY());
        lastAddedShapes.push(newShape);
        model.shapes.add(newShape);
    }

    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (var shape : model.shapes) {
            gc.setFill(shape.getColor());
            switch (shape.getType()) {
                case RECTANGLE -> gc.fillRect(shape.getX(), shape.getY(), shape.getSize(), shape.getSize()*0.7);
                case CIRCLE -> gc.fillOval(shape.getX(), shape.getY(), shape.getSize(), shape.getSize());
            }
        }
    }

    public void onRectangleButtonClick(ActionEvent actionEvent) {
        model.setTypeOfShape(TypeOfShape.RECTANGLE);
    }

    public void onCircleButtonClick(ActionEvent actionEvent) {
        model.setTypeOfShape(TypeOfShape.CIRCLE);
    }

    public void onUndoButtonClicked(ActionEvent actionEvent) {
        model.shapes.remove(lastAddedShapes.pop());
        draw();
    }

    public void onModeButtonClicked(ActionEvent actionEvent) {
        normalMode = !normalMode;
    }
}