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
	class PositionStub extends Position{ 
		
		public  int distance(Position p) {
				return 10;
		}
		public int getX(){
				return 3;
			}
		public int getY() {
				return 4;
			}
	}
	class StubCustomer extends Customer{
		public StubCustomer(int ID, String Name, String Password) {
			super(ID, Name, Password);
		}
	}
	class OrderStub extends Order{ 
		int Id;
		public OrderStub(int id, String itemName, Customer sender, Customer receiver, ArrayList<Position> path) {
			super(id, itemName, sender, receiver, path);
			this.Id = id;
		}
		public int getId() {
			return this.Id;
		}
	}
	class CourierStub extends Courier{
		public CourierStub(int ID, String name, Branch managerOffice, int capacity) {
	        super(ID,  name,  managerOffice,  capacity);
	    }
	}
	private PositionStub posStub = new PositionStub(); 
	private StubCustomer cus_send = new StubCustomer(1,"testSend","123456");
	private StubCustomer cus_receive = new StubCustomer(2,"testReceive","654321");
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testGetId() {
		Branch brc = new Branch(1,"testBranch",posStub);
		 assertEquals(1,brc.getId());
	}

	@Test
	public void testGetName() {
		Branch brc = new Branch(1,"testBranch",posStub);
		assertEquals("testBranch",brc.getName());
	}

	@Test
	public void testGetOrder1() {
		Branch brc = new Branch(1,"testBranch",posStub);
		ArrayList<Position> testPath = new ArrayList<Position>();
		testPath.add(posStub);
		OrderStub order1 = new OrderStub(1,"testOrder1",cus_send,cus_receive,testPath);
		OrderStub order2 = new OrderStub(2,"testOrder2",cus_send,cus_receive,testPath);
		OrderStub order3 = new OrderStub(3,"testOrder3",cus_send,cus_receive,testPath);
		brc.checkInOrder(order1);
		brc.checkInOrder(order2);
		brc.checkInOrder(order3);
		Order retOrder = brc.getOrder(2);
		assertEquals(2,retOrder.getId());
	}
	@Test
	public void testGetOrder2() {
		Branch brc = new Branch(1,"testBranch",posStub);
		ArrayList<Position> testPath = new ArrayList<Position>();
		testPath.add(posStub);
		OrderStub order1 = new OrderStub(1,"testOrder1",cus_send,cus_receive,testPath);
		OrderStub order2 = new OrderStub(2,"testOrder2",cus_send,cus_receive,testPath);
		OrderStub order3 = new OrderStub(3,"testOrder3",cus_send,cus_receive,testPath);
		brc.checkInOrder(order1);
		brc.checkInOrder(order2);
		brc.checkInOrder(order3);
		Order retOrder = brc.getOrder(10);
		assertEquals(null,retOrder);
	}
	@Test
	public void testGetOrder3() {
		Branch brc = new Branch(1,"testBranch",posStub);
		ArrayList<Position> testPath = new ArrayList<Position>();
		CourierStub cur = new CourierStub(1,"testCourier",brc,10);
		testPath.add(posStub);
		OrderStub order1 = new OrderStub(1,"testOrder1",cus_send,cus_receive,testPath);
		OrderStub order2 = new OrderStub(2,"testOrder2",cus_send,cus_receive,testPath);
		OrderStub order3 = new OrderStub(3,"testOrder3",cus_send,cus_receive,testPath);
		brc.checkInOrder(order1);
		brc.checkInOrder(order2);
		brc.checkInOrder(order3);
		brc.checkOutOrders(cur);
		Order retOrder = brc.getOrder(2);
		assertEquals(2,retOrder.getId());
	}
	@Test
	public void testGetMan() {
		Branch brc = new Branch(1,"testBranch",posStub);
		CourierStub cur = new CourierStub(1,"testCourier",brc,10);
		brc.arrive(cur);
		Courier getted = brc.getMan(cur.getID());
		assertEquals(1,getted.getID());
	}

	@Test
	public void testGetLocation() {
		Branch brc = new Branch(1,"testBranch",posStub);
		Position pos = brc.getLocation();
		Boolean ok = (pos.getX() == 3) && (pos.getY() == 4);
		assertEquals(true,ok);
	}

	@Test
	public void testGetDistance() {
		Branch brc1 = new Branch(1,"testBranch1",posStub);
		Branch brc2 = new Branch(2,"testBranch2",posStub);
		int res = brc1.getDistance(brc2);
		assertEquals(10,res);
	}

	@Test
	public void testToString() {
		Branch brc = new Branch(1,"testBranch",posStub);
		String desiredRes = "Name: testBranch, Position: (3, 4)";
		assertEquals(desiredRes,brc.toString());
	}

	@Test
	public void testCheckInOrder() {
		
		Branch brc = new Branch(1,"testBranch",posStub);
		ArrayList<Position> testPath = new ArrayList<Position>();
		testPath.add(posStub);
		OrderStub order1 = new OrderStub(1,"testOrder1",cus_send,cus_receive,testPath);
		OrderStub order2 = new OrderStub(2,"testOrder2",cus_send,cus_receive,testPath);
		OrderStub order3 = new OrderStub(3,"testOrder3",cus_send,cus_receive,testPath);
		brc.checkInOrder(order1);
		brc.checkInOrder(order2);
		brc.checkInOrder(order3);
		Boolean allIn = true;
		for(int i = 1; i<= 3;i++)
		{
			if(brc.getOrder(i) == null)
				allIn = false;
		}
		assertEquals(true,allIn);
	}

	@Test
	public void testCheckOutOrders() {
		Branch brc = new Branch(1,"testBranch",posStub);
		ArrayList<Position> testPath = new ArrayList<Position>();
		CourierStub cur = new CourierStub(1,"testCourier",brc,10);
		testPath.add(posStub);
		OrderStub order1 = new OrderStub(1,"testOrder1",cus_send,cus_receive,testPath);
		OrderStub order2 = new OrderStub(2,"testOrder2",cus_send,cus_receive,testPath);
		OrderStub order3 = new OrderStub(3,"testOrder3",cus_send,cus_receive,testPath);
		brc.checkInOrder(order1);
		brc.checkInOrder(order2);
		brc.checkInOrder(order3);
		ArrayList<Order> checkedOut = brc.checkOutOrders(cur);
		assertEquals(3,checkedOut.size());
	}

	@Test
	public void testArrive() {
		Branch brc = new Branch(1,"testBranch",posStub);
		CourierStub cur = new CourierStub(1,"testCourier",brc,10);
		brc.arrive(cur);
		Courier getted = brc.getMan(cur.getID());
		assertEquals(1,getted.getID());
	}
	@Test
	public void testReportFinished() {
		Branch brc = new Branch(1,"testBranch",posStub);
		ArrayList<Position> testPath = new ArrayList<Position>();
		testPath.add(posStub);
		CourierStub cur = new CourierStub(1,"testCourier",brc,10);
		OrderStub order1 = new OrderStub(1,"testOrder1",cus_send,cus_receive,testPath);
		OrderStub order2 = new OrderStub(2,"testOrder2",cus_send,cus_receive,testPath);
		OrderStub order3 = new OrderStub(3,"testOrder3",cus_send,cus_receive,testPath);
		brc.checkInOrder(order1);
		brc.checkInOrder(order2);
		brc.checkInOrder(order3);
		ArrayList<Order> checkedOut = brc.checkOutOrders(cur);
		for(Order o: checkedOut)
		{
			brc.reportFinished(o);
		}
		Boolean allFinished = true;
		for(int i = 1; i<= 3;i++)
		{
			if(brc.getOrder(i) != null)
				allFinished = false;
		}
		assertEquals(true,allFinished);
	}

}
