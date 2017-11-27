package ems;

public class CmdAddBranch implements Command {
    private Manager manager;
    private Position pos;
    private String name;

    public CmdAddBranch(String para[], Manager theManager) {
        this.manager = theManager;
        this.pos = new Position(Integer.parseInt(para[2]), Integer.parseInt(para[3]));
        this.name = para[1];
    }

    @Override
    public void execute() {
        this.manager.addBranch(name, pos);
        System.out.println("Branch: " + Company.getInstance().getBranchByLocation(this.pos) + " is added by: " + this.manager);
    }

}
