package ems_test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ems.CmdAddBranch;
import ems.CmdAddLinkage;
import ems.Customer;
import ems.Position;
import ems.Manager;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CmdAddLinkageTest {
	CmdAddLinkage cmdL = null;
	CmdAddBranch cmdB1= null;
	CmdAddBranch cmdB2 = null;
	String[] paraB1 = null;
	String[] paraB2 = null;
	String[] paraL = null;
	Manager manager = null;
	@Before
	public void setUp() throws Exception {
		paraL= new String[]{"addLinkage","1","2","8","5","6"};
		paraB1 = new String[]{"addBranch","Sheila","3","4"};
		paraB2 = new String[]{"addBranch","Suzy","3","1"};
		manager = new Manager(1,"Yolanda","love","F",555);
		cmdB1 = new CmdAddBranch(paraB1,manager);
		cmdB1.execute();
		cmdB2 = new CmdAddBranch(paraB2,manager);
		cmdB2.execute();	
		cmdL = new CmdAddLinkage(manager,paraL);
	}
	@Test
	public void test01Execute() {
		 ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));
	     cmdL.execute();
	     assertEquals("edge created from: 1, to: 2",outContent.toString().trim());
	}
}


