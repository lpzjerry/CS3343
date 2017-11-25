package ems;

//import java.util.logging.Logger;

public class CmdAddCustomer implements Command {
    private Manager manager;
    private String name;
    private String pass;
    private int prior = -1;
    private Position pos = null;

    //    TODO throw exception, may nor implement yet??
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
            System.out.println("argument error"); // TODO features changed to log
        }
    }

    public void execute() {
        if (prior == -1) {
            this.manager.addCustomer(name, pass);
        } else {
            this.manager.addCustomer(name, pass, prior, pos);
        }

    }
}
