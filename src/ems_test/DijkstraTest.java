package ems_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ems.Branch;
import ems.Customer;
import ems.Order;
import ems.Position;
import ems.Edge;
import ems.Graph;
import ems.Dijkstra;

public class DijkstraTest {   
    Position pos1 = null;
    Position pos2 = null;
    Position pos3 = null;
    Position pos4 = null;
    Position pos5 = null;
    Position pos6 = null;
    Position pos7 = null;
    Branch branch1 = null;
    Branch branch2 = null;
    Branch branch3 = null;
    Branch branch4 = null;
    Branch branch5 = null;
    Branch branch6 = null;
    Branch branch7 = null;
    Edge edge1 = null;
    Edge edge2 = null;
    Edge edge3 = null;
    Edge edge4 = null;
    Edge edge5 = null;
    Edge edge6 = null;
    Edge edge7 = null;
    List <Branch> branchlist; 
    List <Edge> edgelist;
	Graph graph = null;
	Dijkstra dijkstra = null;
	ArrayList<Branch> path1 = null;
	ArrayList<Branch> path2 = null;
	ArrayList<Branch> path3 = null;
	ArrayList<Branch> path4 = null;
	ArrayList<Branch> path5 = null; 
	ArrayList<Branch> path6 = null; 
	@Before
	public void setUp() throws Exception {
		path1 = new ArrayList<>();
		path2 = new ArrayList<>();
		path3 = new ArrayList<>();
		path4 = new ArrayList<>();
		path5 = new ArrayList<>();
		path6 = new ArrayList<>();
		pos1 = new Position(1,1);
		pos2 = new Position(6,1);
		pos3 = new Position(4,4);
		pos4 = new Position(1,3);
		pos5 = new Position(5,6);
		pos6 = new Position(12,6);
		pos7 = new Position(8,8);
		branch1 = new Branch(1,"Sheila",pos1);
		branch2 = new Branch(2,"Sherlock",pos2);
		branch3 = new Branch(3,"Jerry",pos3);
		branch4 = new Branch(4,"Patrick",pos4);
		branch5 = new Branch(5,"Yolanda",pos5);
		branch6 = new Branch(6,"Tony",pos6);
		branch7 = new Branch(7,"Alinda",pos7);
		branchlist = new ArrayList<Branch>();
		edgelist = new ArrayList<>();
		branchlist.add(branch1);
		branchlist.add(branch2);
		branchlist.add(branch3);
		branchlist.add(branch4);
		branchlist.add(branch5);
		edge1  = new Edge("a",branch1,branch2,branch1.getDistance(branch2));
		edgelist.add(edge1);
		edge2  = new Edge("b",branch2,branch5,branch2.getDistance(branch5));
		edgelist.add(edge2);
		edge3  = new Edge("c",branch1,branch3,branch1.getDistance(branch3));
		edgelist.add(edge3);
		edge4  = new Edge("d",branch3,branch5,branch3.getDistance(branch5));
		edgelist.add(edge4);
		edge5  = new Edge("e",branch1,branch4,branch1.getDistance(branch4));
		edgelist.add(edge5);
		edge6  = new Edge("f",branch2,branch7,branch7.getDistance(branch2));
		edgelist.add(edge6);
		edge7  = new Edge("g",branch5,branch7,branch7.getDistance(branch5));
		edgelist.add(edge7);
		graph = new Graph(branchlist,edgelist);
		dijkstra = new Dijkstra(graph);
		dijkstra.execute(branch1);
		path1.add(branch1);
		
		path2.add(branch1);
		path2.add(branch2);
		
		path3.add(branch1);
		path3.add(branch3);
		
		path4.add(branch1);
		path4.add(branch4);
		
		path5.add(branch1);
		path5.add(branch3);
		path5.add(branch5);
		
		
	}
	@Test
	public void testGetPath01() {
		assertEquals(null,dijkstra.getPath(branch1));
	}
	
	@Test
	public void testGetPath02() {
		assertEquals(path2,dijkstra.getPath(branch2));
	}
	
	@Test
	public void testGetPath03() {
		assertEquals(path3,dijkstra.getPath(branch3));
	}
	
	@Test
	public void testGetPath04() {
		assertEquals(path4,dijkstra.getPath(branch4));
	}
	
	@Test
	public void testGetPath05() {
		assertEquals(path5,dijkstra.getPath(branch5));
	}

	@Test
	public void testGetPath06() {
		assertEquals(null,dijkstra.getPath(branch6));
	}
}
