package se.iths.helena.javafx.labb3;

import javafx.event.ActionEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class DrawingController {
    Model model;

    public Button serverButton;
    public Button saveButton;
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
        colorPicker.valueProperty().bindBidirectional(model.selectedColorProperty());
        sizeSlider.valueProperty().bindBidirectional(model.selectedSizeProperty());
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
        model.getShapeByCoordinates(mouseEvent.getX(),mouseEvent.getY())
                .ifPresent(this::setNewShapeFromSelectMode);
    }

    private void setNewShapeFromSelectMode(Shape shape) {
        Shape newShape = shape.changeLook(model.getSelectedColor(), model.getSelectedSize());
        model.replaceShape(newShape, shape);
    }

    private void canvasClickedInNormalMode(MouseEvent mouseEvent) {
        Shape newShape = getNewShapeBySelectedType(mouseEvent);
        model.addShape(newShape);
        model.sendToServer(newShape);

    }

    private Shape getNewShapeBySelectedType(MouseEvent mouseEvent){
        return switch (model.getSelectedShapeType()){
            case Square -> ShapeFactory.getSquare(model.getSelectedColor(),model.getSelectedSize(),mouseEvent.getX(),mouseEvent.getY());
            case Circle -> ShapeFactory.getCircle(model.getSelectedColor(),model.getSelectedSize(),mouseEvent.getX(),mouseEvent.getY());
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
        model.undo();
        draw();
    }

    public void onSaveButtonClicked(ActionEvent actionEvent) {
        SvgWriter svgWriter = new SvgWriter();
        svgWriter.save(model);
    }

    public void onConnectToServerClicked(ActionEvent actionEvent) {
        model.connect();
    }
}