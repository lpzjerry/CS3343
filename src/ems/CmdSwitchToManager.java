package ems;

public class CmdSwitchToManager implements Command {
    private int id;
    private Manager manager;
    private Manager reference;

    CmdSwitchToManager(Company company, String[] cmd, Manager theManager) {
        this.reference = theManager;
        this.id = Integer.parseInt(cmd[1]);
        this.manager = company.getManager(this.id);
    }

    @Override
    public void execute() {
        this.reference = this.manager;
//        TODO what can this method do...
    }
}
