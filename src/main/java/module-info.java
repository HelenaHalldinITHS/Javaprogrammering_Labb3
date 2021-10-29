module se.iths.helena.javafx.labb3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens se.iths.helena.javafx.labb3 to javafx.fxml;
    exports se.iths.helena.javafx.labb3;
}