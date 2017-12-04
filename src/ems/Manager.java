package ems;

//import java.util.ArrayList;
//import java.security.MessageDigest;
/**
 * <h1>Manager</h1>
 * The manager can manage the company, which involves add and remove
 * customer, branch, linkage another manager.
 * 
 *@version v0.2-beta
 *
 * */
public class Manager {


    private int id;
    private String name;
    //    private int pwHash;
//    private String gender = "Not Know";
    private int status = 1;

    private Company company;
//    private OrderPool orderPool;
/**
 *This is the manager constructor. It will output the log after the construction.
 *@param id The id of Manager.
 *@param name The name of Manager.
 *@param password The password for the Manager to log in.
 *@param gender The gender of the Manager.
 *@param status The level of the Manager post.
 */
    public Manager(int id, String name, String password, String gender, int status) {

        this.id = id;
        this.name = name;
//       this.pwHash = password.hashCode();
//        this.gender = gender;
        this.status = status;

        this.company = Company.getInstance();

        System.out.println("Express Company Manager " + name + " (level:" + status + ") created!");
    }
/**
 * This is the id getter.
 * @return id
 * */
    public int getId() {
        return this.id;
    }
    /**
     * This is the id getter.
     * @return id
     * */
    public int getStatus() {
        return this.status;
    }

//	public Branch addBranch(String name) {
//		return company.addBranch(name);
//	}
//    TMP removed by patrick wang 11-24
    /**
     * This method is used to add branch to the company.
     * @param name The name of the branch to add.
     * @param position The position of the branch to add.
     * @return The branch which is added.
     * */
    public Branch addBranch(String name, Position position) {
        return company.addBranch(name, position);
    }
    /**
     * This method is used to remove branch from the company.
     * @param id The id of the branch to remove.
     * @return The branch which is removed.
     * */
    public Branch removeBranch(int id) {

        return company.removeBranch(id);
    }
    /**
     * This method is used to add customer to the company.
     * @param name The name of the customer to add.
     * @param password The password for the customer to log in.
     * @param priority The priority of the customer.
     * @param position The position of the customer.
     * @return The customer which is added.
     * */
    public Customer addCustomer(String name, String password, int priority, Position position) {
        return Company.getInstance().addCustomer(name, password, priority, position);
    }
    /**
     * This method is used to add customer to the company.
     * @param name The name of the customer to add.
     * @param password The password for the customer to log in.
     * @return The customer which is added.
     * */
    public Customer addCustomer(String name, String password) {
        return Company.getInstance().addCustomer(name, password);
    }
    /**
     * This method is check whether the manager is the super manager.
     * @return True or False.
     * */
    public boolean rootOrNot() {
        return status == 0;
    }
    /**
     * This method is used to add linkage between branches to the company.
     * @param source The id of the source branch.
     * @param target The id of the target branch.
     * @return The linkage which is added.
     * */

    public boolean addLinkage(int source, int target) {
        return Company.getInstance().addLinkage(source, target);
    }
    
    /**
     * This method is used to remove linkage between branches to the company.
     * @param source The id of the source branch.
     * @param target The id of the target branch.
     * @return Whether the linkages have been removed.
     * */

    public boolean rmLinkage(int source, int target) {
        return Company.getInstance().rmLinkage(source, target);
    }
    /**
     * This method is used to add manager to the company.
     * @param name The name of the manager to add.
     * @param password The password for the manager to log in.
     * @param gender The gender of the manager.
     * @param status The status of the manager to add.
     * @return The manager which is added.
     * */
    public Manager addNewManager(String name, String password, String gender, int status) {
        if (this.rootOrNot()) {
            return this.company.addNewManager(name, password, gender, status);
        } else {
            System.out.println("permision denied");
            return null;
        }
    }
    
    /**
     * This method is override the original toString() method
     * @return The string of the manager name information.
     * */
    public String toString() {
        return "[manager " + this.name + "]";
    }
    
    /**
     * This method is used to remove a given manager by id.
     * @param id The id of the manager to remove.
     * @return The removed manager.
     * */
    public Manager removeManager(int id) {
        return this.company.removeManager(id);
    }
}
