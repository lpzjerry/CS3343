package ems;

import java.util.ArrayList;

public class Customer implements Sender, Receiver {
    private int id;
    private String name;
    private String password;
//<<<<<<< HEAD
//    private ArrayList<Integer> sentOrderID;
//    private ArrayList<Integer> receivingOrderID;
//    private Position position;

    private int priority = 1;// default
    private ArrayList<Order> MyOrder;
    private ArrayList<Order> SendToMe;
    
    public Customer(int ID,String Name,String Password,int Priority){
        this.id = ID;
        this.name = Name;
        this.priority = Priority;
        MyOrder = new ArrayList<Order>();
        SendToMe = new ArrayList<Order>();
    }

    private Company company = Company.getInstance();

    // Pengze Liu 2017-Nov-2
    @Override
    public Position getOrderLocation(int orderID) {
        return company.searchOrder(orderID).getLocation();
    }

    @Override
    public void confirmReception(Order order) {
        // TODO called by Courier, remove order from OrderPool, change order status
    }

    @Override
    public void askToCreateOrder(String itemName, Customer target) {
        company.createOrder(itemName, this.getPosition(), target.getPosition());
    }

    @Override
    public void askToCreateOrder(String itemName, Position target) {
        company.createOrder(itemName, this.getPosition(), target);
    }

    @Override
    public Boolean changeDestination(int orderID, Position newPosition) {
        return null;
    }

    @Override
    public Boolean askToWithdrawOrder(int orderID) {
        return null;
    }

    public Position getPosition() {
        return position;
    }

}