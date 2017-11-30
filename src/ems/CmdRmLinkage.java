package ems;

public class CmdRmLinkage implements Command {
    private Manager manager;
    private int source;
    private int target;

    public CmdRmLinkage(Manager theManager, String[] cmd) {
        this.manager = theManager;
        this.source = Integer.parseInt(cmd[1]);
        this.target = Integer.parseInt(cmd[2]);
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        if(this.manager.rmLinkage(source, target))
        System.out.println("edge deleted from: "+source+", to: "+target);
    }

}
