package ems;

public class CmdRmManager implements Command {
    private Manager employer;
    private Manager employee = null;
    private int id;

    public CmdRmManager(Manager manager, String[] cmd) {
        if (cmd.length == 2) {
            this.employer = manager;
            this.id = Integer.parseInt(cmd[1]);
        } else {
            System.out.println("argument error");
        }
    }

    public void execute() {
        this.employee = this.employer.removeManager(id);
        if (employee == null) {
            System.out.println("id does not exist!");
        } else {
            System.out.println(this.employee + " is removed by: " + this.employer);
        }
    }
}
