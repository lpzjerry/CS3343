package ems;

public interface Sender {
   public Order AskToCreateOrder(String itemName,Customer c);
   public Order AskToCreateOrder(String itemName,String address);
   public String getCurrentLocation(Order o);
   public Boolean ChangeDestination(Order o,String address);
   public Boolean AskToWithdrawOrder(Order o);

}
