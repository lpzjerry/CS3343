package ems;

public class SearchBranchCmd implements Command {
    private Company theCompany;
    private String[] param;

    public SearchBranchCmd(Company cmp, String para[]) {
        this.theCompany = cmp;
        this.param = para;
    }

    @Override
    public void execute() {
        Position position = new Position(Integer.valueOf(param[1]), Integer.valueOf(param[2]));
        this.theCompany.getBranchByLocation(position);
    }

}
