package ems_test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ems.CmdAddManager;
import ems.Company;
import ems.Customer;
import ems.Position;
import ems.Manager;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CmdAddManagerTest {
	Manager manager = null;
	String[]para = null;
	CmdAddManager cmd = null;
	@Before
	public void setUp() throws Exception {
		Company company = Company.getInstance();
		company.addNewManager("super", "hahaha", "Male", 0);
		manager = Company.getInstance().getManager(0);
		para = new String[]{"addManager","Sheila","abc","F","666"};
		cmd = new CmdAddManager(manager,para);
	}

	@Test
	public void testExecute() {
		 ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));
	     cmd.execute();
	     assertEquals("Express Company Manager Sheila (level:666) created!",outContent.toString().split("\n")[0].trim());
	}

}
