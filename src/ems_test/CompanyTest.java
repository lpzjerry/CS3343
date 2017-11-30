package ems_test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import ems.Branch;
import ems.Company;
import ems.Position;
import ems.Customer;
import ems.OrderPool;
import ems.Manager;
import ems.Order;

public class CompanyTest {
    private Company c = Company.getInstance();

    @Test 
    public void testAddLinkage_1() {
    		c.reset();
	    Position p1 = new Position();
	    Position p2 = new Position();
	    	c.addBranch("b1",p1);
	    c.addBranch("b2", p2);
	    Branch b1 = c.getBranchByLocation(p1);
	    Branch b2 = c.getBranchByLocation(p2);
    		boolean res = c.addLinkage(b1.getId(), 3);
    		assertEquals(false,res);
    }
    @Test 
    public void testAddLinkage_2() {
    		c.reset();
	    Position p1 = new Position();
	    Position p2 = new Position();
	    	c.addBranch("b1",p1);
	    c.addBranch("b2", p2);
	    Branch b1 = c.getBranchByLocation(p1);
	    Branch b2 = c.getBranchByLocation(p2);
		boolean res = c.addLinkage(b1.getId(), b2.getId());
		assertEquals(true,res);
    }
    @Test 
    public void testAddLinkage_3() {
    		c.reset();
		boolean res = c.addLinkage(1, 2);
		assertEquals(false,res);
    }
    @Test 
    public void testRemoveLinkage_1() {
    		c.reset();
    		Position p1 = new Position();
	    Position p2 = new Position();
	    c.addBranch("B1", p1);
    		c.addBranch("B2", p2);
		Branch b1 = c.getBranchByLocation(p1);
		Branch b2 = c.getBranchByLocation(p2);
		c.addLinkage(b1.getId(), b2.getId());
		boolean res = c.rmLinkage(b1.getId(), 3);
		assertEquals(false,res);
    }
    @Test 
    public void testRemoveLinkage_2() {
    		c.reset();
    		Position p1 = new Position();
    	    Position p2 = new Position();
    	    c.addBranch("B1", p1);
        		c.addBranch("B2", p2);
    		Branch b1 = c.getBranchByLocation(p1);
    		Branch b2 = c.getBranchByLocation(p2);
    		c.addLinkage(b1.getId(), b2.getId());
		boolean res = c.rmLinkage(b1.getId(), b2.getId());
		assertEquals(true,res);
    }
    @Test 
    public void testRemoveLinkage_3() {
		c.reset();
		Position p1 = new Position();
	    Position p2 = new Position();
	    c.addBranch("B1", p1);
    		c.addBranch("B2", p2);
		Branch b1 = c.getBranchByLocation(p1);
		Branch b2 = c.getBranchByLocation(p2);
		boolean res = c.rmLinkage(b1.getId(), b2.getId());
		assertEquals(false,res);
    }    
    @Test 
    public void testRemoveLinkage_4() {
		c.reset();
		Position p1 = new Position();
	    Position p2 = new Position();
	    Position p3 = new Position();
	    c.addBranch("B1", p1);
    		c.addBranch("B2", p2);
    		c.addBranch("B3", p3);
		Branch b1 = c.getBranchByLocation(p1);
		Branch b2 = c.getBranchByLocation(p2);
		Branch b3 = c.getBranchByLocation(p3);
		c.addLinkage(b1.getId(),b2.getId());
		boolean res = c.rmLinkage(b1.getId(), b3.getId());
		assertEquals(false,res);
    }    
    @Test 
    public void testRemoveLinkage_5() {
    		c.reset();
		boolean res = c.rmLinkage(1, 3);
		assertEquals(false,res);
    }
    @Test 
    public void testRemoveLinkage_6() {
		c.reset();
		Position p1 = new Position();
	    Position p2 = new Position();
	    Position p3 = new Position();
	    c.addBranch("B1", p1);
    		c.addBranch("B2", p2);
    		c.addBranch("B3", p3);
		Branch b1 = c.getBranchByLocation(p1);
		Branch b2 = c.getBranchByLocation(p2);
		Branch b3 = c.getBranchByLocation(p3);
		c.addLinkage(b1.getId(),b2.getId());
		boolean res = c.rmLinkage(b2.getId(), b3.getId());
		assertEquals(false,res);
    }    
    @Test
    public void testAddManager_1() {
		c.reset();
    		Manager mng = c.addNewManager("wfz", "ljyk", "Male", 100);
    		assertEquals(100,mng.getStatus());
    }
    @Test
    public void testCreateOrder_1() {
		c.reset();
	    	Customer fakesender = new Customer(1, "fakesender", "abc");
	    	Customer fakereceiver = new Customer(2, "fakereceiver", "def");
	    Position p1 = new Position();
	    Position p2 = new Position();
	    	c.addBranch("b1",p1);
	    c.addBranch("b2", p2);
	    Branch b1 = c.getBranchByLocation(p1);
	    Branch b2 = c.getBranchByLocation(p2);
	    c.addLinkage(b1.getId(), b2.getId());
	    c.addLinkage(b2.getId(), b1.getId());
	    	int res = c.createOrder("yk", fakesender, fakereceiver);
	    	assertEquals(1,res);
    }
    @Test
    public void testCreateOrder_2() {
		c.reset();
	    	Customer fakesender = new Customer(1, "fakesender", "abc");
	    	Customer fakereceiver = new Customer(2, "fakereceiver", "def");
	    	c.addBranch("hh", new Position());
	    	int res = c.createOrder("yk", fakesender, fakereceiver);
	    	assertEquals(1,res);
    }
    @Test
    public void testCreateOrder_3() {
		c.reset();
	    	Customer fakesender = new Customer(1, "fakesender", "abc");
	    	Customer fakereceiver = new Customer(2, "fakereceiver", "def");
	    		Position p1 = new Position();
		    Position p2 = new Position();
		    Position p3 = new Position();
		    Position p4 = new Position();
		    	c.addBranch("b1", p1);
		    	c.addBranch("b2", p2);
		    	c.addBranch("b3", p3);
		    	c.addBranch("b4", p4);
	   Branch b1 = c.getBranchByLocation(p1);
	   Branch b2 = c.getBranchByLocation(p2);
	   Branch b3 = c.getBranchByLocation(p3);
	   Branch b4 = c.getBranchByLocation(p4);
	    c.addLinkage(b1.getId(), b2.getId());
	    c.addLinkage(b2.getId(), b1.getId());
	    c.addLinkage(b2.getId(), b3.getId());
	    c.addLinkage(b3.getId(), b2.getId());
	    c.addLinkage(b3.getId(), b4.getId());
	    c.addLinkage(b4.getId(), b3.getId());
	    c.addLinkage(b1.getId(), b3.getId());
	    c.addLinkage(b3.getId(), b1.getId());
	    	int res = c.createOrder("yk", fakesender, fakereceiver);
	    	assertEquals(1,res);
    }
    @Test
    public void testCreateOrder_4() {
		c.reset();
	    	Customer fakesender = new Customer(1, "fakesender", "abc",new Position(1,2));
	    	Customer fakereceiver = new Customer(2, "fakereceiver", "def",new Position(3,4));
	    	c.addBranch("hhh", new Position(1,2));
	    	c.addBranch("233", new Position(3,4));
	    	int res = c.createOrder("yk", fakesender, fakereceiver);
	    	assertEquals(-1,res);
    }
    @Test
    public void testGetManager_1() {
		c.reset();
    		Manager m = c.addNewManager("wfz", "wfz", "Male",1);
    		Manager n = c.getManager(m.getId());
    		assertEquals(n,m);
    }
    @Test
    public void testRemoveManager_1() {
		c.reset();
    		Manager m = c.addNewManager("wfz", "wfz", "Male",1);
		Manager removed = c.removeManager(m.getId());
		assertEquals(m,removed);
    }
    @Test
    public void testAddBranch_1() {
		c.reset();
        Branch b = c.addBranch("A", new Position(1, 1));
        assertEquals(null, b);
    }
    @Test
    public void testAddBranch_2() {
		c.reset();
    	Branch a = c.addBranch("A", new Position(1, 1));
    	Branch b = c.addBranch("B", new Position(2, 2));
    assertEquals(null, b);
    }
    @Test
    public void testGetCustomer_1() {
		c.reset();
    		Customer cc = c.addCustomer("wfz", "123");
    		assertEquals(0, c.getCustomer(cc.getId()).getId());
    		
    }
    @Ignore
    @Test
    public void testGetTime_1() { 
		c.reset();
    		boolean ok = false;
    		if(c.getTime() < 10000)
    			ok = true;
    		assertEquals(true,ok);
    }
    @Test
    public void testReceiveOrder_1(){//?
		c.reset();
     	Customer fakesender = new Customer(1, "fakesender", "abc",new Position(1,2));
    	Customer fakereceiver = new Customer(2, "fakereceiver", "def",new Position(3,4));
    	Position p1 = new Position(1,2);
    	Position p2 = new Position(2,3);
    	Position p3 = new Position(3,4);
    	Position p4 = new Position(2,2);
    	
    	
    	c.addBranch("b1", p1);
   	    c.addBranch("b2", p2);
   	    c.addBranch("b3", p3);
   	    c.addBranch("b4",p4);
   	    Branch b1 = c.getBranchByLocation(p1);
   	    Branch b2 = c.getBranchByLocation(p2);
   	    Branch b3 = c.getBranchByLocation(p3);
   	    Branch b4 = c.getBranchByLocation(p4);
   	    c.addLinkage(b1.getId(), b2.getId());
   	    c.addLinkage(b2.getId(), b3.getId());
   	    c.addLinkage(b2.getId(), b4.getId());
    		c.createOrder("hhh", fakesender, fakereceiver);
    		c.receiveOrder(c.searchOrder("hhh"));
    		assertEquals(true,c.searchOrder("hhh").isReceived());
    }
    
    @Test
    public void testGetBranchByPosition_1() {
		c.reset();
	 	Position p1 = new Position(1,2);
	 	Position p2 = new Position(2,3);
	 	c.addBranch("b1",p1);
	 	c.addBranch("b2", p2);
        Branch re = c.getBranchByLocation(p1);
        assertEquals(p1,re.getLocation());
    }

    @Test
    public void testRemoveBranch_1() {
		c.reset();
		Position p1 =new Position(1,2);
		Position p2 =new Position(2,3);
		c.addBranch("A1", p1);
		c.addBranch("A2", p2);
    		Branch a1 = c.getBranchByLocation(p1);
    	    		Branch a2 = c.getBranchByLocation(p2);
    		c.addLinkage(a1.getId(), a2.getId());
    		c.addLinkage(a2.getId(), a1.getId());
        Branch b = c.removeBranch(a1.getId());
        assertEquals(a1.getId(), b.getId());
    }
    @Test
    public void testRemoveBranch_2() {
		c.reset();
		Position p1 =new Position(1,2);
		Position p2 =new Position(2,3);
		Position p3 =new Position(4,4);
		
		c.addBranch("A1", p1);
		c.addBranch("A2", p2);
		c.addBranch("A3", p3);
		Branch a1 = c.getBranchByLocation(p1);
	    Branch b = c.removeBranch(a1.getId());
	    assertEquals(a1.getId(), b.getId());
    }
   
    @Test
    public void testRemoveBranch_3() {
		c.reset();
		Position p1 =new Position(1,2);
		Position p2 =new Position(2,3);
		Position p3 =new Position(4,4);
		
		c.addBranch("A1", p1);
		c.addBranch("A2", p2);
		c.addBranch("A3", p3);

		Branch a1 = c.getBranchByLocation(p1);

		Branch a2 = c.getBranchByLocation(p2);

		Branch a3 = c.getBranchByLocation(p3);
		c.addLinkage(a2.getId(),a3.getId());
	    Branch b = c.removeBranch(a1.getId());
	    assertEquals(a1.getId(), b.getId());
    }
    @Test
    public void testSearchOrderByID() {
		c.reset();
        Customer fakesender = new Customer(1, "fakesender", "abc");
        Customer fakereceiver = new Customer(2, "fakereceiver", "def");
        OrderPool.getInstance().createOrder("itemName", fakesender, fakereceiver, new ArrayList<>());
        assertEquals(OrderPool.getInstance().getOrderById(1), Company.getInstance().searchOrder(1));
    }

    @Test
    public void testSearchOrderByName() {
		c.reset();
        Customer fakesender = new Customer(1, "fakesender", "abc");
        Customer fakereceiver = new Customer(2, "fakereceiver", "def");
        OrderPool.getInstance().createOrder("itemName", fakesender, fakereceiver, new ArrayList<>());
        assertEquals(OrderPool.getInstance().getOrderByName("itemName"),
                Company.getInstance().searchOrder("itemName"));
    }

    @Test
    public void testAddCustomer() {
		c.reset();
        assertEquals(0, c.addCustomer("name", "password").getId());
    }

    @Test
    public void testAddCustomerWithPosition() {
		c.reset();
        Position position = new Position(1,2);
        assertEquals(0, c.addCustomer("name", "password", 1, position).getId());
    }
}
