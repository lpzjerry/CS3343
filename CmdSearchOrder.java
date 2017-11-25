package ems;

public class CmdSearchOrder implements Command {
    private Company theCompany;
    private String[] param;

    public CmdSearchOrder(Company cmp, String para[]) {
        this.theCompany = cmp;
        this.param = para;
    }

    @Override
    public void execute() {
        Position locOfOrder=this.theCompany.searchOrder(param[1]).currentLocation();
        Branch result = this.theCompany.getBranchByLocation(locOfOrder);
        System.out.println(result);
    }

}
