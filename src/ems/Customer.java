package ems;

import java.util.ArrayList;

public class Customer implements Sender, Receiver {
    private int id;
    private String name;
    private String password;
    private ArrayList<Integer> sentOrderID;
    private ArrayList<Integer> receivingOrderID;
    private Position position;
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

    /*
    public Order AskToCreateOrder(String itemName, Customer customer) {
        return company.CreateOrder(itemName, customer);
    }

    public Order AskToCreateOrder(String itemName, int[] address) {
        return company.CreateOrder(itemName, address);
    }

    public Boolean AskToWithdrawOrder(Order order) {
        if (order.status == 1)
            return false;
        return Company.WithdrawOrder(order);
    }

    public Boolean ChangeDestination(Order order, Position newPosition) {
        if (!order.hasBeenSent()) {
            order.setNewLocation(newPosition);
            return true;
        }
        return false;
    }

    public Position getCurrentLocation(Order o) {
        return o.getLocation();
    }

    public Boolean ConfirmReception(Order o) {
        return true;
    }

    public Order searchOrder(int id) {
        return company.searchOrder(id);
    }

    public Order searchOrder(String name) {
        return company.searchOrder(name);
    }

    public Position getPosition() {
        return position;
    }
    */
}