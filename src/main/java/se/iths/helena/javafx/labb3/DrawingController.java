package se.iths.helena.javafx.labb3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;

public class DrawingController {

    public ResizableCanvas canvas;
    @FXML
    private Button circleButton;
    @FXML
    private Button rectangleButton;
    @FXML
    private Button triangleButton;
    
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Slider sizeSlider;
    @FXML
    private Label sizeLabel;
    @FXML
    private Text sizeText;

    @FXML
    private Button undoButton;
    @FXML
    private Button modeButton;

    
    
    
}