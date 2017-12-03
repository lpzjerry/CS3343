package ems;

import java.util.ArrayList;

public class Order {

    private int id;
    private String itemName;
    private ArrayList<Position> path;
    private Customer sender;
    private Customer receiver;
    private int locationPtr;
    private long initTime = Company.getInstance().getTime();
    private long timeBuffer = 0;
    private boolean received;

    public Order(int id, String itemName, Customer sender, Customer receiver, ArrayList<Position> path) {
        this.id = id;
        this.itemName = itemName;
        this.sender = sender;
        this.receiver = receiver;
        this.path = path;
        locationPtr = 0;

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

    public void reportSent() {
        System.out.printf("Item #%d %s has been sent to its destination\n", id, itemName);
    }

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

    public void receiveOrder() {
        this.received = true;
    }

    public boolean isReceived() {
        return this.received;
    }

    public String toString() {
        return "[#" + this.id + " " + this.itemName + "]";
    }

    public void updatePositionByTime(long time) {
        if (isReceived()) return;
        long past_time = time - initTime;
        timeBuffer += past_time;
        int nextPtr = locationPtr + 1;
        while (timeBuffer > 0 && nextPtr < path.size()) {
            int next_length = Position.distance(path.get(locationPtr), path.get(nextPtr));
            long next_time = next_length * 1000; // second -> millisecond
            if (timeBuffer > next_time) {
            	Company.getInstance().getBranchByLocation(this.path.get(locationPtr)).checkOutOrders(this);
                locationPtr++;
                Company.getInstance().getBranchByLocation(this.path.get(locationPtr)).checkInOrder(this);
                nextPtr++;
                timeBuffer -= next_time;
            } else break;
        }
        if (nextPtr >= path.size()) {
            received = true;
            System.out.println("Order [" + this.itemName + "] is available to receive by " + receiver);
            Company.getInstance().getBranchByLocation(this.path.get(locationPtr)).checkOutOrders(this);
            this.receiver.confirmReception(this);           
        }
    }
}