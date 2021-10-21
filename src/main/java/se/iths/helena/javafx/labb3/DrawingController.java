package se.iths.helena.javafx.labb3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class DrawingController {
    Model model;
    
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
    
        //Bindings!!
    }

    public void onTriangleButtonClick(ActionEvent actionEvent) {
    }

    public void onRectangleButtonClick(ActionEvent actionEvent) {
    }

    public void onCircleButtonClick(ActionEvent actionEvent) {
    }

    public void onUndoButtonClicked(ActionEvent actionEvent) {
    }

    public void onModeButtonClicked(ActionEvent actionEvent) {
    }

    public void onCanvasClicked(MouseEvent mouseEvent) {
        var context = canvas.getGraphicsContext2D();
        context.fillOval(mouseEvent.getX(),mouseEvent.getY(),25,25);
    }
}