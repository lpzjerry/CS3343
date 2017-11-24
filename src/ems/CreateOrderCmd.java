package ems;

public class CreateOrderCmd implements Command {
	private Company theCompany;
	private String[] param;
	
	public CreateOrderCmd(Company cmp,String para[]) {
		this.theCompany = cmp;
		this.param = para;
		
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		int cusid1 = Integer.valueOf(param[2]);
		int cusid2 = Integer.valueOf(param[3]);
		Customer sender = this.theCompany.getCustomer(cusid1); // require getCustomer function in company
		Customer receiver = this.theCompany.getCustomer(cusid2);
		this.theCompany.createOrder(param[1], sender, receiver);

	}

}
