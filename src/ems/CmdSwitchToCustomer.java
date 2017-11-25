package ems;

public class CmdSwitchToCustomer implements Command {
    private Customer reference;
    private int id;
    private Customer customer;

    public CmdSwitchToCustomer(Company company, String[] cmd, Customer theCustomer) {
        this.reference = theCustomer;
        this.id = Integer.parseInt(cmd[1]);
        this.customer = company.getCustomer(this.id);
    }

    @Override
    public void execute() {
        this.reference = this.customer;
//        TODO what can this method do...
    }

}
