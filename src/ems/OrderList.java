package ems;

import java.util.HashMap;

public class OrderList {

	private HashMap<Integer, Order> orderList;
	
	private static OrderList instance;
	
	private int currentOrder = 0;
	
	private OrderList() {
		this.orderList  = new HashMap<Integer, Order>();
	}
	
	public static OrderList getInstance() {return instance;}
	
	
	/**
	 * This method will add order object into the OrderList, which belongs to the express company.
	 * After adding to the OrderList, a id(int) will be assigned to this order object and 
	 * id will be returned.
	 * @param order
	 * @return id of this order
	 */
	public int addOrderToList(Order order) {
		
		this.currentOrder++;
		this.orderList.put(this.currentOrder, order);
		
		return currentOrder;
	}
	
	public Order getOrderById(int id) {
		return this.orderList.get(id);
	}
	
	

}
