package ems_test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ems.Branch;
import ems.Customer;
import ems.Order;
import ems.Position;
import ems.Edge;

public class EdgeIntegrationTest {

	Position pos1 = null;
    Position pos2 = null;
    Branch branch1 = null;
    Branch branch2 = null;
	Edge edge = null;
	@Before
	public void setUp() throws Exception {
		pos1 = new Position(1,2);
		pos2 = new Position(3,4);
		branch1 = new Branch(1,"Sheila",pos1);
		branch2 = new Branch(2,"Suzy",pos2);
		edge = new Edge("abc",branch1,branch2,5); 
	}

	@Test
	public void testGetId() {
		assertEquals("abc",edge.getId());
	}
	
	@Test
	public void testGetDestination() {
		assertEquals(branch2,edge.getDestination());
	}
	
	@Test
	public void testGetSource() {
		assertEquals(branch1,edge.getSource());
	}
	
	@Test
	public void testGetWeight() {
		assertEquals(5,edge.getWeight());
	}
	
	@Test
	public void testToString(){
		assertEquals("[Branch Sheila at (1, 2)] [Branch Suzy at (3, 4)]",edge.toString());
	}
}
