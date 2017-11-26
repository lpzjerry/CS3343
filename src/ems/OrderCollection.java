//package ems;
//import java.util.*;
////import java.util.zip.CheckedOutputStream;
//
//import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;
//
////import javax.print.attribute.standard.PrinterMessageFromOperator;
//// wfz &qr
//
//public class OrderCollection {
//	private int id;
//	private PriorityQueue<Order> myQueue;
//
//	public OrderCollection(int id) {
//		this.id = id;
//		this.myQueue = new PriorityQueue<Order>(); // compare function will be defined in order class, just implement comparable interface in order class
//	}
//
//	public int getId() {
//		return this.id;
//	}
//
//	public PriorityQueue<Order> getQueue() {
//		return this.myQueue;
//	}
//
//	public void add(Order o) {
//		myQueue.add(o);
//	}
//
//	public Order peek() {
//		return myQueue.peek();
//	}
//
//	public void remove(Order o) {
//		myQueue.remove(o);
//	}
//}
