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


    public DrawingController() {}

    public DrawingController(Model model) {
        this.model = model;
    }

    public void initialize() {
        model = new Model();

        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        sizeSlider.valueProperty().bindBidirectional(model.sizeProperty());

        canvas.widthProperty().addListener(observable -> draw());
        canvas.heightProperty().addListener(observable -> draw());

    }

    public void onCanvasClicked(MouseEvent mouseEvent) {
        if (normalMode)
            canvasClickedInNormalMode(mouseEvent);
        else
            canvasClickedInSelectMode(mouseEvent);
        draw();
    }


    private void canvasClickedInSelectMode(MouseEvent mouseEvent) {
        var clickedShape = model.getShapes().stream()
                .filter(shape -> shape.coordinatesInShapesArea(mouseEvent.getX(),mouseEvent.getY()))
                .findAny();
        clickedShape.ifPresent(this::setNewShapeFromSelectMode);
    }


    private void setNewShapeFromSelectMode(Shape shape) {
        shape.setSize(model.getSize()).setColor(model.getColor());
    }


    private void canvasClickedInNormalMode(MouseEvent mouseEvent) {
        Shape newShape = new ShapeFactory().getShape(model.getCurrentShape())
                .setColor(model.getColor())
                .setSize(model.getSize())
                .setX(mouseEvent.getX())
                .setY(mouseEvent.getY()).copyOf();

        lastAddedShapes.push(newShape);
        model.getShapes().add(newShape);
    }

    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (var shape : model.getShapes()) {
            gc.setFill(shape.getColor());
            if (shape.getClass().equals(Rectangle.class)){
                gc.fillRect(shape.getX(), shape.getY(), shape.getSize(), shape.getSize()*0.7);
            } else if (shape.getClass().equals(Circle.class)){
                gc.fillOval(shape.getX(),shape.getY(),shape.getSize(),shape.getSize());
            }
        }
    }

    public void onRectangleButtonClick(ActionEvent actionEvent) {
        model.setCurrentShape("RECTANGLE");
    }

    public void onCircleButtonClick(ActionEvent actionEvent) {
        model.setCurrentShape("CIRCLE");
    }

    public void onUndoButtonClicked(ActionEvent actionEvent) {
        model.getShapes().remove(lastAddedShapes.pop());
        draw();
    }

    public void onModeButtonClicked(ActionEvent actionEvent) {
        normalMode = !normalMode;
    }
}