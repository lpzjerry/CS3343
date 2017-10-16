package ems;

import java.util.HashMap;

public class Company {
	
	private HashMap<Integer, Manager> managerList = new HashMap<Integer, Manager>();

	private int branchId = 1;
	private HashMap<Integer, Branch> branchList = new HashMap<Integer, Branch>();
	private OrderPool orderPool;

	
	
	/**
	 * while company's init, create a user with full priviledges.
	 */
	private Company() {
		this.orderPool = OrderPool.getInstance();
		
		Manager superuser = new Manager(0, "superuser", "123456", "nil", 0);
		this.managerList.put(superuser.getId(), superuser);
		
	}
	
	private static Company instance = new Company();
	
	public static Company getInstance() {
		return instance;
	}
	
	
	public void addNewManager(int id, String name, String password, String gender, int status) {
		Manager m1 = new Manager(id, name, password, gender, status);
		
		this.managerList.put(m1.getId(), m1);
	}
	
	public void changeManagerStatus(int id, int status) {
		return;
	}
	
	
	public Manager getManagerById(int id) {
		return this.managerList.get(id);
	}
	
	public OrderPool getOrderPool() {
		return this.orderPool;
	}


	public Order CreateOrder(String itemName, Customer c) {
		//	   TODO: implement CreateOrder for Customer
        return new Order("",0);
	}

    public Branch addBranch(String name){
	    int id = this.branchId ++ ;
	    Branch branch = new Branch(id, name);
	    return branchList.put(id, branch);
    }

    public Branch removeBranch(int id){
        return branchList.remove(id);
    }

    public Order searchOrder(int id){
        return orderPool.getOrderById(id);
    }

    public Order searchOrder(String name){
        return orderPool.getOrderByName(name);
    }

}
