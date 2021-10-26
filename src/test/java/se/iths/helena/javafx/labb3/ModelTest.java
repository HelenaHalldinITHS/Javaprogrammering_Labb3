package se.iths.helena.javafx.labb3;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class ModelTest {

    @Test
    void usingMethodToSetInitialValuesToModelShouldSetSaidValues(){
        Model model = new Model();

        model.setInitialValues(ShapeType.Square, Color.BLUE,50f);

        assertThat(model.getColor()).isEqualTo(Color.BLUE);
        assertThat(model.getSelectedShapeType()).isEqualTo(ShapeType.Square);
        assertThat(model.getSize()).isEqualTo(50f);
    }

    @Test
    void modelShouldInitiallyNotBeInSelectMode(){
        Model model = new Model();

        assertThat(model.isInSelectMode()).isFalse();
    }

    @Test
    void addingAShapeShouldPutSaidShapeInListOfShapes(){
        Model model = new Model();
        Shape shape = ShapeFactory.getCircle(Color.BLUE, 50f, 30, -50);

        model.addShape(shape);

        assertThat(model.getShapes()).contains(shape);
    }

    @Test
    void replacingAShapeShouldAddNewShapeAndRemoveNewShapeFromListOfShapes() {
        Model model = new Model();
        Shape oldShape = ShapeFactory.getCircle(Color.BLUE, 50f, 30, -50);
        model.addShape(oldShape);
        Shape newShape = ShapeFactory.getCircle(Color.RED, 10f, 30, -30);

        model.replaceShape(newShape, oldShape);

        assertThat(model.getShapes()).contains(newShape).doesNotContain(oldShape);
    }

}