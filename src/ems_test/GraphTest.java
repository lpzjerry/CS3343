package ems_test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import ems.Branch;
import ems.Position;
import ems.Edge;
import ems.Graph;
public class GraphTest {
    Position pos = null;
    Branch branch1 = null;
    Branch branch2 = null;
    Edge edge1 = null;
    Edge edge2 = null;
    List <Branch> branchlist; 
    List <Edge> edgelist;
	Graph graph = null;
    @Before
	public void setUp() throws Exception {
		pos = new Position();
		branch1 = new Branch(1,"Sheila",pos);
		branch2 = new Branch(2,"Suzy",pos);
		branchlist = new ArrayList<Branch>();
		edgelist = new ArrayList<>();
		branchlist.add(branch1);
		branchlist.add(branch2);
		edgelist.add(edge1);
		edgelist.add(edge2);
		graph = new Graph(branchlist,edgelist);
		
	}


	@Test
	public void testGetVertexes() {
		assertEquals(branchlist,graph.getVertexes());
	}

	@Test
	public void testGetEdges() {
		assertEquals(edgelist,graph.getEdges());
	}

}
