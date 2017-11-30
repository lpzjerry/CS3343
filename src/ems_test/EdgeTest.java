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

public class EdgeTest {

	Position pos = null;
    public class BranchStub extends Branch {
    		private int id;
    		public BranchStub(int id, String name, Position loc){
    			super(id,name,loc);
    			this.id = id;
    		}
    		
    	    @Override
    	    public String toString() {
    	        if(id==1)return "Sheila";
    	        else return "Suzy";
    	    }
    }
    
    BranchStub branchstub1 = null;
    BranchStub branchstub2 = null;
	Edge edge = null;
	@Before
	public void setUp() throws Exception {
		pos = new Position();
		branchstub1 = new BranchStub(1,"Sheila",pos);
		branchstub2 = new BranchStub(2,"Suzy",pos);
		edge = new Edge("abc",branchstub1,branchstub2,5); 
	}

	@Test
	public void testGetId() {
		assertEquals("abc",edge.getId());
	}
	
	@Test
	public void testGetDestination() {
		assertEquals(branchstub2,edge.getDestination());
	}
	
	@Test
	public void testGetSource() {
		assertEquals(branchstub1,edge.getSource());
	}
	
	@Test
	public void testGetWeight() {
		assertEquals(5,edge.getWeight());
	}
	
	@Test
	public void testToString(){
		assertEquals("Sheila Suzy",edge.toString());
	}

}
