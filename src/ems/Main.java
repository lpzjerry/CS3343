package ems;

import java.util.Scanner;

public class Main {

    Company company = Company.getInstance();
    

    public void main() {
//      TODO check arrival of Orders (by timer)
        String prompt = ">";
        Scanner reader = new Scanner(System.in);
        while (reader.hasNext()) {
            System.out.print(prompt);

            String cmd_str = reader.nextLine();
            if (cmd_str.equals("getid")) {
                // dosth;
//                Order order.send();
            }
//            TODO wfz cmds

        }
    }
}
