package ems;

//import java.util.ArrayList;
//import java.security.MessageDigest;

public class Manager {


	private int id;
	private String name;
	private int pwHash;
	private String gender = "Not Know";
	private int status = 1;

	private Company company;
	private OrderPool orderPool;

	public Manager(int id, String name, String password, String gender, int status) {

		this.id = id;
		this.name = name;
		this.pwHash = password.hashCode();
		this.gender = gender;
		this.status = status;

		this.company = Company.getInstance();

		System.out.println("Express Company Manager " + name + " (level:" + status + ") created!");
	}


	public int getId() {
		return this.id;
	}

	public int getStatus() {
		return this.status;
	}

	public Branch addBranch(String name) {
		return company.addBranch(name);
	}

	public Branch removeBranch(int id) {
		return company.removeBranch(id);
	}

	public void changeBranchConnections() {
//		TODO: increase or decrease the connection weights between branch offices.
	}
}
