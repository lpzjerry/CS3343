package ems;

import java.util.Scanner;

public class Main {

    // <<<<<<< sherlock
    
    
    public static void main(String args[]) {
    	Company company = Company.getInstance();
    	company.addNewManager("super", "hahaha", "Male", 0);
//      TODO check arrival of Orders (by timer)
        Scanner reader = new Scanner(System.in);
        Invoker commandLogger = new Invoker();
        Manager manager=Company.getInstance().getManager(0);
        Customer customer=null;
        System.out.println("\n> ");
        char userFlag='b';
        while (reader.hasNext()) {
//        	System.out.println("\n> "); // TODO
            if (userFlag == 'm' && manager.rootOrNot() )
                System.out.print("EMS# ");
            else
                System.out.print("EMS$ ");
            String cmdLine = reader.nextLine().trim();

            // Blank lines exist in data file as separators. Skip them.
            if (cmdLine.equals(""))
                continue;

            System.out.println("\n> " + cmdLine);

            // split the words in actionLine => create an array of word
            // strings

            String[] cmdParts = cmdLine.split(" ");  //TODO replace regex " " -> "\s" (blank charater)
            //[0]is cmd [1]is attribute 1 .. and so on...
            if (userFlag=='m'){
            	if(manager!=null&&manager.rootOrNot()){
            		if (cmdParts[0].equals("addManager")) { // cmdParts= [ "name", "password", "gender", "status"]
                        commandLogger.StoreAndExecute(new CmdAddManager(manager, cmdParts));
                        continue;
                    }else if (cmdParts[0].equals("rmManager")) {
                        commandLogger.StoreAndExecute(new CmdRmManager(manager, cmdParts));
                        continue;
                    }
            	}
            	if (cmdParts[0].equals("searchBranch")) { // cmdParts= ["searchBranch","x","y"]
                    commandLogger.StoreAndExecute(new CmdSearchBranch(company, cmdParts));
                    continue;
                } 
            	else if (cmdParts[0].equals("searchOrder")) {// cmdParts= ["searchOrder","id"]
                    commandLogger.StoreAndExecute(new CmdSearchOrder(company, cmdParts));
                    continue;
                }
               /* else if(cmdParts[0].equals("recieveOrder")){
                        commandLogger.StoreAndExecute(new ReceiveOrderCmd(company,cmdParts));
                }*/
                else if (cmdParts[0].equals("exit")) {
                    break;
                } else if (cmdParts[0].equals("addBranch")) {// cmdParts= [ "name"]
                    commandLogger.StoreAndExecute(new CmdAddBranch(cmdParts,manager));
                    continue;
                } else if (cmdParts[0].equals("addCustomer")) {
                    commandLogger.StoreAndExecute(new CmdAddCustomer(cmdParts,manager));
                    continue;
                } else if (cmdParts[0].equals("rmBranch")) {
                    commandLogger.StoreAndExecute(new CmdRmBranch(cmdParts,manager));
                    continue;
                } else if (cmdParts[0].equals("checkTime")) {
                    commandLogger.StoreAndExecute(new CmdCheckTime(company, cmdParts));
                    continue;
                } else if (cmdParts[0].equals("logout")){
                	userFlag='b';
                	continue;
                } else if (cmdParts[0].equals("addLink")) {
                    commandLogger.StoreAndExecute(new CmdAddLinkage(manager, cmdParts));
                    continue;
                } else if (cmdParts[0].equals("rmLink")) {
                    commandLogger.StoreAndExecute(new CmdRmLinkage(manager, cmdParts));
                    continue;
                } else {
                    System.out.println("Cmd not found m");
                }

            }
            else if(userFlag=='c'){
            	if (cmdParts[0].equals("searchBranch")) { // cmdParts= ["searchBranch","x","y"]
                    commandLogger.StoreAndExecute(new CmdSearchBranch(company, cmdParts));
                } else if (cmdParts[0].equals("searchOrder")) {// cmdParts= ["searchOrder","id"]
                    commandLogger.StoreAndExecute(new CmdSearchOrder(company, cmdParts));
                } else if (cmdParts[0].equals("exit")) {
                    break;
                } else if (cmdParts[0].equals("createOrder")) {//cmdParts= ["createOrder","itemname","customer1ID"嚙踝蕭謅��"customer2ID"]
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
            
            
            
/*            if (cmdParts[0].equals("searchBranch")) { // cmdParts= ["searchBranch","x","y"]
                commandLogger.StoreAndExecute(new SearchBranchCmd(company, cmdParts));
            } else if (cmdParts[0].equals("searchOrder")) {// cmdParts= ["searchOrder","id"]
                commandLogger.StoreAndExecute(new SearchOrderCmd(company, cmdParts));
            }
            else if(cmdParts[0].equals("recieveOrder")){
                    commandLogger.StoreAndExecute(new ReceiveOrderCmd(company,cmdParts));
            }
            else if (cmdParts[0].equals("exit")) {
                break;
            } else if (cmdParts[0].equals("createOrder")) {//cmdParts= ["createOrder","itemname","customer1ID"嚙踝蕭謅��"customer2ID"]
                commandLogger.StoreAndExecute(new CreateOrderCmd(company, cmdParts));
            } else if (cmdParts[0].equals("addManager")) { // cmdParts= [ "name", "password", "gender", "status"]
                commandLogger.StoreAndExecute(new AddManagerCmd(company, cmdParts));
            } else if (cmdParts[0].equals("addBranch")) {// cmdParts= [ "name"]
                commandLogger.StoreAndExecute(new AddBranchCmd(company, cmdParts));
            } else if (cmdParts[0].equals("addCustomer")) {
                commandLogger.StoreAndExecute(new AddCustomerCmd(company, cmdParts));
            } else if (cmdParts[0].equals("rmBranch")) {
                commandLogger.StoreAndExecute(new RmBranchCmd(company, cmdParts));
            } else if (cmdParts[0].equals("rmManager")) {
                commandLogger.StoreAndExecute(new RmManagerCmd(company, cmdParts));
            } else if (cmdParts[0].equals("checkTime")) {
                commandLogger.StoreAndExecute(new CheckTimeCmd(company, cmdParts));
            } else {
                System.out.println("Cmd not found");
            }
*/
            OrderPool.getInstance().processAllOrders();

        }
        reader.close();
    }
}
