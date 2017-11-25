package ems;

public class CmdRmBranch implements Command {
    private Manager manager;
    private int id;

    public CmdRmBranch(String[] cmd,Manager theManager) {
        if (cmd.length == 2) {
            this.manager = theManager;
            this.id = Integer.parseInt(cmd[1]);
        } else {
            System.out.println("argument error"); // TODO can we change them into log or throw exception???
        }
    }

    public void execute() {
        this.manager.removeBranch(id);
    }
}
