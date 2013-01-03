/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ruffy
 */
public class MovingObjectTest {
    
    public MovingObjectTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAccelerate() {
    }

    @Test
    public void testTurn() {
    }

    @Test
    public void testSetDirectionToTurnAngle() {
    }

    @Test
    public void testPaint() {
    }

    @Test
    public void testMove() {
    }

    @Test
    public void testWrapPosition() {
    }

    @Test
    public void testShapeContainsPointRectangle() {
        float[] x = {0, 1, 1, 0};
        float[] y = {0, 0, 1, 1};
    
        CustomShape customShape = new CustomShape(0, 0, x, y);
        assertTrue(customShape.shapeContainsPoint(0.1f, 0.1f));
        assertTrue(customShape.shapeContainsPoint(0.5f, 0.5f));
        assertFalse(customShape.shapeContainsPoint(0.5f, 2.1f));
        assertFalse(customShape.shapeContainsPoint(1.1f, 0.1f));
    }
    
        @Test
    public void testShapeContainsPointTriangle() {
        float[] x = {-1, 0, 1};
        float[] y = {0, 1, 0};
    
        CustomShape customShape = new CustomShape(0, 0, x, y);
        assertFalse(customShape.shapeContainsPoint(-1.0f, 0.5f));
        assertTrue(customShape.shapeContainsPoint(0.0f, 0.5f));
        assertFalse(customShape.shapeContainsPoint(1.0f, 0.5f));
    }


    @Test
    public void testLineIntersectsShapeRectangle() {
        float[] x = {0, 0, 1, 1};
        float[] y = {0, 1, 1, 0};
    
        CustomShape customShape = new CustomShape(0, 0, x, y);
        assertTrue(customShape.lineIntersectsShape(-0.5f, 0.5f, 0.5f, 0.5f));
        assertTrue(customShape.lineIntersectsShape(-20.5f, 10.5f, 0.5f, 0.5f));
        assertTrue(customShape.lineIntersectsShape(20.5f, 20.5f, 0.5f, 0.5f));
        
        assertFalse(customShape.lineIntersectsShape(-20.5f, 1.1f, 2.5f, 1.0f));
        assertFalse(customShape.lineIntersectsShape(1.1f, -20.5f, 1.0f, 2.5f));
    }

    @Test
    public void testCircleIntersect() {
    }

    @Test
    public void testIntersects() {
    
        float[] shape1X = {0, 1, 1};
        float[] shape1Y = {0, 1, 0};    
        CustomShape customShape1 = new CustomShape(0, 0, shape1X, shape1Y);

        float[] shape2X = {0,      1, 0.5f};
        float[] shape2Y = {0.1f, 1.1f, 0.5f};    
        CustomShape customShape2 = new CustomShape(0, 0, shape2X, shape2Y);
        
        assertTrue(customShape1.intersects(customShape2));
        assertTrue(customShape2.intersects(customShape1));
    }
    
    public void testIntersects2()
    {
        float[] shape1X = {0, 1, 1};
        float[] shape1Y = {0, 1, 0};    
        CustomShape customShape1 = new CustomShape(0, 0, shape1X, shape1Y);

        float[] shape2X = {0, 1, 0};
        float[] shape2Y = {0.1f, 1.1f, 1.1f};    
        CustomShape customShape2 = new CustomShape(0, 0, shape2X, shape2Y);
        
        assertFalse(customShape1.intersects(customShape2));
        assertFalse(customShape2.intersects(customShape1));
    }
    @Test
    public void testApplyFriction() {
    }

    @Test
    public void testInitializeShapeRadius() {
    }

    @Test
    public void testUpdateRotationMatrix() {
    }

    @Test
    public void testUpdateRotatedShape() {
    }

    public class MovingObjectImpl extends MovingObject {
    }
}
