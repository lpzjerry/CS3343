package ems;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
//      TODO check arrival of Orders (by timer)
        Company company = Company.getInstance();
        OrderPool orderPool = OrderPool.getInstance();
        Scanner reader = new Scanner(System.in);
        Invoker commandLogger = new Invoker();
        Manager manager=null;
        Customer customer=null;
        
        char userFlag='b';
        while (reader.hasNext()) {
            if (userFlag == 'm' && manager.rootOrNot)
                System.out.print("EMS# ");
            else
                System.out.print("EMS$ ");
            String cmdLine = reader.nextLine().trim();

            /*/ Blank lines exist in data file as separators. Skip them.
            if (cmdLine.equals(""))
                continue;*/
            //System.out.println("\n> " + cmdLine);

            // split the words in actionLine => create an array of word
            // strings

            String[] cmdParts = cmdLine.split(" ");  //TODO replace regex " " -> "\s" (blank charater)
            //[0]is cmd [1]is attribute 1 .. and so on...
            if (userFlag=='m'){
            	if(manager.rootOrNot()){
            		userFlag='r';
            	}
            	if (cmdParts[0].equals("searchBranch")) { // cmdParts= ["searchBranch","x","y"]
                    commandLogger.StoreAndExecute(new CmdSearchBranch(company, cmdParts));
                } 
            	else if (cmdParts[0].equals("searchOrder")) {// cmdParts= ["searchOrder","id"]
                    commandLogger.StoreAndExecute(new CmdSearchOrder(company, cmdParts));
                }
               /* else if(cmdParts[0].equals("recieveOrder")){
                        commandLogger.StoreAndExecute(new ReceiveOrderCmd(company,cmdParts));
                }*/
                else if (cmdParts[0].equals("exit")) {
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
                } else if(userFlag!='r'){
                    System.out.println("Cmd not found");
                }
            }
            else if(userFlag=='c'){
            	if (cmdParts[0].equals("searchBranch")) { // cmdParts= ["searchBranch","x","y"]
                    commandLogger.StoreAndExecute(new CmdSearchBranch(company, cmdParts));
                } else if (cmdParts[0].equals("searchOrder")) {// cmdParts= ["searchOrder","id"]
                    commandLogger.StoreAndExecute(new CmdSearchOrder(company, cmdParts));
                } else if (cmdParts[0].equals("exit")) {
                    break;
                } else if (cmdParts[0].equals("createOrder")) {//cmdParts= ["createOrder","itemname","customer1ID"��蕭"customer2ID"]
                    commandLogger.StoreAndExecute(new CmdCreateOrder(company, cmdParts,customer));
                } else if (cmdParts[0].equals("checkTime")) {
                    commandLogger.StoreAndExecute(new CmdCheckTime(company, cmdParts));
                } else if (cmdParts[0].equals("logout")){
                	userFlag='b';
                } else {
                    System.out.println("Cmd not found");
                }
            }
            else if(userFlag=='b'){
            	if(cmdParts[0].equals("manager")){
            		commandLogger.StoreAndExecute(new CmdSwitchToManager(company, cmdParts, manager));
            		userFlag='m';
            	} else if(cmdParts[0].equals("customer")){
            		commandLogger.StoreAndExecute(new CmdSwitchToCustomer(company, cmdParts, customer));
            		userFlag='c';
            	} else if (cmdParts[0].equals("searchBranch")) { // cmdParts= ["searchBranch","x","y"]
                    commandLogger.StoreAndExecute(new CmdSearchBranch(company, cmdParts));
                } else if (cmdParts[0].equals("exit")) {
                    break;
                } else if (cmdParts[0].equals("checkTime")) {
                    commandLogger.StoreAndExecute(new CmdCheckTime(company, cmdParts));
                } else {
                    System.out.println("Cmd not found");
                }
            }
            
            if(userFlag=='r'){
            	if (cmdParts[0].equals("addManager")) { // cmdParts= [ "name", "password", "gender", "status"]
                    commandLogger.StoreAndExecute(new CmdAddManager(company, cmdParts));
                }else if (cmdParts[0].equals("rmManager")) {
                    commandLogger.StoreAndExecute(new CmdRmManager(company, cmdParts));
                }else {
                    System.out.println("Cmd not found");
                }
            	userFlag='m';
            }

            OrderPool.getInstance().processAllOrders();

        }
    }
}
