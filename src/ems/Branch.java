package ems;

import java.util.*;
// wfz &qr
// Refactored by Pengze Liu

public class Branch {

    private int id;
    private String name;
    private Position location;
    private ArrayList<Order> queuingOrders = new ArrayList<Order>();
    private ArrayList<Order> onDelivery = new ArrayList<Order>();

    // Refactored by Pengze LIU 2017-Nov-3
    public Branch(int id, String name, Position loc) {
        this.id = id;
        this.name = name;
        this.location = loc;
        queuingOrders = new ArrayList<>();
        onDelivery = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    // Added by Pengze LIU 2017-Nov-3
    public Position getLocation() {
        return this.location;
    }

    public int getDistance(Branch destination) {
        return this.location.distance(destination.getLocation());
    }

    @Override
    public String toString() {
        return String.format("[Branch %s at (%d, %d)]", this.name, this.location.getX(), this.location.getY());
    }

    // Refactored by Pengze LIU 2017-Nov-3
    public boolean checkInOrder(Order order) {
        return queuingOrders.add(order);
    }

    // Refactored by Pengze LIU 2017-Nov-3
    public boolean checkOutOrders(Order order) { // assign queuing Orders to Courier (FreeMan)
        return this.onDelivery.remove(order);
    }

    
}
