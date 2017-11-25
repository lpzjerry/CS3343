package ems;

import java.util.*;

public class Branch {


    private int id;
    private String name;
    private Position location;
    private ArrayList<Courier> freeMan = new ArrayList<Courier>();
    private ArrayList<Courier> outMan = new ArrayList<Courier>();
    private ArrayList<Order> queuingOrders = new ArrayList<Order>();
    private ArrayList<Order> onDelivery = new ArrayList<Order>();

    public Branch(int id, String name, Position loc) {
        this.id = id;
        this.name = name;
        this.location = loc;
        queuingOrders = new ArrayList<>();
        onDelivery = new ArrayList<>();

    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Order getOrder(int id) {
        for (Order order : this.queuingOrders) {
            if (order.getId() == id) {
                return order;
            }
        }
        for (Order order : this.onDelivery) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public Courier getMan(int id) {
        for (Courier m : this.freeMan) {
            if (m.getID() == id) {
                return m;
            }
        }
        for (Courier m : this.outMan) {
            if (m.getID() == id) {
                return m;
            }
        }

        return null;
    }

    public Position getLocation() {
        return this.location;
    }

    public int getDistance(Branch destination) {
        return this.location.distance(destination.getLocation());
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Position: (%d, %d)", this.name, this.location.getX(), this.location.getY());
    }

    public void checkInOrder(Order order) {
        queuingOrders.add(order);
    }

    public ArrayList<Order> checkOutOrders(Courier courier) {
        ArrayList<Order> thingsToSend = new ArrayList<Order>();
        int weight = 1;
        while (weight < courier.getCapacity() && thingsToSend.size() > 0) {
            Order newOrder = queuingOrders.get(0);
            thingsToSend.add(newOrder);
            onDelivery.add(newOrder);
            thingsToSend.add(newOrder);
            queuingOrders.remove(newOrder);
        }
        this.outMan.add(courier);
        this.freeMan.remove(courier);
        return thingsToSend;
    }

    public void arrive(Courier courier) {
        this.onDelivery.remove(courier.popTopOrder());
        this.outMan.remove(courier);
        this.freeMan.add(courier);
    }

    private boolean checkLastDelivery(Order order) {
        return order.hasBeenSent();
    }

    public void reportFinished(Order order) {
        onDelivery.remove(order);
    }
}
