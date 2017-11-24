package ems;

public class RmBranchCmd implements Command {
    private Company c;
    private int id;

    public RmBranchCmd(Company c, String[] cmd) {
        if (cmd.length == 2) {
            this.c = c;
            this.id = Integer.parseInt(cmd[1]);
        } else {
            System.out.println("argument error"); // TODO can we change them into log or throw exception???
        }
    }

    public void execute() {
        this.c.removeBranch(id);
    }
}
