package se.iths.helena.javafx.labb3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DrawingApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DrawingApplication.class.getResource("drawing-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        stage.setTitle("Helenas drawing application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}