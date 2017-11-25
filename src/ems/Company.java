package ems;


import java.util.*;
import java.util.stream.Collectors;

public class Company {

    private HashMap<Integer, Manager> managerList;
    private HashMap<Integer, Branch> branchList;
    private HashMap<Integer, Customer> customerList;
    private int branchId;

    private OrderPool orderPool;
    private Date companyClock;


    /**
     * while company's init, create a user with full priviledges.
     */
    private Company() {
        this.orderPool = OrderPool.getInstance();
        this.branchList = new HashMap<>();
        this.managerList = new HashMap<>();
        this.customerList = new HashMap<Integer, Customer>();
        this.companyClock = new Date();
        Manager superuser = new Manager(0, "superuser", "123456", "nil", 0);
        this.managerList.put(superuser.getId(), superuser);

        this.branchId = 1;
    }

    private static Company instance = new Company();

    private SortedSet<Branch> sortBranchesByDestination(Iterable<Branch> branches, Branch source, Branch destination) {
        SortedSet<Branch> sortedEntries = new TreeSet<Branch>(
                (leftBranch, rightBranch) -> {
                    int leftDistance = leftBranch.getDistance(source);
                    int rightDistance = rightBranch.getDistance(source);

                    return Integer.compare(leftDistance, rightDistance);
                }
        );

        for (Branch branch : branches) {
            int total_distance = branch.getDistance(destination) + branch.getDistance(source);
            int least_distance = Position.distance(source.getLocation(), destination.getLocation());
            // sort all branches exclude itself
            // if the path through this branch will not increase total amount of distance: means the correct distance
            // This branch is nearer to destination
            if (least_distance <= total_distance && branch.getDistance(source) != 0) sortedEntries.add(branch);
        }
        return sortedEntries;
    }

    public Branch neighbourForBranch(Branch me, Branch destination) {
        ArrayList<Branch> neighbourBranches = new ArrayList<>();
        this.branchList.forEach((id, branch) -> neighbourBranches.add(branch));
        SortedSet<Branch> sortedBranches = this.sortBranchesByDestination(neighbourBranches, me, destination);
        // find if there are middle station(branch) on the way
        // if no, directly goes to destination :(
        Branch result;
        try {
            result = sortedBranches.first();
        } catch (NoSuchElementException e) {
            result = null;
        }

        return result;
    }

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

        Branch dummyDestinationBranch = new Branch(0, "", receiver.getPosition());

//        create dummy branches(with position of sender/receiver), these dummy branches will be GC after.
        Branch nextBranch = this.neighbourForBranch(new Branch(0, "", sender.getPosition()),
                dummyDestinationBranch);

        while (nextBranch != null) {
            path.add(nextBranch.getLocation());
            nextBranch = this.neighbourForBranch(nextBranch, dummyDestinationBranch);
        }

        path.add(receiver.getPosition());

        // GC
        dummyDestinationBranch = null;
        return this.orderPool.createOrder(itemName, sender, receiver, path);
    }

    public Branch addBranch(String name, Position position) {
        int id = this.branchId++;
        Branch branch = new Branch(id, name, position);
        return branchList.put(id, branch);
    }

    public Branch removeBranch(int id) {
        return branchList.remove(id);
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
