package ems;

public interface Sender {
   public void askToCreateOrder(String itemName, Customer receiver);

   public Position getOrderLocation(int orderID);

   // public Boolean changeDestination(int orderID, Position newPosition);

   // public Boolean askToWithdrawOrder(int orderID);
}
