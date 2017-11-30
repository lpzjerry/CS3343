package ems_test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ems.Branch;
import ems.CmdAddBranch;
import ems.Customer;
import ems.Order;
import ems.Position;
import ems.Manager;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CmdAddBranchTest {

	CmdAddBranch cmd = null;
	Manager manager = null;
	String []para;
	@Before
	public void setUp() throws Exception {
		para = new String[]{"addBranch","Sheila","3","4"};
		manager = new Manager(1,"Suzy","Tony","F",555);
		cmd = new CmdAddBranch(para,manager);
	} 

	@Test
	public void testExecute() {
		 ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));
	     cmd.execute();
	     assertEquals("Branch: [Branch Sheila at (3, 4)] is added by: [manager Suzy]\n",outContent.toString());
	}

}
