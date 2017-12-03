package ems_test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ems.Branch;
import ems.CmdSearchOrder;
import ems.Company;
import ems.Customer;
import ems.Position;

public class CmdSearchOrderTest {
	CmdSearchOrder cmd;
	Company c;
	
	@Before
	public void setUp() throws Exception {
		c=Company.getInstance();
		String para[]={"CmdSearchOrder","1"};
		cmd=new CmdSearchOrder(c,para);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		setOutput();
		c.reset();
		Customer sender=new Customer(2, "A", "123");
		Customer receiver=new Customer(4, "B", "234");
		c.createOrder("1", sender, receiver);
		cmd.execute();
		assertEquals("no branch exist\r\nOrder not found. Please input the correct Order ID or Order Name\r\n",getOutput());
	}
	
	@Test
	public void test02() throws Exception {
		setOutput();
		c.reset();
		Customer sender=new Customer(2, "A", "123");
		Customer receiver=new Customer(4, "B", "234");
		Position p1 = new Position(1,2);
		Position p2 = new Position(1,2);
		c.addBranch("b1",p1);
		c.addBranch("b1", p2);
		Branch b1 = c.getBranchByLocation(p1);
		Branch b2 = c.getBranchByLocation(p2);
		c.addLinkage(b1.getId(), b2.getId());
	    c.addLinkage(b2.getId(), b1.getId());
		c.createOrder("1", sender, receiver);
		cmd.execute();
		assertEquals("[#1 1]\r\n[Branch b1 at (1, 2)]\r\n",getOutput());
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
