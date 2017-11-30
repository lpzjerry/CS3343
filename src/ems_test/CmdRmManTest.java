package ems_test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ems.CmdRmManager;
import ems.Manager;

public class CmdRmManTest {
	CmdRmManager cmdRmM;
	String[] cmd1,cmd2; 
	Manager m;

	@Before
	public void setUp() throws Exception {
		m=new Manager(2, "A", "123", "F", 0);
		cmd1=new String[]{"CmdRmManger"};
		cmd2=new String[]{"CmdRmManger","1"};
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test01() throws Exception {
		setOutput();
		cmdRmM=new CmdRmManager(m,cmd1);
		assertEquals("argument error\r\n",getOutput());
		
	}
	
	@Test
	public void test02() throws Exception {
		setOutput();
		cmdRmM=new CmdRmManager(m,cmd2);
		cmdRmM.execute();
		assertEquals("id does not exist!\r\n",getOutput());
		
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
