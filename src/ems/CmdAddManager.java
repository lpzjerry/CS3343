package ems;

public class CmdAddManager implements Command {
    private Manager employer;
    private Manager employee;
    private String[] param;

    public CmdAddManager(Manager manager, String para[]) {
        this.employer = manager;
        this.param = para;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        this.employee = this.employer.addNewManager(param[1], param[2], param[3], Integer.valueOf(param[4]));
        System.out.println(this.employee + " is added by " + this.employer);
    }
}
