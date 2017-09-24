package ems;

import java.util.HashMap;

public class Company {
	
	private HashMap<Integer, Manager> managerList = new HashMap<Integer, Manager>();
	
	private OrderList orderList;
	
	
	/**
	 * while company's init, create a user with full priviledges.
	 */
	private Company() {
		this.orderList = OrderList.getInstance();
		
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
	
	public OrderList getOrderList() {
		return this.orderList;
	}
	
	
}
