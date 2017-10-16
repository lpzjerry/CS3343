package ems;
import java.util.*;
//import java.util.zip.CheckedOutputStream;

//import javax.print.attribute.standard.PrinterMessageFromOperator;


public class Branch {
	private int id;
	private String name;
	private static int count=0;
	private ArrayList<Order> packages = new ArrayList<Order>();
	private ArrayList<Courier> freeMan = new ArrayList<Courier>();
	private ArrayList<Courier> outMan = new ArrayList<Courier>();
	private ArrayList<Order> onDelivery = new ArrayList<Order>();
	private static Object map = new Object() ;//to be confirm
	
	private static Branch[] optimizePath(Order p) {

		return new Branch[10]; // tmp dummy return value -- added by patrick ;)
	}

	public Branch(int id, String name) {
//		TODO: conflict constructor with Manager
		this.id = id;
		this.name =name;
		Branch.count++;
		this.id=Branch.count;
	}

	public void checkIn(Order p,Branch previous) {
		this.packages.add(p);
		previous.arrive(p);
	}

	public void arrive(Order p) {
		this.onDelivery.remove(p);
	}

	private void checkedOut(Order p, Courier m) {
		this.packages.remove(p);
		this.outMan.add(m);
		this.freeMan.remove(m);
		this.onDelivery.add(p);
		//m.addParcel()
	}

	public void deliver(Order p, Courier m) {
		//p.getAddress()
		Branch next=Branch.optimizePath(p)[1];
		this.checkedOut(p, m);
		//m.send(p)
	}
	
	public Courier getMan(int id) {
/* TODO: implement getMan*/
		return null;
	}
	
	public Order getPkt(int id) {
/* TODO: implement getOrder*/
		return null;
	}
	public int getId() {
		return this.id;
	}
	public void printPath(Order p){
//		TODO: implement printPath in Branch
//		prePath=p.getPrePath();
//		Branch[] nextPath = Branch.optimizePath(p);
//		path=prePath+nextPath;
//		System.out.println(path);
//		what ever...
		
	}
	
	
	
}
