package ems_test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ems.Branch;
import ems.Customer;
import ems.Order;
import ems.Position;

public class BranchTest {

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
        String desiredRes = "[Branch testBranch at (3, 4)]";
        assertEquals(desiredRes, brc.toString());
    }

    @Test
    public void test01_CheckInOrder() {

        Branch brc = new Branch(1, "testBranch", posStub);
        ArrayList<Position> testPath = new ArrayList<Position>();
        testPath.add(posStub);
        Order order1 = new Order(1, "testOrder1", cus_send, cus_receive, testPath);
        assertEquals(true, brc.checkInOrder(order1));
    }

    @Test
    public void test01_CheckOutOrders() {
        Branch brc = new Branch(1, "testBranch", posStub);
        ArrayList<Position> testPath = new ArrayList<Position>();
        testPath.add(posStub);
        Order order1 = new Order(1, "testOrder1", cus_send, cus_receive, testPath);
        brc.checkInOrder(order1);
        assertEquals(true, brc.checkOutOrders(order1));
    }


}