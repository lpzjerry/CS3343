package ems;

public class AddManagerCmd implements Command {
	private Company theCompany;
	private String[] param;
	
	public AddManagerCmd(Company cmp,String para[]) {
	this.theCompany = cmp;
	this.param = para;			
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.theCompany.addNewManager(param[1], param[2],param[3], Integer.valueOf(param[4]));

	}

}
