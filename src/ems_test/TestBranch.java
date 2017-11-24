package ems_test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ems.Branch;
import ems.Courier;
import ems.Customer;
import ems.Order;
import ems.Position;

public class TestBranch {

    // dummy position class :
    class PositionStub extends Position {

        public int distance(Position p) {
            return 10;
        }

        public int getX() {
            return 3;
        }

        public int getY() {
            return 4;
        }
    }


//	TODO 1 added by patrick 2017-11-17
//  plz remove setUp() if you don't add any contains inside this method
//  The actually usage of setUp() is actually let you define testing environment
//  Therefore, I think you'd better move those Postion and cus_send... object creatation step
//  into setUp()

//    TODO 2 added by patrick 2017-11-17
//    you'd better change testcase naming convention from testGetName()
//    into sth like this test01_getName(), this gives each test case their own ID and description
//    more clearly

    //    TODO 3 added by patrick 2017-11-17
//    line 115-119 and line 184-188 are duplicated. plz inspect this.
    private PositionStub posStub = null;
    private Customer cus_send = null;
    private Customer cus_receive = null;

    @Before
    public void setUp() throws Exception {
        posStub = new PositionStub();
        cus_send = new Customer(1, "testSend", "123456");
        cus_receive = new Customer(2, "testReceive", "654321");

    }

    @Test
    public void test01_GetId() {
        Branch brc = new Branch(1, "testBranch", posStub);
        assertEquals(1, brc.getId());
    }

    @Test
    public void test01_GetName() {
        Branch brc = new Branch(1, "testBranch", posStub);
        assertEquals("testBranch", brc.getName());
    }

    @Test
    public void test01_GetOrder() {
        Branch brc = new Branch(1, "testBranch", posStub);
        ArrayList<Position> testPath = new ArrayList<Position>();
        testPath.add(posStub);
        Order order1 = new Order(1, "testOrder1", cus_send, cus_receive, testPath);
        Order order2 = new Order(2, "testOrder2", cus_send, cus_receive, testPath);
        Order order3 = new Order(3, "testOrder3", cus_send, cus_receive, testPath);
        brc.checkInOrder(order1);
        brc.checkInOrder(order2);
        brc.checkInOrder(order3);
        Order retOrder = brc.getOrder(2);
        assertEquals(2, retOrder.getId());
    }

    @Test
    public void test02_GetOrder() {
        Branch brc = new Branch(1, "testBranch", posStub);
        ArrayList<Position> testPath = new ArrayList<Position>();
        testPath.add(posStub);
        Order order1 = new Order(1, "testOrder1", cus_send, cus_receive, testPath);
        Order order2 = new Order(2, "testOrder2", cus_send, cus_receive, testPath);
        Order order3 = new Order(3, "testOrder3", cus_send, cus_receive, testPath);
        brc.checkInOrder(order1);
        brc.checkInOrder(order2);
        brc.checkInOrder(order3);
        Order retOrder = brc.getOrder(10);
        assertEquals(null, retOrder);
    }

    @Test
    public void test03_GetOrder() {
        Branch brc = new Branch(1, "testBranch", posStub);
        ArrayList<Position> testPath = new ArrayList<Position>();
        Courier cur = new Courier(1, "testCourier", brc, 10);
        testPath.add(posStub);
        Order order1 = new Order(1, "testOrder1", cus_send, cus_receive, testPath);
        Order order2 = new Order(2, "testOrder2", cus_send, cus_receive, testPath);
        Order order3 = new Order(3, "testOrder3", cus_send, cus_receive, testPath);
        brc.checkInOrder(order1);
        brc.checkInOrder(order2);
        brc.checkInOrder(order3);
        brc.checkOutOrders(cur);
        Order retOrder = brc.getOrder(2);
        assertEquals(2, retOrder.getId());
    }

    @Test
    public void test01_GetLocation() {
        Branch brc = new Branch(1, "testBranch", posStub);
        Position pos = brc.getLocation();
        Boolean ok = (pos.getX() == 3) && (pos.getY() == 4);
        assertEquals(true, ok);
    }

    @Test
    public void test01_GetDistance() {
        Branch brc1 = new Branch(1, "testBranch1", posStub);
        Branch brc2 = new Branch(2, "testBranch2", posStub);
        int res = brc1.getDistance(brc2);
        assertEquals(10, res);
    }

    @Test
    public void test01_ToString() {
        Branch brc = new Branch(1, "testBranch", posStub);
        String desiredRes = "Name: testBranch, Position: (3, 4)";
        assertEquals(desiredRes, brc.toString());
    }

    @Test
    public void test01_CheckInOrder() {

        Branch brc = new Branch(1, "testBranch", posStub);
        ArrayList<Position> testPath = new ArrayList<Position>();
        testPath.add(posStub);
        Order order1 = new Order(1, "testOrder1", cus_send, cus_receive, testPath);
        Order order2 = new Order(2, "testOrder2", cus_send, cus_receive, testPath);
        Order order3 = new Order(3, "testOrder3", cus_send, cus_receive, testPath);
        brc.checkInOrder(order1);
        brc.checkInOrder(order2);
        brc.checkInOrder(order3);
        Boolean allIn = true;
        for (int i = 1; i <= 3; i++) {
            if (brc.getOrder(i) == null)
                allIn = false;
        }
        assertEquals(true, allIn);
    }

    @Test
    public void test01_CheckOutOrders() {
        Branch brc = new Branch(1, "testBranch", posStub);
        ArrayList<Position> testPath = new ArrayList<Position>();
        Courier cur = new Courier(1, "testCourier", brc, 10);
        testPath.add(posStub);
        Order order1 = new Order(1, "testOrder1", cus_send, cus_receive, testPath);
        Order order2 = new Order(2, "testOrder2", cus_send, cus_receive, testPath);
        Order order3 = new Order(3, "testOrder3", cus_send, cus_receive, testPath);
        brc.checkInOrder(order1);
        brc.checkInOrder(order2);
        brc.checkInOrder(order3);
        ArrayList<Order> checkedOut = brc.checkOutOrders(cur);
        assertEquals(3, checkedOut.size());
    }

    @Test
    public void test01_Arrive() {
        Branch brc = new Branch(1, "testBranch", posStub);
        Courier cur = new Courier(1, "testCourier", brc, 10);
        brc.arrive(cur);
        Courier getted = brc.getMan(cur.getID());
        assertEquals(1, getted.getID());
    }

    @Test
    public void test01_ReportFinished() {
        Branch brc = new Branch(1, "testBranch", posStub);
        ArrayList<Position> testPath = new ArrayList<Position>();
        testPath.add(posStub);
        Courier cur = new Courier(1, "testCourier", brc, 10);
        Order order1 = new Order(1, "testOrder1", cus_send, cus_receive, testPath);
        Order order2 = new Order(2, "testOrder2", cus_send, cus_receive, testPath);
        Order order3 = new Order(3, "testOrder3", cus_send, cus_receive, testPath);
        brc.checkInOrder(order1);
        brc.checkInOrder(order2);
        brc.checkInOrder(order3);
        ArrayList<Order> checkedOut = brc.checkOutOrders(cur);
        for (Order o : checkedOut) {
            brc.reportFinished(o);
        }
        Boolean allFinished = true;
        for (int i = 1; i <= 3; i++) {
            if (brc.getOrder(i) != null)
                allFinished = false;
        }
        assertEquals(true, allFinished);
    }

}
