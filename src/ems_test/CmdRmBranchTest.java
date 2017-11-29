package ems_IntegrationTest;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ems.CmdRmBranch;
import ems.Manager;

public class CmdRmBranchTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		setOutput();
		Manager m=new Manager(2, "A", "123", "F", 0);
		String[] cmd={"rmBranch","1"};
		CmdRmBranch RmBranch=new CmdRmBranch(cmd,m);
		RmBranch.execute();
		assertEquals("Express Company Manager A (level:0) created!\r\nbranch: 1 does not exist!\r\n",getOutput());
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
