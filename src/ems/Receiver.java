package ems;

public interface Receiver {
    public Position getOrderLocation(int orderID);

    public void confirmReception(Order order);
}