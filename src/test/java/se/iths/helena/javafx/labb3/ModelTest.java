package se.iths.helena.javafx.labb3;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @Test
    void usingMethodToSetInitialValuesToModelShouldSetSaidValues(){
        Model model = new Model();

        model.setInitialValues(ShapeType.Square, Color.BLUE,50f);

        assertEquals(Color.BLUE, model.getColor());
        assertEquals(ShapeType.Square, model.getSelectedShapeType());
        assertEquals(50f, model.getSize());
    }

    @Test
    void modelShouldInitiallyNotBeInSelectMode(){
        Model model = new Model();

        assertFalse(model.isInSelectMode());
    }

    @Test
    void addingAShapeShouldPutSaidShapeInListOfShapes(){
        Model model = new Model();
        Shape shape = ShapeFactory.getCircle(Color.BLUE, 50f, 30, -50);

        model.addShape(shape);

        assertTrue(model.getShapes().contains(shape));
    }

    @Test
    void replacingAShapeShouldAddNewShapeAndRemoveNewShapeFromListOfShapes(){
        Model model = new Model();
        Shape oldShape = ShapeFactory.getCircle(Color.BLUE, 50f, 30, -50);
        model.addShape(oldShape);
        Shape newShape = ShapeFactory.getCircle(Color.RED, 10f, 30, -30);

        model.replaceShape(newShape,oldShape);

        assertTrue(model.getShapes().contains(newShape));
        assertFalse(model.getShapes().contains(oldShape));
    }

}