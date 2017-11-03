package ems;

public class Order {

	private int id;
	private String itemName;
<<<<<<< Updated upstream
    private Position location;
	private Position destination;

    // private double price; // might be useless
    // private int priority; // might be useless

	public Order(int id, String itemName, Position location, Position destination) {
		this.id = id;
//=======
//	private double price;
//	private int priority;
//
//	private
//
//	/**
//	 * the default value of priority is 1
//	 * @param itemName
//	 * @param price
//	 * @param priority
//	 */
//	public Order(String itemName, double price, int priority) {
////		this.id = id;
//		this.itemName = itemName;
//		this.price  = price;
//		this.priority = priority;
//	}
//
//	public Order(String itemName, double price) {
////		this.id = id;
//>>>>>>> Stashed changes
		this.itemName = itemName;
		this.location = location; // Position of the sender, specified by company
		this.destination = destination; // Position of the receiver, specified by sender
		// this.price = price; // generate by Company
		//this.priority = priority; // generate by Company, [1 by default]
	}

	public Position getLocation() {
		return location;
	}

	public boolean hasBeenSent() {
	    return location.equals(destination);
    }

	public String getItemName() {
		return this.itemName;
	}

	public int getId() {
		return this.id;
	}
}
