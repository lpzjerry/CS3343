package ems;


import java.util.HashMap;
import java.util.Date;
import java.util.ArrayList;

public class Company {

    private HashMap<Integer, Manager> managerList;
    private HashMap<Integer, Branch> branchList;
    private HashMap<Integer, Customer> customerList;
    private int branchId = 1;

    private OrderPool orderPool;
    private Date companyClock;


    /**
     * while company's init, create a user with full priviledges.
     */
    private Company() {
        this.orderPool = OrderPool.getInstance();
        this.branchList = new HashMap<>();
        this.managerList = new HashMap<>();
        this.customerList = new HashMap<Integer, Customer>();
        this.companyClock = new Date();
        Manager superuser = new Manager(0, "superuser", "123456", "nil", 0);
        this.managerList.put(superuser.getId(), superuser);
    }

    private static Company instance = new Company();

    public static Company getInstance() {
        return instance;
    }


    public Manager addNewManager(String name, String password, String gender, int status) {
        Manager manager = new Manager(managerList.size(), name, password, gender, status);

        this.managerList.put(manager.getId(), manager);
        return manager;
    }


    // Pengze Liu 2017-Nov-3
    public int createOrder(String itemName, Customer sender, Customer receiver) {
        // TODO generate path
        ArrayList<Position> path = new ArrayList<>();
        // TODO check whether ID is correctly assigned
        int ID = orderPool.getInstance().getCurrentOrder() + 1;
        return OrderPool.getInstance().addOrderToList(
                new Order(ID, itemName, sender, receiver, path));
    }


    public Branch addBranch(String name) {
        int id = this.branchId++;
        Branch branch = new Branch(id, name, null); // TODO implement location
        return branchList.put(id, branch);
    }


    public Branch removeBranch(int id) {
        return branchList.remove(id);
    }

    public Order searchOrder(int id) {
        return orderPool.getOrderById(id);
    }

    public Order searchOrder(String name) {
        return orderPool.getOrderByName(name);
    }

    public void receiveOrder(Order order) {
        orderPool.receiveOrder(order);
    }

    /**
     * @return type:long, return the time after the company is created(in millisecond)
     */
    public long getTime() {
        return new Date().getTime() - this.companyClock.getTime();
    }


    public Customer addCustomer(String name, String password, int priority, Position position) {
        Customer customer = new Customer(customerList.size(), name, password, priority, position);
        this.customerList.put(customer.getId(), customer);
        return customer;
    }

    public Customer addCustomer(String name, String password) {
        Customer customer = new Customer(customerList.size(), name, password);
        this.customerList.put(customer.getId(), customer);
        return customer;
    }


    // TODO implement this method (Pengze Liu 2017-Nov-3)
    public static Branch getBranchByLocation(Position position) {
        return null;
    }


}
