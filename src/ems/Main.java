package ems;

import java.util.Scanner;

public class Main {

// <<<<<<< sherlock
    Company c = Company.getInstance();
    OrderPool o=OrderPool.getInstance();
    public void main() {
//      TODO check arrival of Orders (by timer)
        Scanner reader = new Scanner(System.in);
        Invoker commandLogger = new Invoker();
        while (reader.hasNext()) {
        	String cmdLine = reader.nextLine().trim();

			// Blank lines exist in data file as separators. Skip them.
			if (cmdLine.equals(""))
				continue;

			System.out.println("\n> " + cmdLine);

			// split the words in actionLine => create an array of word
			// strings
			
			String[] cmdParts = cmdLine.split(" ");
			//[0]is cmd [1]is attribute 1 .. and so on...
            if (cmdParts[0].equals("searchBranch")) { // cmdParts= ["searchBranch","x","y"]
            		commandLogger.StoreAndExecute(new SearchBranchCmd(c,cmdParts));
            }
            else if(cmdParts[0].equals("searchOrder")){// cmdParts= ["searchOrder","id"]
            		commandLogger.StoreAndExecute(new SearchOrderCmd(c,cmdParts));
            }
           /* else if(cmdParts[0].equals("recieveOrder")){
            		commandLogger.StoreAndExecute(new ReceiveOrderCmd(c,cmdParts));
            }*/
            else if(cmdParts[0].equals("exit")){
            		break;
            }
            else if(cmdParts[0].equals("createOrder")){//cmdParts= ["createOrder","itemname","customer1ID"，"customer2ID"]
            	commandLogger.StoreAndExecute(new CreateOrderCmd(c,cmdParts));
            }
            else if(cmdParts[0].equals("addManager")){ // cmdParts= [ "name", "password", "gender", "status"]
            	commandLogger.StoreAndExecute(new AddManagerCmd(c,cmdParts));
            }
            else if(cmdParts[0].equals("addBranch")){// cmdParts= [ "name"]
            	commandLogger.StoreAndExecute(new AddBranchCmd(c,cmdParts));
            }
            else if(cmdParts[0].equals("addCustomer")){
            	commandLogger.StoreAndExecute(new AddCustomerCmd(c,cmdParts));
            }
            else if(cmdParts[0].equals("rmBranch")){
            	commandLogger.StoreAndExecute(new RmBranchCmd(c,cmdParts));
            }
            else if(cmdParts[0].equals("rmManager")){
            	commandLogger.StoreAndExecute(new RmManagerCmd(c,cmdParts));
            }
            else if(cmdParts[0].equals("checkTime")){
            	commandLogger.StoreAndExecute(new CheckTimeCmd(c,cmdParts));
            }
            else{
            	System.out.println("Cmd not found");
            }
//            TODO wfz cmds
            
            for(int i=0;i<o.getCurrentOrder();i++){
        		Order p=o.getOrderById(i);
        		if(p.okToCheckIn(c.getTime())){
        			c.getBranchByLocation(p.currentLocation()).getOutManByOrder(p).deliverOneOrderByTime(p);
        		}
        	}


        }
    }
}
