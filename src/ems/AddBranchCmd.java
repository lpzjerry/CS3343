package ems;

public class AddBranchCmd implements Command {
    private Company theCompany;
    //    private String[] param;
    private Position pos;
    private String name;
    private int id;

    public AddBranchCmd(Company cmp, String para[]) {
        this.theCompany = cmp;
//        this.param = para;
        this.pos = new Position(Integer.parseInt(para[3]), Integer.parseInt(para[4]));
        this.id = Integer.parseInt(para[2]);
        this.name = para[1];
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        this.theCompany.getManager(id).addBranch(name, pos);
    }

}
