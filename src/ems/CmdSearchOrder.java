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
        Order order = this.theCompany.searchOrder(Integer.parseInt(param[1]));
        if (order.isReceived()) System.out.println("Order " + order + " is received");
        else {
            System.out.println(order);
            Position locOfOrder = order.currentLocation();
            Branch result = this.theCompany.getBranchByLocation(locOfOrder);
            System.out.println(result);
        }
    }
}