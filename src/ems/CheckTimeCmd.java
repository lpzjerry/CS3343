package ems;

public class CheckTimeCmd implements Command{
	private Company c;
	public CheckTimeCmd(Company c,String[] cmd){
		this.c=c;
	}
	public void execute(){
		long re=c.getTime();
		System.out.println("It is "+re);
	}
}
