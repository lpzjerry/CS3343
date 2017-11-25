package ems;

public class CmdRmManager implements Command {
    private Company company;
    private int id;

    public CmdRmManager(Company company, String[] cmd) {
        if (cmd.length == 2) {
            this.company = company;
            this.id = Integer.parseInt(cmd[1]);
        } else {
            System.out.println("argument error");
        }
    }

    public void execute() {
        this.company.removeManager(id);
    }

}
