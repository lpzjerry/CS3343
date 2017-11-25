package ems;

public class CmdAddBranch implements Command {
    private Manager manager;
    private Position pos;
    private String name;

    public CmdAddBranch(String para[],Manager theManager) {
        this.manager=theManager;
        this.pos = new Position(Integer.parseInt(para[3]), Integer.parseInt(para[4]));
        this.name = para[1];
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        this.manager.addBranch(name, pos);
    }

}
