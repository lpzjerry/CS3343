package ems_test;

import ems.Position;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

public class PositionTest {

    private int x_test;
    private int y_test;
    private Position position_test;

    @Before
    public void setUp() throws Exception {
        x_test = 4;
        y_test = 3;
        position_test = new Position(4, 3);
    }

    @After
    public void tearDown() throws Exception {
        position_test = null;
    }

    @Test
    public void test01_getx() {
        assertEquals(x_test, position_test.getX());
    }

    @Test
    public void test02_gety() {
        assertEquals(y_test, position_test.getY());
    }

    @Test
    public void test03_distance01() {
        Position position_tmp = new Position(1, 2);
        int distance = Position.distance(position_tmp, position_test);
        assertEquals(4, distance);
    }

    @Test
    public void test04_distance02() {
        Position position_tmp = new Position(1, 2);
        int distance = position_test.distance(position_tmp);
        assertEquals(4, distance);
    }

    @Test
    public void test05_toString() {
        String tmp = position_test.toString();
        assertEquals("Address: (4, 3).", tmp);
    }


}
