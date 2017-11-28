package ems;

public class CmdAddLinkage implements Command {
    private Manager manager;
    private int source;
    private int target;

    public CmdAddLinkage(Manager theManager, String[] cmd) {
        this.manager = theManager;
        this.source = Integer.parseInt(cmd[1]);
        this.target = Integer.parseInt(cmd[2]);
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        this.manager.addLinkage(source, target);
    }
}
