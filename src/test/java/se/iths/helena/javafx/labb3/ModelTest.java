package se.iths.helena.javafx.labb3;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ModelTest {

    @Test
    void usingMethodToSetInitialValuesToModelShouldSetSaidValues() {
        Model model = new Model(new DummySvgWriter(), new DummyServerConnector());

        model.setInitialValues(ShapeType.Square, Color.BLUE, 50f);
        assertThat(model.getSelectedColor()).isEqualTo(Color.BLUE);
        assertThat(model.getSelectedShapeType()).isEqualTo(ShapeType.Square);
        assertThat(model.getSelectedSize()).isEqualTo(50f);
    }

    @Test
    void modelShouldInitiallyNotBeInSelectMode() {
        Model model = new Model(new DummySvgWriter(), new DummyServerConnector());

        assertThat(model.isInSelectMode()).isFalse();
    }

    @Test
    void addingAShapeShouldPutSaidShapeInListOfShapes() {
        Model model = new Model(new DummySvgWriter(), new DummyServerConnector());
        Shape shape = ShapeFactory.getCircle(Color.BLUE, 50f, 30, -50);

        model.addShape(shape);

        assertThat(model.getShapes()).contains(shape);
    }

    @Test
    void replacingAShapeShouldAddNewShapeAndRemoveOldShapeFromListOfShapes() {
        Model model = new Model(new DummySvgWriter(), new DummyServerConnector());
        Shape oldShape = ShapeFactory.getCircle(Color.BLUE, 50f, 30, -50);
        model.addShape(oldShape);
        Shape newShape = ShapeFactory.getCircle(Color.RED, 10f, 30, -30);

        model.replaceShape(newShape, oldShape);

        assertThat(model.getShapes()).contains(newShape).doesNotContain(oldShape);
    }


}