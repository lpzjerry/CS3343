package ems_test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ems.CmdCheckTime;
import ems.Company;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
public class CmdCheckTimeTest {
    CmdCheckTime cmd = null;
	String[] para;
    @Before
	public void setUp() throws Exception {
		para = new String[] {"checkTime"};
    		cmd = new CmdCheckTime(Company.getInstance(),para);
	}

	@Test
	public void testExecute() {
		 ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));
	     cmd.execute();
	     assertEquals("It is 1\n",outContent.toString());
	}

}
