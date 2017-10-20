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

	public String getLocation() {
//		TODO: maybe call Company to return the location(branch offices)
        return "";
	}

	public String getItemName(){
	    return this.itemName;
    }

    public int getId(){
	    return this.id;
    }
}
