package ems;

//import java.util.ArrayList;
//import java.security.MessageDigest;

public class Manager {


    private int id;
    private String name;
    //    private int pwHash;
//    private String gender = "Not Know";
    private int status = 1;

    private Company company;
//    private OrderPool orderPool;

    public Manager(int id, String name, String password, String gender, int status) {

        this.id = id;
        this.name = name;
//       this.pwHash = password.hashCode();
//        this.gender = gender;
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

//	public Branch addBranch(String name) {
//		return company.addBranch(name);
//	}
//    TMP removed by patrick wang 11-24

    public Branch addBranch(String name, Position position) {
        return company.addBranch(name, position);
    }

    public Branch removeBranch(int id) {

        return company.removeBranch(id);
    }

    public Customer addCustomer(String name, String password, int priority, Position position) {
        return Company.getInstance().addCustomer(name, password, priority, position);
    }

    public Customer addCustomer(String name, String password) {
        return Company.getInstance().addCustomer(name, password);
    }

    public boolean rootOrNot() {
        return status == 0;
    }

    public boolean addLinkage(int source, int target) {
        return Company.getInstance().addLinkage(source, target);
    }

    public boolean rmLinkage(int source, int target) {
        return Company.getInstance().rmLinkage(source, target);
    }

    public Manager addNewManager(String name, String password, String gender, int status) {
        if (this.rootOrNot()) {
            return this.company.addNewManager(name, password, gender, status);
        } else {
            System.out.println("permision denied");
            return null;
        }
    }

    public String toString() {
        return "[manager " + this.name + "]";
    }

    public Manager removeManager(int id) {
        return this.company.removeManager(id);
    }
}
