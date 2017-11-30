package ems;


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
     * while company's init, create a user with full priviledges.
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

    public static Company getInstance() {
        return instance;
    }

    public Manager addNewManager(String name, String password, String gender, int status) {
        Manager manager = new Manager(managerList.size(), name, password, gender, status);

        this.managerList.put(manager.getId(), manager);
        return manager;
    }

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

    public Branch getBranchByLocation(Position position) {

        Map<Integer, Branch> tmp_container = this.branchList.entrySet().stream()
                .filter(map -> map.getValue().getLocation().distance(position) == 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//        there are probably more than one branches on this location
//        return tmp_container to get all branches on this location.
        return tmp_container.entrySet().iterator().next().getValue();
    }

    public Manager getManager(int id) {
        return this.managerList.get(id);
    }

    public Manager removeManager(int id) {
        return this.managerList.remove(id);
    }

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