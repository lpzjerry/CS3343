package ems;

import java.util.HashMap;
import java.util.Map;

public class OrderPool {

	private HashMap<Integer, Order> orderList;
	
	private static OrderPool instance;
	
	private int currentOrder = 0;
	
	private OrderPool() {
		this.orderList  = new HashMap<Integer, Order>();
	}
	
	public static OrderPool getInstance() {return instance;}
	
	
	/**
	 * This method will add order object into the OrderPool, which belongs to the express company.
	 * After adding to the OrderPool, a id(int) will be assigned to this order object and
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

	public Order getOrderByName(String name){
	    for (Map.Entry<Integer, Order> entry: orderList.entrySet()){

	        Order o = entry.getValue();
	        if(o.getItemName().equals(name)){
	            return o;
            }
        }
        return null;
    }
	
	

}
