package ems_test;

import ems.Branch;
import ems.Position;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ems.Manager;

import static org.junit.Assert.assertNull;

public class ManagerTest {

    private Manager manager;
    private int ID_for_test;
    private String name_for_test;
    private String pw_for_test;
    private String gender_for_test;
    private int status_for_test;

    @Before
    public void setUp() throws Exception {
        ID_for_test = 1;
        name_for_test = "test";
        gender_for_test = "male";
        status_for_test = 1;
        pw_for_test = "1";
        manager = new Manager(ID_for_test, name_for_test, pw_for_test, gender_for_test, status_for_test);

    }

    @After
    public void tearDown() throws Exception {
        manager = null;

    }

    @Test
    public void test01_addBranch() {
        Branch result = manager.addBranch("name", new Position(1, 2));
        assertNull(result);
    }

    @Test
    public void test02_removeBranch() {
        Branch result = manager.removeBranch(1);
        assertNull(result);
    }


}
