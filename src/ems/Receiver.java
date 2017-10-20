package ems;

public interface Receiver {
    public String getCurrentLocation(Order o);
    public Boolean ConfirmReception(Order o);
}
