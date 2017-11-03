package ems;
import java.util.*;

/**
 * Created by JerryLiu on 24/9/2017.
 */
public class Courier {
    private int ID; // unique identification for each Courier
    private String name;
    private Branch managerOffice;
    private ArrayList<Order> orderQueue; // Orders to be delivered

    public Courier(int ID, String name, Branch managerOffice) {
        this.ID = ID;
        this.name = name;
        this.managerOffice = managerOffice;
        orderQueue = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    // TODO push Order into queue if there queue is empty and there are queuing Orders in his/her office
    public void collectOrder() {
        orderQueue = managerOffice.checkOut();
    }

    private void sendToHome(Order order) {
        // TODO implementation
    }

    private void sendToNextBranch(Order order) {
        // TODO implementation
    }

    private void goBackToOffice() {
        // TODO implementation
    }

    public void deliverOrder() {
        if(orderQueue.isEmpty()){
            System.out.println("Nothing to deliver!");
            goBackToOffice();
            return;
        }
        Order orderToDeliver = orderQueue.get(0);
        orderQueue.remove(0);
        if(orderQueue.size() % 2 == 0){ // TODO check if next position is branch/home
            sendToHome(orderToDeliver);
        } else {
            sendToNextBranch(orderToDeliver);
        }
    }

    public void reportUnfinishedTask() {
        for(Order order: orderQueue) {
            System.out.printf("Unfinished task #%d: %s\n", order.getId(), order.getItemName());
        }
    }
}
