package ems;

public class Order {

	private int id;
	private String itemName;
    private Position location;
	private Position destination;

    // private double price; // might be useless
    // private int priority; // might be useless

	public Order(int id, String itemName, Position location, Position destination) {
		this.id = id;
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
