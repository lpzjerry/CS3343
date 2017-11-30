package ems_test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ems.CmdAddCustomer;
import ems.Customer;
import ems.Position;
import ems.Manager;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CmdAddCustomerTest {
	CmdAddCustomer cmdA = null;
	CmdAddCustomer cmdB = null;
	CmdAddCustomer cmdC = null;
	String[] cmd1 = null;//length6
	String[] cmd2 = null;//length3
	String[] cmd3 = null;//error
	Manager manager = null;
	@Before
	public void setUp() throws Exception {
		cmd1 = new String[]{"addCustomer","Sheila","Suzy","8","5","6"};
		cmd2 = new String[]{"addCustomer","Tony","Sherlock"};
		cmd3 = new String[]{"addCustomer"};
		manager = new Manager(1,"Yolanda","love","F",555);
	}
	@Test
	public void test01CmdAddCustomer() {
		 ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));
	     cmdC = new CmdAddCustomer(cmd3,manager);
	     assertEquals("argument error\n",outContent.toString());
	}
	
	
	@Test
	public void test02Execute() {
		 ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));
	     cmdA= new CmdAddCustomer(cmd1,manager);
	     cmdA.execute();
	     assertEquals("[Customer: 0 name: Sheila] is added by: [manager Yolanda]\n",outContent.toString());
	}
	
	@Test
	public void test03Execute() {
		 ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));
	     cmdB= new CmdAddCustomer(cmd2,manager);
	     cmdB.execute();
	     assertEquals("[Customer: 1 name: Tony] is added by: [manager Yolanda]\n",outContent.toString());
	}

}
