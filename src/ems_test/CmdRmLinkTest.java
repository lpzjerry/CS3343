package ems_test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ems.CmdRmLinkage;
import ems.Manager;
import ems.Position;

public class CmdRmLinkTest {
	CmdRmLinkage rmLink;
	Manager m;
	@Before
	public void setUp() throws Exception {
		m=new Manager(23,"A", "123", "F", 0);
		rmLink = new CmdRmLinkage(m,new String[]{"RmLinkage","1","3"});
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		setOutput();
		rmLink.execute();
		System.out.flush();
		assertEquals("source or target does not exist",getOutput().trim());
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
