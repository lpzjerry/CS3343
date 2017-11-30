package ems_test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ems.CmdRmBranch;
import ems.Command;
import ems.Invoker;
import ems.Manager;

public class InvokerTest {
	Invoker invoker;
	@Before
	public void setUp() throws Exception {
		invoker=new Invoker();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test01_getSize() {
		assertEquals(0,invoker.getSize());
	}
	
	@Test
	public void test02_stoAndExe() {
		Command cmd=new CmdRmBranch(new String[]{"CmdRmBranch","1"}, new Manager(12, "A", "123", "F", 0));
		invoker.StoreAndExecute(cmd);
	}

}
