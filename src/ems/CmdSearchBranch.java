package ems;

public class CmdSearchBranch implements Command {
    private Company theCompany;
    private String[] param;

    public CmdSearchBranch(Company cmp, String para[]) {
        this.theCompany = cmp;
        this.param = para;
    }

    @Override
    public void execute() {
        Position position = new Position(Integer.valueOf(param[1]), Integer.valueOf(param[2]));
        System.out.println(this.theCompany.getBranchByLocation(position));
    }

}
