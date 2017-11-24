package ems;

public class SearchOrderCmd implements Command {
    private Company theCompany;
    private String[] param;

    public SearchOrderCmd(Company cmp, String para[]) {
        this.theCompany = cmp;
        this.param = para;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        this.theCompany.searchOrder(param[1]);
//		what's the purpose of this execute?????????????
    }

}
