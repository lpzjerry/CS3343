package ems;

//import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
	
	private String name;
	private String password;
	private String gender = "Not Know";
	private int status = 1;
	
//	private static Map<Integer,String> statusMap ;
	
//	private ArrayList<> orderList;
	private HashMap<Integer, Order> orderList;
	
	
	public Manager(String name, String password, String gender, int status) {
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.status = status;
		
		System.out.println("Express Company Manager " + name + " (level:"+ status + ") created!");
	}
	
	public void setpriority(int id, int priority) {
		Order order = orderList.get(id);
		
	}
	

}
