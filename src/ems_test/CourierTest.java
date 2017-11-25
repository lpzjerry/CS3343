package ems_test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import ems.*;

class CourierTest {
    public ArrayList<StubOrder> OrderList;
    StubPosition pos1 = new StubPosition();
    StubPosition pos2 = new StubPosition();

    class StubPosition extends Position {
    }

    class StubOrder extends Order {
        int Id;
        String ItemName;

        public StubOrder(int id, String itemName, Customer sender, Customer receiver, ArrayList<Position> path) {
            super(id, itemName, sender, receiver, path);
            this.Id = id;
            this.ItemName = itemName;
        }

        public int getId() {
            return this.Id;
        }

        public String getItemName() {
            return this.ItemName;
        }

        public void moveToNextLocation() {
            return;
        }

        public Position currentLocation() {
            return pos2;
        }

        public Position nextLocation() {
            return pos1;
        }

    }

    class StubCustomer extends Customer {
        public StubCustomer(int ID, String Name, String Password) {
            super(ID, Name, Password);
        }

        public void confirmReception() {
            return;
        }
    }

    class StubBranch extends Branch {
        public StubBranch(int id, String name, Position loc) {
            super(id, name, loc);
        }

        public void checkInOrder(Order order) {

            return;
        }

        public ArrayList<Order> checkOutOrders(Courier courier) {
            ArrayList<Order> thingsToSend = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                thingsToSend.add(OrderList.get(i));
            }
            return thingsToSend;
        }

        public Position getLocation() {
            return pos1;
        }

        public void reportFinished(Order o) {
            return;
        }
    }

    private Courier testCourier1;


    @BeforeEach
    void setUp() throws Exception {

        StubCustomer sender = new StubCustomer(100, "Sam", "abc");
        StubCustomer receiver = new StubCustomer(101, "Alice", "ABC");
        ArrayList<Position> PosList = new ArrayList();
        OrderList = new ArrayList();

        PosList.add(pos1);
        PosList.add(pos2);
        StubBranch stubBranch = new StubBranch(12, "ontheway", pos1);
        for (int i = 1; i <= 10; i++) {
            OrderList.add(new StubOrder(i, "A" + i, sender, receiver, PosList));
        }
        testCourier1 = new Courier(1, "Sheila", stubBranch, 5);
    }

    @Test
    void testGetID() {
        assertEquals(1, testCourier1.getID());
    }

    @Test
    void testGetName() {
        assertEquals("Sheila", testCourier1.getName());
    }

    @Test
    void testGetCapacity() {
        assertEquals(5, testCourier1.getCapacity());
    }

    @Test
    void testCollectOrder() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        testCourier1.collectOrder();
        assertEquals("Courier 1 has collected Order: 1 2 3\n", outContent.toString());
    }

    @Test
    void testPopTopOrder1() {//If the orderQueue is empty
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        testCourier1.popTopOrder();
        assertEquals("Courier 1 failed to pop the top order.(The order queue is empty)", outContent.toString());
    }

    @Test
    void testPopTopOrder2() {//If the orderQueue is not empty
        testCourier1.collectOrder();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        testCourier1.popTopOrder();
        assertEquals("Courier 1 pop the top order (ID: 1 )\n", outContent.toString());
    }

    @Test
    void testDeliverAllOrder() {
        testCourier1.collectOrder();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        testCourier1.deliverAllOrder();
        assertEquals("Courier 1 pop the top order (ID: 1 )\n", outContent.toString());
    }

    @Test
    void testGetLocation1() {
        assertEquals(pos1, testCourier1.getLocation());
    }

    @Test
    void testGetLocation2() {
        testCourier1.collectOrder();
        assertEquals(pos2, testCourier1.getLocation());
    }

    @Test
    void testReportUnfinishedTask() {
        testCourier1.collectOrder();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        testCourier1.reportUnfinishedTask();
        assertEquals("Unfinished task #1: A1\nUnfinished task #2: A2\nUnfinished task #3: A3\n", outContent.toString());
    }
}
