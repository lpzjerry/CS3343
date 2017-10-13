package ems;
import java.util.*;
/**
 * Created by JerryLiu on 24/9/2017.
 */
public class Courier {
    private int ID; // unique identification for each Courier
    private String name;
    private Branch managerOffice;
    private ArrayList<Order> OrderQueue; // Orders to be delivered
    // TODO Constructor
    public Courier(){}

    // TODO push Order into queue if there queue is empty and there are queuing Orders in his/her office
    public void collectOrder(){}

    // TODO deliver the top Order in the queue to the receiver; remove it from the queue; inform office;
    public void deliverOrder(){}
    private void checkout(Order order){}
    private void informOfCheckout(PostOffice office){}

    // TODO report the current unfinished task to the manager office
    public void reportUnfinishedTask(){}
}
