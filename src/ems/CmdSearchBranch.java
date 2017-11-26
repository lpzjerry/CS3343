package ems;

public class CmdSearchBranch implements Command {
    private Company theCompany;
    private Position pos=null;

    public CmdSearchBranch(Company cmp, String para[]) {
        this.theCompany = cmp;
        if(para.length==3){
        	this.pos=new Position(Integer.valueOf(para[1]), Integer.valueOf(para[2]));
        }
    }

    @Override
    public void execute() {
        if(this.pos==null){
        	System.out.println("invalid arguments!");
        }
        else{
        	System.out.println(this.theCompany.getBranchByLocation(this.pos));
        }
        
    }

}
