package ems;

public class CmdRmBranch implements Command {
    private Manager manager;
    private Branch branch = null;
    private int id;

    public CmdRmBranch(String[] cmd, Manager theManager) {

        if (cmd.length == 2) {
            this.manager = theManager;
            this.id = Integer.parseInt(cmd[1]);
        } else {
            System.out.println("argument error"); // TODO can we change them into log or throw exception???
        }
    }

    public void execute() {
        this.branch = this.manager.removeBranch(id);
        if (this.branch == null) {
            System.out.println("branch: " + this.id + " does not exist!");
        } else {
            System.out.println("branch: " + this.branch + " is removed by: " + this.manager);
        }
    }
}
