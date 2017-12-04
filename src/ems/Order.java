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
    /**
     *This is the order constructor.
     *@param id The id of the order.
     *@param itemName The name of the order.
     *@param sender The sender customer of the order.
     *@param receiver The receiver customer of the order
     *@param path The planned delivery route assigned to the order.
     */
    public Order(int id, String itemName, Customer sender, Customer receiver, ArrayList<Position> path) {
        this.id = id;
        this.itemName = itemName;
        this.sender = sender;
        this.receiver = receiver;
        this.path = path;
        locationPtr = 0;

        this.received = false;
    }
    /**
     * This is the current location getter.
     * @return The current position of the order.
     * */
    public Position currentLocation() {

        return path.get(locationPtr);
    }
    /**
     * This is the destination getter.
     * @return The destination of the order.
     * */
    public Position destination() {
        return receiver.getPosition();
    }
    /**
     * This method is used to check whether the order has reached the destination been sent.
     * @return Whether the order has been sent or not.
     **/
    public boolean hasBeenSent() {
        return this.currentLocation().equals(destination());
    }
    /**
     * This method is used to output the report of sending.
     **/
    public void reportSent() {
        System.out.printf("Item #%d %s has been sent to its destination\n", id, itemName);
    }
    
    /**
     * This is the next location getter. If the order has already been sent, report it, otherwise get the next location.
     * @return The next position of the order.
     * */
    public Position nextLocation() {
        if (!hasBeenSent())
            return path.get(locationPtr + 1);
        reportSent();
        return path.get(locationPtr);
    }
    /**
     * This method is used to move the order to the next location. If the order has already been sent, report it,
     *  otherwise move the order to the next location.
     * */
    public void moveToNextLocation() {
        if (!hasBeenSent()) locationPtr++;
        else reportSent();
    }
    
    /**
     * This is the itemName getter.
     * @return itemName of the order.
     * */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * This is the id getter.
     * @return id of the order.
     * */
    public int getId() {
        return this.id;
    }

    /**
     * This method is used to check whether an order is accessible by a customer.
     * @param customer The customer for check.
     * @return True or False.
     * */
    public boolean accessible(Customer customer) {
        return customer == this.receiver || customer == this.sender;
    }
    
    /**
     * This is the receiver getter.
     * @return The receiver of the order.
     * */
    public Customer getReceiver() {
        return receiver;
    }
    
    /**
     * This method is used to change the status of the order to received.
     * */
    public void receiveOrder() {
        this.received = true;
    }
    
    /**
     * This method is used to check whether an order is received.
     * @return True or False.
     * */
    public boolean isReceived() {
        return this.received;
    }
    /**
     * This method is override the original toString() method
     * @return The string of the order id and itemName information.
     * */
    public String toString() {
        return "[#" + this.id + " " + this.itemName + "]";
    }
    /**
     * This method is used to update the position of the order by time
     * @param time The current company system time.
     * */
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
