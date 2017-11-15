package ems;

import java.util.ArrayList;

public class Order {

    private int id;
    private String itemName;
    //private Position location;
    private ArrayList<Position> path;
    private Customer sender;
    private Customer receiver;
    private int locationPtr;

    private boolean received;

    public Order(int id, String itemName, Customer sender, Customer receiver, ArrayList<Position> path) {
        this.id = id;
        this.itemName = itemName;
        this.sender = sender;
        this.receiver = receiver;
        this.path = path; // sender.getPosition() -> receiver.getPosition()
        locationPtr = 0;
        //this.location = sender.getPosition(); // Position of the sender, specified by company
        // this.price = price; // generate by Company
        // this.priority = priority; // generate by Company, [1 by default]

        this.received = false;
    }

    public Position currentLocation() {
        return path.get(locationPtr);
    }

    public Position destination() {
        return receiver.getPosition();
    }

    public boolean hasBeenSent() {
        return this.currentLocation().equals(destination());
    }

    public void reportSent() { System.out.printf("Item #%d %s has been sent to its destination\n", id, itemName); }

    public Position nextLocation() {
        if (!hasBeenSent())
            return path.get(locationPtr + 1);
        reportSent();
        return path.get(locationPtr);
    }

    public void moveToNextLocation() {
        if (!hasBeenSent()) locationPtr++;
        else reportSent();
    }

    public String getItemName() {
        return this.itemName;
    }

    public int getId() {
        return this.id;
    }

    public boolean accessible(Customer customer) {
        return customer == this.receiver || customer == this.sender;
    }

    public Customer getReceiver() {
        return receiver;
    }

    public void receiveOrder(){
        this.received = true;
    }

    public boolean isReceived(){
        return this.received;
    }
}
