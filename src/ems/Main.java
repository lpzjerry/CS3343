package ems;

import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
    	Company company = Company.getInstance();
    	company.addNewManager("super", "hahaha", "Male", 0);
        Scanner reader = new Scanner(System.in);
        Invoker commandLogger = new Invoker();
        Manager manager=Company.getInstance().getManager(0);
        Customer customer=null;
        char userFlag='b';
    	System.out.print("EMS$ ");
        while (reader.hasNext()) {
        	
            String cmdLine = reader.nextLine().trim();
            
            // Blank lines exist in data file as separators. Skip them.
            if (cmdLine.equals(""))
                continue;

            // split the words in actionLine => create an array of word
            // strings

            String[] cmdParts = cmdLine.split(" ");  //TODO replace regex " " -> "\s" (blank charater)
            //[0]is cmd [1]is attribute 1 .. and so on...
            if (userFlag=='m'){
            	if (cmdParts[0].equals("searchBranch")) { // cmdParts= ["searchBranch","x","y"]
                    commandLogger.StoreAndExecute(new CmdSearchBranch(company, cmdParts));                  
                } else if(cmdParts[0].equals("help")){
            		commandLogger.StoreAndExecute(new CmdHelp());
            	} else if (cmdParts[0].equals("addManager")&&manager.rootOrNot()) { // cmdParts= [ "name", "password", "gender", "status"]
                    commandLogger.StoreAndExecute(new CmdAddManager(manager, cmdParts));
                } else if (cmdParts[0].equals("rmManager")&&manager.rootOrNot()) {
                    commandLogger.StoreAndExecute(new CmdRmManager(manager, cmdParts));
                   
                } else if (cmdParts[0].equals("searchOrder")) {// cmdParts= ["searchOrder","id"]
                    commandLogger.StoreAndExecute(new CmdSearchOrder(company, cmdParts));
                } else if (cmdParts[0].equals("exit")) {
                    break;
                } else if (cmdParts[0].equals("addBranch")) {// cmdParts= [ "name"]
                    commandLogger.StoreAndExecute(new CmdAddBranch(cmdParts,manager));
                    
                } else if (cmdParts[0].equals("addCustomer")) {
                    commandLogger.StoreAndExecute(new CmdAddCustomer(cmdParts,manager));
                    
                } else if (cmdParts[0].equals("rmBranch")) {
                    commandLogger.StoreAndExecute(new CmdRmBranch(cmdParts,manager));
                    
                } else if (cmdParts[0].equals("checkTime")) {
                    commandLogger.StoreAndExecute(new CmdCheckTime(company, cmdParts));
                    
                } else if (cmdParts[0].equals("logout")){
                	userFlag='b';
                	
                } else if (cmdParts[0].equals("addLink")) {
                    commandLogger.StoreAndExecute(new CmdAddLinkage(manager, cmdParts));
                    
                } else if (cmdParts[0].equals("rmLink")) {
                    commandLogger.StoreAndExecute(new CmdRmLinkage(manager, cmdParts));
                    
                } else {
                    System.out.println("Cmd not found m");
                }
            }
            else if(userFlag=='c'){
            	if (cmdParts[0].equals("searchBranch")) { // cmdParts= ["searchBranch","x","y"]
                    commandLogger.StoreAndExecute(new CmdSearchBranch(company, cmdParts));
                } else if(cmdParts[0].equals("help")){
            		commandLogger.StoreAndExecute(new CmdHelp());
            	} else if (cmdParts[0].equals("searchOrder")) {// cmdParts= ["searchOrder","id"]
                    commandLogger.StoreAndExecute(new CmdSearchOrder(company, cmdParts));
                } else if (cmdParts[0].equals("exit")) {
                    break;
                } else if (cmdParts[0].equals("createOrder")) {
                    commandLogger.StoreAndExecute(new CmdCreateOrder(company, cmdParts,customer));
                } else if (cmdParts[0].equals("checkTime")) {
                    commandLogger.StoreAndExecute(new CmdCheckTime(company, cmdParts));
                } else if (cmdParts[0].equals("logout")){
                	userFlag='b';
                } else {
                    System.out.println("Cmd not found c");
                }
            }
            else if(userFlag=='b'){
            	if(cmdParts[0].equals("manager")){
            		manager=Company.getInstance().getManager(Integer.parseInt(cmdParts[1]));
            		System.out.println("Log in as: "+manager);
            		//commandLogger.StoreAndExecute(new CmdSwitchToManager(company, cmdParts, manager));
            		userFlag='m';
            	} else if(cmdParts[0].equals("help")){
            		commandLogger.StoreAndExecute(new CmdHelp());
            	} else if(cmdParts[0].equals("customer")){
            		customer=Company.getInstance().getCustomer(Integer.parseInt(cmdParts[1]));
            		System.out.println("Log in as: "+customer);
            		//commandLogger.StoreAndExecute(new CmdSwitchToCustomer(company, cmdParts, customer));
            		userFlag='c';
            	} else if (cmdParts[0].equals("searchBranch")) { // cmdParts= ["searchBranch","x","y"]
                    commandLogger.StoreAndExecute(new CmdSearchBranch(company, cmdParts));
                } else if (cmdParts[0].equals("exit")) {
                    break;
                } else if (cmdParts[0].equals("checkTime")) {
                    commandLogger.StoreAndExecute(new CmdCheckTime(company, cmdParts));
                } else {
                    System.out.println("Cmd not found b");
                }
            }
            OrderPool.getInstance().processAllOrders();
            
            if (userFlag == 'm' && manager.rootOrNot() )
        		System.out.print("EMS# ");
        	else
        		System.out.print("EMS$ ");
        }
        reader.close();
    }
}
