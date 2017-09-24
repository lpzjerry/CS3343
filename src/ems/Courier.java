package ems;
import java.util.*;
/**
 * Created by JerryLiu on 24/9/2017.
 */
public class Courier {
    private int ID; // unique identification for each Courier
    private String name;
    private PostOffice managerOffice;
    private ArrayList<Package> packageQueue; // packages to be delivered
    // TODO Constructor
    public Courier(){}

    // TODO push package into queue if there queue is empty and there are queuing packages in his/her office
    public void collectPackage(){}

    // TODO deliver the top package in the queue to the receiver; remove it from the queue; inform office;
    public void deliverPackage(){}
    private void checkout(Package pkg){}
    private void informOfCheckout(PostOffice office){}

    // TODO report the current unfinished task to the manager office
    public void reportUnfinishedTask(){}
}
