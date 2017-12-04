package ems;
/**
 * <h1>Branch</h1>
 * The branch is the transfer point of delivery. The order will go past several branches
 * to reach the destination.
 * 
 *@version v0.2-beta
 * */
import java.util.*;
// wfz &qr
// Refactored by Pengze Liu

public class Branch {

    private int id;
    private String name;
    private Position location;
    private ArrayList<Order> queuingOrders = new ArrayList<Order>();
    private ArrayList<Order> onDelivery = new ArrayList<Order>();

    /**
     *This is the branch constructor.
     *@param id The id of Branch.
     *@param name The name of Branch.
     *@param loc The position of Branch.
     */
    
    // Refactored by Pengze LIU 2017-Nov-3
    public Branch(int id, String name, Position loc) {
        this.id = id;
        this.name = name;
        this.location = loc;
        queuingOrders = new ArrayList<>();
        onDelivery = new ArrayList<>();
    }
    
    /**
     * This is the id getter.
     * @return id
     * */

    public int getId() {
        return this.id;
    }    
    
    /**
     * This is the position getter.
     * @return position
     * */

    // Added by Pengze LIU 2017-Nov-3
    public Position getLocation() {
        return this.location;
    }
    /**
     * This method is used to get the distance between this branch and another branch.
     * @param destination The destination branch.
     * @return The distance.
     * */
    public int getDistance(Branch destination) {
        return this.location.distance(destination.getLocation());
    }
    /**
     * This method is override the original toString() method
     * @return The string of the branch name and location information.
     * */
    @Override
    public String toString() {
        return String.format("[Branch %s at (%d, %d)]", this.name, this.location.getX(), this.location.getY());
    }
    /**
     * This method is used to check in a given order.
     * @param order The order to check in.
     * @return Whether the order is checked in successfully.
     * */
    // Refactored by Pengze LIU 2017-Nov-3
    public boolean checkInOrder(Order order) {
        boolean tmp=queuingOrders.add(order);
        tmp=tmp&&this.queuingOrders.remove(order);
        tmp=tmp&&this.onDelivery.add(order);
        return tmp;
    }
    /**
     * This method is used to check out a given order.
     * @param order The order to check out.
     * @return Whether the order is checked out successfully.
     * */
    // Refactored by Pengze LIU 2017-Nov-3
    public boolean checkOutOrders(Order order) { // assign queuing Orders to Courier (FreeMan)
        return this.onDelivery.remove(order);
    }

    
}
