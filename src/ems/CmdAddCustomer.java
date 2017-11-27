package ems;

public class CmdAddCustomer implements Command {
    private Manager manager;
    private Customer customer;
    private String name;
    private String pass;
    private int prior = -1;
    private Position pos = null;

    public CmdAddCustomer(String[] cmd, Manager theManager) {
        this.manager = theManager;
        if (cmd.length == 6) {
            this.name = cmd[1];
            this.pass = cmd[2];
            this.prior = Integer.parseInt(cmd[3]);
            this.pos = new Position(Integer.parseInt(cmd[4]), Integer.parseInt(cmd[5]));
        } else if (cmd.length == 3) {
            this.name = cmd[1];
            this.pass = cmd[2];
        } else {
            System.out.println("argument error");
            return;// TODO features changed to log
        }
    }

    public void execute() {
        if (prior == -1) {
            this.customer = this.manager.addCustomer(name, pass);
        } else {
            this.customer = this.manager.addCustomer(name, pass, prior, pos);
        }
        System.out.println(this.customer + " is added by: " + this.manager);
    }
}
