package ems;
/**
 * <h1>Company</h1>
 * The company contains all the overall information about the system, including
 * the managerlist, branchlist, customerlist, the map of the delivery system. And 
 * it can modify the information via calling the methods.
 *@version v0.2-beta
 *
 * */

import java.util.*;
import java.util.stream.Collectors;

public class Company {

    private HashMap<Integer, Manager> managerList;
    private HashMap<Integer, Branch> branchList;
    private HashMap<Integer, Customer> customerList;
    //private HashMap<Integer,ArrayList<Integer>> adjacency;
    private Graph map;
    private List<Edge> edges;
    private int branchId;
    private OrderPool orderPool;
    private Date companyClock;
    /**
     * This method is used to add linkage between branches to the company.
     * @param source The id of the source branch.
     * @param target The id of the target branch.
     * @return Whether the linkage is successfully added.
     * */
    public boolean addLinkage(int source, int target) {
        if (this.branchList.containsKey(source) && this.branchList.containsKey(target)) {
            int distance = this.branchList.get(source).getDistance(this.branchList.get(target));
            Edge edge = new Edge(("(" + source + ", " + target + ")"), this.branchList.get(source), this.branchList.get(target), distance);
            this.edges.add(edge);
            return true;
        }
        System.out.println("source or target does not exist");
        return false;
    }
    /**
     * This method is reset the company.
     * */
    public void reset() {
    		this.orderPool.reset();;
         this.branchList.clear();
         this.managerList.clear();
         this.customerList.clear();
         //this.adjacency=new HashMap<Integer,ArrayList<Integer>>();
         this.companyClock = new Date();
         //Manager superuser = new Manager(0, "superuser", "123456", "nil", 0);
         //this.managerList.put(superuser.getId(), superuser);
         this.edges.clear();
         this.map = new Graph(new ArrayList<Branch>(), this.edges);
         this.branchId = 1;
    }
    /**
     * This method is used remove linkage from the company's map.
     * @param source The id of the source branch.
     * @param target The id of the target branch.
     * @return Whether the linkage is successfully removed.
     * */
    public boolean rmLinkage(int source, int target) {
        ArrayList<Edge> edgeToRemove = new ArrayList<Edge>();
        if (this.branchList.containsKey(source) && this.branchList.containsKey(target)) {
            for (Edge e : (ArrayList<Edge>) this.edges) {
                if (e.getSource() == this.branchList.get(source) && e.getDestination() == this.branchList.get(target)) {
                    edgeToRemove.add(e);
                }

            }
            if (edgeToRemove.size() == 0) {
                System.out.println("edge does not exist");
                return false;
            }
            this.edges.removeAll(edgeToRemove);
            return true;
        }
        System.out.println("source or target does not exist");
        return false;
    }
//    public boolean connectOrNot(int source, int target){
//    	return this.adjacency.get(source).contains(target)&&this.adjacency.get(target).contains(source);
//    }
    /**
     *This is the company constructor. 
     */
    private Company() {
        this.orderPool = OrderPool.getInstance();
        this.branchList = new HashMap<>();
        this.managerList = new HashMap<>();
        this.customerList = new HashMap<Integer, Customer>();
        //this.adjacency=new HashMap<Integer,ArrayList<Integer>>();
        this.companyClock = new Date();
        //Manager superuser = new Manager(0, "superuser", "123456", "nil", 0);
        //this.managerList.put(superuser.getId(), superuser);
        this.edges = new ArrayList<Edge>();
        this.map = new Graph(new ArrayList<Branch>(), this.edges);
        this.branchId = 1;
    }

    private static Company instance = new Company();
    /**
     *This is the company get singleton instance method.
     *@return The singleton instance.
     */
    public static Company getInstance() {
        return instance;
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
        Manager manager = new Manager(managerList.size(), name, password, gender, status);

        this.managerList.put(manager.getId(), manager);
        return manager;
    }
    /**
     * This method is used to create new orders and plan the route of delivery automatically.
     * @param itemName The name of the order.
     * @param sender The sender customer.
     * @param receiver The receiver customer.
     * @return The new order's id or the error information.
     * */
    public int createOrder(String itemName, Customer sender, Customer receiver) {
        ArrayList<Position> path = new ArrayList<>();
        Position s = sender.getPosition();
        Position v = receiver.getPosition();
        int min = 100000;
        Branch senderSide = null;
        Branch receiverSide = null;
        for (Map.Entry<Integer, Branch> entry : this.branchList.entrySet()) {
            int d = entry.getValue().getDistance(new Branch(0, "", s));
            if (d <= min) {
                min = d;
                senderSide = entry.getValue();
            }
        }
        min = 100000;
        for (Map.Entry<Integer, Branch> entry : this.branchList.entrySet()) {
            int d = entry.getValue().getDistance(new Branch(0, "", v));
            if (d <= min) {
                min = d;
                receiverSide = entry.getValue();
            }
        }
        if(senderSide==null){
        	System.out.println("no branch exist");
        	return -1;
        }
        if(senderSide==receiverSide){
        	path.add(senderSide.getLocation());
        	return this.orderPool.createOrder(itemName, sender, receiver, path);
        }
        Dijkstra dijkstra = new Dijkstra(this.map);
        dijkstra.execute(senderSide);
        ArrayList<Branch> pathOfBranch = dijkstra.getPath(receiverSide);
        if (pathOfBranch==null) {
            System.out.println("Unreachable");
            return -1;
        }
        
        for (Branch b : pathOfBranch) {
            path.add(b.getLocation());

        }
   //     if (path.size() == 0) {
   //         System.out.println("Unreachable");
   //        return -1;
    //    }
        return this.orderPool.createOrder(itemName, sender, receiver, path);
    }
    /**
     * This method is used to add a new branch to the company.
     * @param name The name of the branch.
     * @param position The position of the branch.
     * @return The branch which is added.
     * */
    public Branch addBranch(String name, Position position) {
        int id = this.branchId++;
        Branch branch = new Branch(id, name, position);

        List<Branch> bList = new ArrayList<Branch>();
        bList.add(branch);
        for (Map.Entry<Integer, Branch> entry : this.branchList.entrySet()) {
            bList.add(entry.getValue());
        }
        this.map = new Graph(bList, this.edges);
        return branchList.put(id, branch);
    }
    /**
     * This method is used to remove a branch from the company.
     * @param id The id of the branch.
     * @return The branch which is removed.
     * */
    public Branch removeBranch(int id) {

        Branch toRemove = branchList.remove(id);

        ArrayList<Edge> edgeToRemove = new ArrayList<Edge>();
        for (Edge e : this.edges) {
            if (e.getDestination() == toRemove || e.getSource() == toRemove) {
                edgeToRemove.add(e);
            }
        }

        for (Edge e : edgeToRemove) {
            this.edges.remove(e);
        }

        List<Branch> bList = new ArrayList<Branch>();
        for (Map.Entry<Integer, Branch> entry : this.branchList.entrySet()) {
            bList.add(entry.getValue());
        }

        this.map = new Graph(bList, this.edges);

        return toRemove;
    }
    /**
     * This method is used to search the order by id.
     * @param id The id of the order.
     * @return The order which is searched.
     * */
    public Order searchOrder(int id) {
        return orderPool.getOrderById(id);
    }
    /**
     * This method is used to search the order by name.
     * @param name The name of the order.
     * @return The order which is searched.
     * */
    public Order searchOrder(String name) {
        return orderPool.getOrderByName(name);
    }

    /**
     * This method is used to change the status of a order after it has been received.
     * @param order The order which is received.
     * */
    public void receiveOrder(Order order) {
        orderPool.receiveOrder(order);
    }

    /**
     * This method is used to get the time of the company after creation.
     * @return The time after the company is created(in millisecond)
     */
    public long getTime() {
        return new Date().getTime() - this.companyClock.getTime();
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
        Customer customer = new Customer(customerList.size(), name, password, priority, position);
        this.customerList.put(customer.getId(), customer);
        return customer;
    }
    /**
     * This method is used to add customer to the company.
     * @param name The name of the customer to add.
     * @param password The password for the customer to log in.
     * @return The customer which is added.
     * */
    public Customer addCustomer(String name, String password) {
        Customer customer = new Customer(customerList.size(), name, password);
        this.customerList.put(customer.getId(), customer);
        return customer;
    }
    /**
     * This method is used to search the branch by position.
     * @param position The position of the branch.
     * @return The branch which is searched.
     * */
    public Branch getBranchByLocation(Position position) {

        Map<Integer, Branch> tmp_container = this.branchList.entrySet().stream()
                .filter(map -> map.getValue().getLocation().distance(position) == 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//        there are probably more than one branches on this location
//        return tmp_container to get all branches on this location.
        return tmp_container.entrySet().iterator().next().getValue();
    }
    /**
     * This method is used to search the manager by id.
     * @param id The id of the manager.
     * @return The manager which is searched.
     * */
    public Manager getManager(int id) {
        return this.managerList.get(id);
    }
    /**
     * This method is used to remove the manager by id.
     * @param id The id of the manager.
     * @return The manager which is removed.
     * */
    public Manager removeManager(int id) {
        return this.managerList.remove(id);
    }
    /**
     * This method is used to get the customer by id.
     * @param id The id of the customer.
     * @return The customer which is required.
     * */
    public Customer getCustomer(int id) {
        return this.customerList.get(id);
    }
}

//    private SortedSet<Branch> sortBranchesByDestination(Iterable<Branch> branches, Branch source, Branch destination) {
//       SortedSet<Branch> sortedEntries = new TreeSet<Branch>(
//                (leftBranch, rightBranch) -> {
//                    int leftDistance = leftBranch.getDistance(source);
//                    int rightDistance = rightBranch.getDistance(source);
//
//                    return Integer.compare(leftDistance, rightDistance);
//                }
//        );

//        for (Branch branch : branches) {
//            int total_distance = branch.getDistance(destination) + branch.getDistance(source);
//            int least_distance = Position.distance(source.getLocation(), destination.getLocation());
// sort all branches exclude itself
// if the path through this branch will not increase total amount of distance: means the correct distance
// This branch is nearer to destination
//            if (least_distance <= total_distance && branch.getDistance(source) != 0) sortedEntries.add(branch);
//        }
//        return sortedEntries;
//    }

//    public Branch neighbourForBranch(Branch me, Branch destination) {
//        ArrayList<Branch> neighbourBranches = new ArrayList<>();
//        this.branchList.forEach((id, branch) -> neighbourBranches.add(branch));
//        SortedSet<Branch> sortedBranches = this.sortBranchesByDestination(neighbourBranches, me, destination);
//        // find if there are middle station(branch) on the way
// if no, directly goes to destination :(
//        Branch result;
//        try {
//            result = sortedBranches.first();
//        } catch (NoSuchElementException e) {
//            result = null;
//        }

//        return result;
//    }
