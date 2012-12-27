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
