package ems;

public class CmdCreateOrder implements Command {
	private Company company;
    private Customer sender;
    private Customer receiver;
    private String itemName;
    
    public CmdCreateOrder(Company company, String para[],Customer sender) {
    	this.company=company;
        this.sender = sender;
        this.itemName=para[1];
        this.receiver=this.company.getCustomer(Integer.parseInt(para[2]));

    }

    @Override
    public void execute() {
        this.company.createOrder(itemName, sender, receiver);    	
    }

}
