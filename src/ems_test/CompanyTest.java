package ems_test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

import ems.Branch;
import ems.Company;
import ems.Position;
import ems.Customer;
import ems.OrderPool;

public class CompanyTest {
    private Company c = Company.getInstance();

    @Test
    public void testAddBranch_1() {
        Branch b = c.addBranch("A", new Position(1, 1));
        assertEquals(null, b);
    }

    @Test
    public void testGetBranchByPosition_1() {
        Branch b = c.addBranch("A2", new Position(1, 2));
        Branch b1 = c.addBranch("A3", new Position(1, 3));
        Branch re = c.getBranchByLocation(new Position(1, 3));
        assertEquals("A3", re.getName());
    }

    @Test
    public void testGetBranchByPosition_2() {
        Branch b = c.getBranchByLocation(new Position(1, 4));
        assertEquals("A3", b.getName());
    }

    @Test
    public void testNeighbour_1() {
        c.addBranch("A4", new Position(4, 4));
        Branch s = c.getBranchByLocation(new Position(1, 1));
        Branch v = c.getBranchByLocation(new Position(4, 4));

        Branch b = c.neighbourForBranch(s, v);
        assertEquals(c.getBranchByLocation(new Position(1, 2)), b);
    }

    @Test
    public void testNeighbour_2() {
        c.addBranch("A4", new Position(3, 1));
        Branch s = c.getBranchByLocation(new Position(1, 1));
        Branch v = c.getBranchByLocation(new Position(3, 1));

        Branch b = c.neighbourForBranch(s, v);
        assertEquals(v, b);
    }

    @Test
    public void testRemoveBranch_1() {
        Branch b2 = c.removeBranch(2);
        assertEquals("A2", b2.getName());
    }

    @Test
    public void testSearchOrderByID() {
        Customer fakesender = new Customer(1, "fakesender", "abc");
        Customer fakereceiver = new Customer(2, "fakereceiver", "def");
        OrderPool.getInstance().createOrder("itemName", fakesender, fakereceiver, new ArrayList<>());
        assertEquals(OrderPool.getInstance().getOrderById(1), Company.getInstance().searchOrder(1));
    }

    @Test
    public void testSearchOrderByName() {
        Customer fakesender = new Customer(1, "fakesender", "abc");
        Customer fakereceiver = new Customer(2, "fakereceiver", "def");
        OrderPool.getInstance().createOrder("itemName", fakesender, fakereceiver, new ArrayList<>());
        assertEquals(OrderPool.getInstance().getOrderByName("itemName"),
                Company.getInstance().searchOrder("itemName"));
    }

    @Test
    public void testAddCustomer() {
        assertEquals(0, Company.getInstance().addCustomer("name", "password").getId());
    }

    @Test
    public void testAddCustomerWithPosition() {
        Position position = new Position();
        assertEquals(1, Company.getInstance().addCustomer("name", "password", 1, position).getId());
    }
}
