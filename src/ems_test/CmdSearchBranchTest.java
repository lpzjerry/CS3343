package ems_test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ems.CmdSearchBranch;
import ems.Company;
import ems.Position;

public class CmdSearchBranchTest {
	Company com;
    Position pos;
    CmdSearchBranch cmd;
    String[] para1,para2;
	@Before
	public void setUp() throws Exception {
		com=Company.getInstance();
		pos=new Position(1,2);
		para1=new String[]{"CmdSearchBranch"};
		para2=new String[]{"CmdSearchBranch","1","2"};
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		setOutput();
		cmd=new CmdSearchBranch(com,para1);
		cmd.execute();
		assertEquals("invalid arguments!\r\n",getOutput());
		
	}
	
	@Test
	public void test2() throws Exception {
		setOutput();
		com.addBranch("b1", pos);
		cmd=new CmdSearchBranch(com,para2);
		
		cmd.execute();
		assertEquals("[Branch b1 at (1, 2)]\r\n",getOutput());
		
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
