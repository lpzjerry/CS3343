package ems;

import java.util.HashMap;

public class Company {
	
	private Company() {}
	
	public static Company getInstance() {
		return instance;
	}
	
	public static Company instance = Company();

	private HashMap<Integer, Manager> managerList;
	
//	example:
	
	Manager m1 = Manager("Patrick", "1234", "m", 1);
	
	private 
	
	
}
