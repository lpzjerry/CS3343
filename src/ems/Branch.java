package ems;

import java.util.*;
//import java.util.zip.CheckedOutputStream;

//import javax.print.attribute.standard.PrinterMessageFromOperator;
// wfz &qr

public class Branch {


    private int id;
    private String name;
    private int[] location;
    private ArrayList<Branch> neighbour = new ArrayList<Branch>();
    private ArrayList<Courier> freeMan = new ArrayList<Courier>();
    private ArrayList<Courier> outMan = new ArrayList<Courier>();
    private ArrayList<OrderCollection> packages = new ArrayList<OrderCollection>();
    private ArrayList<Order> onDelivery = new ArrayList<Order>();
    private final int capacity;
    private int robinpointer = 0;

    public Branch(int id, String name, int[] loc, int cap, int sur, ArrayList<Branch> branches) {
        this.id = id;
        this.name = name;
        this.location = loc;
        this.capacity = cap;
        this.neighbour = branches;
        for (int i = 0; i <= this.neighbour.size(); i++) {
            this.packages.add(new OrderCollection(1)); // we need to initiate with unique id for each neighbor
        }
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Order getOrder(int id) {
        for (OrderCollection q : this.packages) {
            for (Order o : q.getQueue()) {
                if (o.getId() == id) {
                    return o;
                }
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
            if (m.getId() == id) {
                return m;
            }
        }
        for (Courier m : this.outMan) {
            if (m.getId() == id) {
                return m;
            }
        }

        return null;
    }


    public void checkInOrder(Courier c) {
        for (OrderCollection q : this.packages) {
            if (checkLastDelivery(c.getOrder())) {
                if (checkLastDelivery(q.peek())) {
                    q.add(c.getOrder());
                } else continue;
            }
            if (q.peek().getPath()[q.peek().getCurrenLocation() + 1] == c.getOrder().getPath()[c.getOrder(0).getCurrenLocation() + 1]) {
                q.add(c.getOrder())
            }
        }
        c.getOrder().getPath()[getCurrenLocation()].arrive(c);
    }

    private void checkedOut(Courier c) {
        ArrayList<Order> thingsToSend = new ArrayList<Order>();
        int weight = this.packages.get(robinpointer).peek().getWeight();
        while (weight < c.getCapacity()) {
            for (int i = 0; i < this.packages.size(); i++) {
                robinpointer = (robinpointer + 1) % this.packages.size();
                Order p = this.packages.get(robinpointer).peek();
                if (p != null) {
                    thingsToSend.add(p);
                    this.onDelivery.add(p);
                    this.packages.get(robinpointer).remove(p);
                    break;
                }
            }
        }
        this.outMan.add(c);
        this.freeMan.remove(c);
        if (thingsToSend.size() == 0) {
            System.out.println("no package");
        } else if (checkLastDelivery(thingsToSend.get(0))) {
            c.sendToHome(thingsToSend);
        } else {
            c.sendToBranch(thingsToSend);
        }
    }

    public void arrive(Courier c) {
        this.onDelivery.remove(c.getOrder());
        this.outMan.remove(c);
        this.freeMan.add(c);
    }

    private boolean checkLastDelivery(Order o) {
        if (Math.abs(o.getAddress()[0] - this.location[0]) + Math.abs(o.getAddress()[1] - this.location[1]) <= this.capacity) {
            return true;
        } else return false;
    }

    public String toString() {
        return this.name;
    }

    public void updateNeighbour(Branch b) {
        this.neighbour.add(b);
        this.packages.add(new OrderCollection(1));// with new id
    }


}
