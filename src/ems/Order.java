package ems;

public class Order {
	
	private int id;
	private String itemName;
	private double price;
	private int priority;
	
	/**
	 * the default value of priority is 1
	 * @param itemName
	 * @param price
	 * @param priority
	 */
	public Order(String itemName, double price, int priority) {
//		this.id = id;
		this.itemName = itemName;
		this.price  = price;
		this.priority = priority;
	}
	
	public Order(String itemName, double price) {
//		this.id = id;
		this.itemName = itemName;
		this.price  = price;
		this.priority = 1;
	}

}
