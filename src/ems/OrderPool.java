package ems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
// TODO unittest

public class OrderPool {

    private HashMap<Integer, Order> orderList;

    private static OrderPool instance = new OrderPool();

    private int currentOrder = 0;

    private OrderPool() {
        this.orderList = new HashMap<Integer, Order>();
    }

    public static OrderPool getInstance() {
        return instance;
    }

//    public int addOrderToList(Order order) {
//        this.currentOrder++;
//        this.orderList.put(this.currentOrder, order);
//        return currentOrder;
//    }

    public Order getOrderById(int id) {
        return this.orderList.get(id);
    }

    public Order getOrderByName(String name) {
        for (Map.Entry<Integer, Order> entry : orderList.entrySet()) {
            Order order = entry.getValue();
            if (order.getItemName().equals(name)) {
                return order;
            }
        }
        return null;
    }

    int getCurrentOrder() {
        return currentOrder;
    }

    public void receiveOrder(Order order) {
        order.receiveOrder();
    }

    public int createOrder(String itemName, Customer sender, Customer receiver, ArrayList<Position> paths) {
        this.currentOrder++;
        Order order = new Order(this.currentOrder, itemName, sender, receiver, paths);
        this.orderList.put(this.currentOrder, order);
        return this.currentOrder;
    }

    public void processAllOrders() {
        for (Map.Entry<Integer, Order> entry : orderList.entrySet()) {
            Order order = entry.getValue();
            order.updatePositionByTime(Company.getInstance().getTime());
        }
    }
}
