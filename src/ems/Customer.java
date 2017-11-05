package ems;

import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;

public class Customer implements Sender, Receiver {
    private int id;
    private String name;
    private int psHash;

    private ArrayList<Integer> sentOrderID;
    private ArrayList<Integer> receivingOrderID;
    private Position position;

    private int priority;
    private ArrayList<Order> MyOrder;
    private ArrayList<Order> SendToMe;

    public Customer(int ID, String Name, String Password, int Priority, Position position) {
        this.id = ID;
        this.name = Name;
        this.priority = Priority;
        this.psHash = Password.hashCode();
        this.position = position;
        MyOrder = new ArrayList<Order>();
        SendToMe = new ArrayList<Order>();
    }

    //    default priority is 1
    public Customer(int ID, String Name, String Password, Position position) {
        this(ID, Name, Password, 1, position);
    }

    //    will allocate a random position to unclear location customer
    //    for testing and demo usage
    public Customer(int ID, String Name, String Password) {
        this(ID, Name, Password, 1, new Position());
    }

    private Company company = Company.getInstance();

    // Pengze Liu 2017-Nov-2
    @Override
    public Position getOrderLocation(int orderID) {
        return company.searchOrder(orderID).currentLocation();
    }

    @Override
    public void confirmReception(Order order) {
        company.receiveOrder(order);
    }

    @Override
    public void askToCreateOrder(String itemName, Customer receiver) {
        sentOrderID.add(company.createOrder(itemName, this, receiver));
    }

    public Position getPosition() {
        return position;
    }

    public int getId() {

        return this.id;
    }


}
