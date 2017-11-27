package ems;

public class CmdSearchOrder implements Command {
    private Company theCompany;
    private String[] param;

    public CmdSearchOrder(Company cmp, String para[]) {
        this.theCompany = cmp;
        this.param = para;
    }

    @Override
    public void execute() {
        // Order order = this.theCompany.searchOrder(Integer.parseInt(param[1]));
        String orderKey = param[1];
        Order order = theCompany.searchOrder(orderKey);
        if(order == null)
            order = theCompany.searchOrder(Integer.parseInt(orderKey));
        if (order.isReceived()) System.out.println("Order " + order + " has been received.");
        else {
            System.out.println(order);
            Position locOfOrder = order.currentLocation();
            Branch result = this.theCompany.getBranchByLocation(locOfOrder);
            System.out.println(result);
        }
    }
}