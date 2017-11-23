package ems;

public class RmManagerCmd implements Command{
	private Company c;
	private int id;
	public RmManagerCmd(Company c,String[] cmd){
		if(cmd.length==2){
			this.c=c;
			this.id=Integer.parseInt(cmd[1]);
		}
		else{
			System.out.println("argument error");
		}
	}
	
	public void execute(){
		this.c.removeManager(id);
	}

}
