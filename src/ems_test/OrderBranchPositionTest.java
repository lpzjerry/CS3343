package ems_test;

import ems.Branch;
import ems.Company;
import ems.Customer;
import ems.Manager;
import ems.Order;
import ems.Position;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.ArrayList;

public class OrderBranchPositionTest {
	
	Customer sender,receiver;
	Customer otherCus;
	ArrayList<Position> path;
	Position pos1,pos2,pos3; 
	Order order;
	
	@Before
    public void setUp() throws Exception {
		sender=new Customer(1, "Ann", "12345",new Position(1,5));
		receiver=new Customer(2, "Bob", "12345",new Position(26,43));
		otherCus=new Customer(32, "Bob", "12345",new Position(26,43));
		path=new ArrayList<Position>();
		pos1=new Position(22,34);
		pos2=new Position(24,40);
		pos3=new Position(26,43);
		order=new Order(12,"item1",sender,receiver,path);
    }

    @Test
    public void test01_currentPosition() {
    	path.add(pos1);
    	int resultX=order.currentLocation().getX();
    	int resultY=order.currentLocation().getY();
    	assertEquals(resultX,22);
    	assertEquals(resultY,34);
    } 

    @Test
    public void test02_destination() {
    	int resultX=order.destination().getX();
    	int resultY=order.destination().getY();
    	assertEquals(resultX,26);
    	assertEquals(resultY,43);
    }
    
    @Test
    public void test03_hasBeenSent() {
    	path.add(pos1);
    	assertEquals(false,order.hasBeenSent());
    }
    
    @Test
    public void test04_getItemName() {
    	assertEquals("item1",order.getItemName());
    }
    
    @Test
    public void test05_getID() {
    	assertEquals(12,order.getId());
    }
    
    @Test
    public void test06_accessible01() {
    	assertEquals(order.accessible(sender),true);
    }
    
    @Test
    public void test07_accessible02() {
    	assertEquals(order.accessible(otherCus),false);
    }
    
    @Test
    public void test08_getReceiver() {
    	assertEquals(order.getReceiver(),receiver);
    }
    
    @Test
    public void test09_receiveOrder() {
    	order.receiveOrder();
    	assertEquals(order.isReceived(),true);
    }
    
    @Test
    public void test10_receiveOrder() {
    	assertEquals(order.isReceived(),false);
    }
    
    @Test
    public void test11_toString() {
    	assertEquals(order.toString(),"[#" + 12 + " " + "item1" + "]");
    }
    
    @Test
    public void test12_reportSent() throws Exception {
    	setOutput();
    	order.reportSent();
    	assertEquals("Item #12 item1 has been sent to its destination\n",getOutput());
    }
    
    @Test
    public void test13_nextLocation01() throws Exception {
    	path.add(pos1);
    	path.add(pos2);
		path.add(pos3);
    	Position result=order.nextLocation();
    	assertEquals(result,pos2);
    }
    
    @Test
    public void test14_nextLocation02() throws Exception {
    	setOutput();
		path.add(order.destination());
    	Position result=order.nextLocation();
    	assertEquals("Item #12 item1 has been sent to its destination\n",getOutput());
    	assertEquals(result,order.destination());
    }
    
    @Test
    public void test15_moveToNextPosition01() throws Exception {
    	setOutput();
		path.add(order.destination());
    	order.moveToNextLocation();
    	assertEquals("Item #12 item1 has been sent to its destination\n",getOutput());
    }
    
    @Test
    public void test15_moveToNextPosition02() throws Exception {
    	setOutput();
		path.add(pos1);
    	order.moveToNextLocation();
    	assertEquals("",getOutput());
    }
    
    @Test
    public void test16_accessible03() throws Exception {
    	assertEquals(order.accessible(receiver),true);
    }
    
    
    @Test
    public void test17_updatePositionByTime01() throws Exception{
    	setOutput();
    	order.receiveOrder();
    	order.updatePositionByTime(10);
    	assertEquals("",getOutput());
    }
    
    @Test
    public void test18_updatePositionByTime02() throws Exception{
    	setOutput();
    	path.add(pos1);
    	path.add(pos2);
    	order.updatePositionByTime(10);
    	assertEquals("",getOutput());
    }
    
    @Test
    public void test19_updatePositionByTime03() throws Exception{
    	setOutput();
    	path.add(pos1);
    	order.updatePositionByTime(10);
    	assertEquals("Order [item1] is available to receive by [Customer: 2 name: Bob]\r\n",getOutput());
    }
    
    @Test
    public void test20_updatePositionByTime04() throws Exception{
    	setOutput();
    	path.add(pos1);
    	path.add(pos2);
    	Company.getInstance().addBranch("b1", pos1); 
		Company.getInstance().addBranch("b1", pos2);
    	order.updatePositionByTime(10000);
    	assertEquals("Order [item1] is available to receive by [Customer: 2 name: Bob]\r\n",getOutput());
    }
    
    @Test
    public void test21_updatePositionByTime05() throws Exception{
    	setOutput();
    	path.add(pos1);
    	path.add(pos2);
    	Company.getInstance().addBranch("b1", pos1);
		Company.getInstance().addBranch("b1", pos2);
    	order.updatePositionByTime(7000);
    	assertEquals("",getOutput());
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
