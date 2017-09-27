package delivery;
import java.util.*;
import java.util.zip.CheckedOutputStream;

import javax.print.attribute.standard.PrinterMessageFromOperator;


public class Branch implements ParcelManager,PostmanManager{
	private int id;
	private String address;
	private static int count=0;
	private ArrayList<Pkg> packages = new ArrayList<Pkg>();
	private ArrayList<Postman> freeMan = new ArrayList<Postman>();
	private ArrayList<Postman> outMan = new ArrayList<Postman>();
	private ArrayList<Pkg> onDelivery = new ArrayList<Pkg>();
	private static Object map = new Object() ;//to be confirm
	
	private static Branch[] optimizePath(Pkg p) {
		//
	}
	public Branch(String address) {
		this.address =address;
		Branch.count++;
		this.id=Branch.count;
	}
	public void checkIn(Pkg p,Branch previous) {
		this.packages.add(p);
		previous.arrive(p);
	}
	public void arrive(Pkg p) {
		this.onDelivery.remove(p);
	}
	private void checkedOut(Pkg p, Postman m) {
		this.packages.remove(p);
		this.outMan.add(m);
		this.freeMan.remove(m);
		this.onDelivery.add(p);
		//m.addParcel()
	}
	public void deliver(Pkg p, Postman m) {
		//p.getAddress()
		Branch next=Branch.optimizePath(p)[1];
		this.checkedOut(p, m);
		//m.send(p)
	}
	
	public Postman getMan(int id) {
		
	}
	
	public Pkt getPkt(int id) {
		
	}
	public getId() {
		return this.id;
	}
	public void printPath(Pkt p){
		//prePath=p.getPrePath();
		nextPath=Branch.optimizePath(p);
		path=prePath+nextPath;
		System.out.println(path);
		
	}
	
	
	
}
