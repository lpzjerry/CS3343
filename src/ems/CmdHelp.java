package ems;

public class CmdHelp implements Command{
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("Cmd:");
		System.out.println("------------------------------------------Common_Commands------------------------------------------");
		System.out.println("\thelp: show the available command");
		System.out.println("\tcheckTime: show the time");
		System.out.println("\tsearchBranch <x-corodinate> <y-corodinate>: search the Branch by location");
		System.out.println("\thelp: show the available command");
		System.out.println("\texit: exit the system");
		System.out.println("------------------------------------------End_of_Common_cmd------------------------------------------");
		System.out.println("\n------------------------------------------Basic_UI_Commands------------------------------------------");
		System.out.println("\tmanager <id>: login as manager with id");
		System.out.println("\tcustomer <id>: login as customer with id");
		System.out.println("------------------------------------------End_of_Common_cmd------------------------------------------");
		System.out.println("\n------------------------------------------Manager_Commands------------------------------------------");
		System.out.println("\taddBranch <name> <x-corodinate> <y-corodinate>: add branch with name, and position");
		System.out.println("\taddCustomer <name> <password> <priority> <x-corodinate> <y-corodinate>: add customer with infomation");
		System.out.println("\taddLink <source_id> <target_id>: add directed linkage from source branch to target branch");
		System.out.println("\tsearchOrder <id>: trace where the order is");
		System.out.println("\trmBranch <id>: remove branch with id");
		System.out.println("\trmLink <source_id> <target_id>: remove directed linkage from source branch to target branch");
		System.out.println("\tlogout: logout to basic UI command");
		System.out.println("\t[Command only for super maanger]:");
		System.out.println("\t\taddManager <name> <password> <gender> <rank>: add new manager with the information");
		System.out.println("\t\trmManager <id>: remove manager with id");
		System.out.println("------------------------------------------End_of_Manager_cmd------------------------------------------");
		System.out.println("\n------------------------------------------Customer_Commands------------------------------------------");
		System.out.println("\tcreateOrder <name> <target_id>: create a new order to send");
		System.out.println("\tsearchOrder <id>: trace where the order is");
		System.out.println("\tlogout: logout to basic UI command");
		System.out.println("------------------------------------------End_of_Customer_cmd------------------------------------------");
	}

}
