package ems;

//import java.util.ArrayList;

public class Manager {
	
	
	private int id;
	private String name;
	private String password;
	private String gender = "Not Know";
	private int status = 1;
	
	private Company company;
	private OrderPool orderPool;
	
	public Manager(int id, String name, String password, String gender, int status) {
		this.id = id; 
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.status = status;
		
		this.company = Company.getInstance();
		
		System.out.println("Express Company Manager " + name + " (level:"+ status + ") created!");
	}
	
	
	public int getId() {return this.id;}
	
	public int getStatus() {return this.status;}

	public Branch addBranch(String name){
		return company.addBranch(name);
	}

	public Branch removeBranch(int id){
		return company.removeBranch(id);
	}

	public void changeBranchConnections(){
//		TODO: increase or decrease the connection weights between branch offices.
	}
	

}
