package ems_IntegrationTest;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ems.Customer;
import ems.Order;
import ems.OrderPool;
import ems.Position;

public class OrderPoolOrderTest {
	OrderPool op;
	Customer sender,receiver;
	ArrayList<Position> path;
	Position pos1,pos2,pos3;
	@Before
	public void setUp() throws Exception {
		op=OrderPool.getInstance();
		sender=new Customer(1, "Ann", "12345",new Position(1,5));
		receiver=new Customer(2, "Bob", "12345",new Position(26,43));
		path=new ArrayList<Position>();
		pos1=new Position(22,34);
		pos2=new Position(24,36);
		path.add(pos1);
		op.createOrder("item1", sender, receiver, path);
		op.createOrder("item2", sender, receiver, path);
	}

	@After 
	public void tearDown() throws Exception {
	}

	@Test
	public void test01_getOrderById() {
		assertEquals(op.getOrderById(1),op.getOrderByName("item1"));
		
	}
	
	@Test
	public void test02_getOrderByName() {
		assertEquals(op.getOrderByName("item3"),null);
	}
	
	@Test
	public void test03_receiveOrder() {
		op.receiveOrder(op.getOrderById(1));
		boolean result=op.getOrderById(1).isReceived();
		assertEquals(true,result);
	}
	
	@Test
	public void test04_processAllOrders() throws Exception {
		setOutput();
    	op.processAllOrders();
    	assertEquals("Order [item1] is received by [Customer: 2 name: Bob]\r\nOrder [item2] is received by [Customer: 2 name: Bob]\r\n",getOutput());
	}
	

	
	PrintStream oldPrintStream;
	ByteArrayOutputStream bos;

	private void setOutput() throws Exception {
		oldPrintStream = System.out;
		bos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bos));
	}
 
	private String getOutput() { // throws Exception
		System.setOut(oldPrintStream);
		return bos.toString();
	}

}
