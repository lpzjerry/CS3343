package ems_test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ems.Branch;
import ems.Customer;
import ems.Order;
import ems.Position;
import ems_test.BranchTest.PositionStub;

public class BranchPosIntegrationTest {


    private Position pos = null;
    private Customer cus_send = null;
    private Customer cus_receive = null;

    @Before
    public void setUp() throws Exception {
        pos = new Position();
        cus_send = new Customer(1, "testSend", "123456");
        cus_receive = new Customer(2, "testReceive", "654321");

    }

    @Test
    public void test01_GetId() {
        Branch brc = new Branch(1, "testBranch", pos);
        assertEquals(1, brc.getId());
    }



    @Test
    public void test01_GetLocation() {
        Branch brc = new Branch(1, "testBranch", pos);
        assertEquals(pos,brc.getLocation());
    }

    @Test
    public void test01_GetDistance() {
    		Position pos1 = new Position(1,2);
    		Position pos2 = new Position(3,4);
        Branch brc1 = new Branch(1, "testBranch1", pos1);
        Branch brc2 = new Branch(2, "testBranch2", pos2);
        int res = brc1.getDistance(brc2);
        assertEquals(4, res);
    }

    @Test
    public void test01_ToString() {
    		Position pos1 = new Position(1,2);
        Branch brc = new Branch(1, "testBranch", pos1);
        String desiredRes ="[Branch testBranch at (1, 2)]";
        assertEquals(desiredRes, brc.toString());
    }

    @Test
    public void test01_CheckInOrder() {

        Branch brc = new Branch(1, "testBranch", pos);
        ArrayList<Position> testPath = new ArrayList<Position>();
        testPath.add(pos);
        Order order1 = new Order(1, "testOrder1", cus_send, cus_receive, testPath);
        assertEquals(true,brc.checkInOrder(order1) );
    }
    @Test
    public void test01_CheckOutOrders() {
        Branch brc = new Branch(1, "testBranch", pos);
        ArrayList<Position> testPath = new ArrayList<Position>();
        testPath.add(pos);
        Order order1 = new Order(1, "testOrder1", cus_send, cus_receive, testPath);
        brc.checkInOrder(order1);
        assertEquals(true, brc.checkOutOrders(order1));
    }
}