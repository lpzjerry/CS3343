package ems;

public class AddCustomerCmd implements Command{
	private Company c;
	private String name;
	private String pass;
	private int prior=-1;
	private Position pos=null;
	public AddCustomerCmd(Company c,String[] cmd){
		if(cmd.length==6){
			this.c=c;
			this.name=cmd[1];
			this.pass=cmd[2];
			this.prior=Integer.parseInt(cmd[3]);
			this.pos=new Position(Integer.parseInt(cmd[4]),Integer.parseInt(cmd[5]));
		}
		else if(cmd.length==3){
			this.c=c;
			this.name=cmd[1];
			this.pass=cmd[2];
		}
		else{
			System.out.println("argument error");
		}
	}
	
	public void execute(){
		if(prior==-1){
			this.c.addCustomer(name, pass);
		}
		else{
			this.c.addCustomer(name, pass, prior, pos);
		}
	}
}
