package ems;

import java.util.*;

/**
 * Created by JerryLiu on 24/9/2017.
 */
public class Courier {
    private int ID; // unique identification for each Courier
    private String name;
    private Branch managerOffice;
    private final int capacity;
    private ArrayList<Order> orderQueue; // Orders to be delivere

    private Company company;

    public Courier(int ID, String name, Branch managerOffice, int capacity) {
        this.ID = ID;
        this.name = name;
        this.managerOffice = managerOffice;
        this.capacity = capacity;
        orderQueue = new ArrayList<>();
        this.company = Company.getInstance();
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void collectOrder() {
        orderQueue = managerOffice.checkOutOrders(this);
        //Sheila-20171113-Start
        System.out.print("Courier " + this.ID + " has collected Order:");
        for (Order o : orderQueue) {
            System.out.print(" " + o.getId());
        }
        System.out.printf("\n");
        //Sheila-20171113-End
    }

    private void goBackToOffice() {
        managerOffice.arrive(this);
        //Sheila-20171114-Start
        System.out.print("Courier " + this.ID + " goes back to the office.");
        //Sheila-20171114-End
    }

    public Order popTopOrder() {
        if (orderQueue.isEmpty()) {
            //Sheila-20171113-Start
            System.out.print("Courier " + this.ID + " failed to pop the top order.(The order queue is empty)");
            return null;
        }
        Order res = orderQueue.get(0);
        orderQueue.remove(0);
        System.out.print("Courier " + this.ID + " pop the top order (ID: " + res.getId() + " )\n");
        //Sheila-20171113-End
        return res;
    }

    private void sendToHome(Order order) {
        order.moveToNextLocation();
        order.getReceiver().confirmReception(order);
        managerOffice.reportFinished(order);
    }

    private void sendToBranch(Order order) {
        Branch nextBranch = this.company.getBranchByLocation(order.nextLocation());
        order.moveToNextLocation();
        nextBranch.checkInOrder(order);
    }

    private void deliverOneOrder(Order order) {
        if (!order.hasBeenSent()) {
            Position nextPosition = order.nextLocation();
            if (order.nextLocation().equals(order.destination()))
                sendToHome(order);
            else
                sendToBranch(order);
        } else order.reportSent();
    }

    public void deliverAllOrder() {
        if (orderQueue.isEmpty()) {
            System.out.println("Nothing to deliver!");
            goBackToOffice();
            return;
        }
        while (!orderQueue.isEmpty()) {
            Order orderToDeliver = orderQueue.get(0);
            orderQueue.remove(0);
            deliverOneOrder(orderToDeliver);
            // TODO timer update
        }
        goBackToOffice();
    }

    public Position getLocation() {
        if (orderQueue.isEmpty())
            return managerOffice.getLocation();
        return orderQueue.get(0).currentLocation();
    }

    public void reportUnfinishedTask() {
        for (Order order : orderQueue) {
            System.out.printf("Unfinished task #%d: %s\n", order.getId(), order.getItemName());
        }
    }
}
