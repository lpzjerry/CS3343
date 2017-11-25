package ems;

public interface Sender {

    public void askToCreateOrder(String itemName, Customer target);

    public Position getOrderLocation(int orderID);

}
