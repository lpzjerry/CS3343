package ems_test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ems.Branch;
import ems.Company;
import ems.Customer;
import ems.Manager;
import ems.Position;

public class ManagerCompanyTest {
	Manager m;
	@Before
	public void setUp() throws Exception {
		m=new Manager(100, "A", "123", "F", 0);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test01_getId() {
		assertEquals(100,m.getId());
	}
	
	@Test
	public void test02_getStatus() {
		assertEquals(0,m.getStatus());
	}
	
	@Test
    public void test03_addBranch() {
        Branch result = m.addBranch("name", new Position(1, 2));
        assertNull(result);
    }
	
	@Test
    public void test04_removeBranch() throws Exception {
		setOutput();
        Branch result = m.removeBranch(4);
        assertEquals(null,result);
    }
	
	@Test
    public void test05_addCustomer() {
		Customer result=m.addCustomer("A", "123");
        assertEquals(Company.getInstance().getCustomer(1),result);
    }
	
	@Test
    public void test06_addCustomer02() {
		Customer result=m.addCustomer("A", "123", 1, new Position(2,2));
        assertEquals(Company.getInstance().getCustomer(0),result);
    }

	@Test
    public void test07_rootOrNot() {
        assertEquals(true,m.rootOrNot());
    }
	
	@Test
    public void test08_addLink() {
        assertEquals(false,m.addLinkage(1, 5));
    }
	
	@Test
    public void test09_removeLink() {
        assertEquals(false,m.rmLinkage(1, 5));
    }
	
	@Test
    public void test10_addManager01() {
		Manager result=m.addNewManager("B", "001", "M", 0);
        assertEquals(Company.getInstance().getManager(0),result);
    }
	
	@Test
    public void test11_addManager02() {
		Manager m2=new Manager(101, "B", "001", "M", 1);
		Manager result=m2.addNewManager("C", "001", "M",0);
        assertEquals(null,result);
    }
	
	@Test
    public void test11_removeManager() {
		Manager result=m.removeManager(101);
        assertEquals(null,result);
    }
	
	@Test
    public void test11_toString() {
        assertEquals("[manager A]",m.toString());
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
