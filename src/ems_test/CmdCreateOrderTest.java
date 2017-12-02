package ems_test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ems.Branch;
import ems.Customer;
import ems.Dijkstra;
import ems.Edge;
import ems.Graph;
import ems.Position;
import ems.Company;
import ems.CmdAddBranch;
import ems.CmdAddLinkage;
import ems.CmdCreateOrder;
import ems.CmdAddCustomer;
import ems.Manager;

public class CmdCreateOrderTest {
	Position pos1 = null;
    Position pos2 = null;
    Position pos3 = null;
    Position pos4 = null;
    Manager manager = null;
    Edge edge1 = null;
	CmdAddBranch cmdAddBranch;
	CmdAddLinkage cmdAddLinkage;
	CmdCreateOrder cmd;
	CmdAddCustomer cmdC;
	String[] paraB1 = new String[]{"addBranch","Sheila","2","1"};
	String[] paraB2 = new String[] {"addBranch","Sherlock","5","4"};
	Customer sender,receiver;
	@Before
	public void setUp() throws Exception {
		pos1 = new Position(1,1);
		pos2 = new Position(2,1);
		pos3 = new Position(5,4);
		pos4 = new Position(5,3);
        manager = new Manager(1, "test", "password","male", 1);
		cmdAddBranch = new CmdAddBranch(paraB1,manager);
		cmdAddBranch.execute();
		cmdAddBranch = new CmdAddBranch(paraB2,manager);
		cmdAddBranch.execute();
		cmdAddLinkage = new CmdAddLinkage(manager,new String[]{"addLinkage","1","2"}) ;
		cmdAddLinkage.execute();
		cmdC = new CmdAddCustomer(new String[]{"addCustomer","Sheila","password","8","1","1"},manager);
		cmdC.execute();
		cmdC = new CmdAddCustomer(new String[]{"addCustomer","Yolanda","password","8","5","3"},manager);
		cmdC.execute();
		sender = Company.getInstance().getCustomer(0);
		cmd = new CmdCreateOrder(Company.getInstance(),new String[] {"addCreateOrder","iphone","1"},sender);
	}
	@Test
	public void testConstructor() {
		//cmd = new CmdCreateOrder(Company.getInstance(),new String[] {"addCreateOrder","iphone","1"},sender);
	}
	@Test
	public void testExecute() {
		 ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));
	     cmd.execute();
	     assertEquals("[#1 iphone] is created.\n",outContent.toString());
	}

}
