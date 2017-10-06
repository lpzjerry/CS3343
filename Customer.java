package com.company;


public class Customer implements Sender,Receiver {
    private int id;
    private String name;
    private String password;
    private ArrayList<Order> MyOrder;
    private ArrayList<Order> SendToMe;

    public Order AskToCreateOrder(String itemName,Customer c){
        return Company.CreateOrder(itemName,c);
    }

    public Order AskToCreateOrder(String itemName,String address){
        return Company.CreateOrder(itemName,address);
    }

    public Boolean AskToWithdrawOrder(Order o){
        if(o.status==1)return false;
        else return Company.WithdrawOrder(o);
    }

    public Boolean ChangeDestination(Order o,String address){
        if(o.status!=1){
            o.address = address;
            return true;}
        else return false;
    }

    public String getCurrentLocation(Order o){
        return o.getLocation();
    }

    public Boolean ConfirmReception(Order o){
        return true;
    }



}
