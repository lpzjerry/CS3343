package ems_IntegrationTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ems.Company;
import ems.Customer;
import ems.OrderPool;
import ems.Position;

public class CustomerCompanyClass {
	Customer cus1,cus2,cus3;
	Company com;
	OrderPool op;
	ArrayList<Position> input;

	@Before
	public void setUp() throws Exception {
		Position pos=new Position(1,3);
		cus1 = new Customer(12,"Alex","alex123",1,pos);
		cus2 = new Customer(44,"Ben","ben123",pos);
		cus3 = new Customer(27,"Cara","cara123");
		com=Company.getInstance();
		op=OrderPool.getInstance();
		input=new ArrayList();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test01_getOrderLocation() {
		Position pos=new Position(1,3);
		input.add(new Position(1,3));
		int cuOrder=op.createOrder("item1", cus1, cus2,input);
		Position result=cus1.getOrderLocation(cuOrder);
		int x=result.getX();
		int y=result.getY();
		assertEquals(x,1);
		assertEquals(y,3);
	}
	
	@Test
	public void test02_ConfirmReception() {
    	Position pos=new Position(2,5);
		input.add(new Position(2,5));
		int cuOrder=op.createOrder("item2", cus2, cus3, input);
		cus2.confirmReception(op.getOrderById(1));
		assertEquals(true,op.getOrderById(1).isReceived());
	}
	
	@Test
	public void test03_getPos() {
		assertEquals(1,cus1.getPosition().getX());
		assertEquals(3,cus1.getPosition().getY());
	}
	
	@Test
	public void test04_GetId() {
		assertEquals(cus1.getId(),12);
	}
	
	@Test 
	public void test05_askToCreateOrder() {
		cus1.askToCreateOrder("item3", cus2);
		assertEquals(1,op.getOrderByName("item3"));
	}
	 

}
