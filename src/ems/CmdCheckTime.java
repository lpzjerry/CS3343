package ems;

public class CmdCheckTime implements Command {
    private Company c;

    public CmdCheckTime(Company c, String[] cmd) {
        this.c = c;
    }

    public void execute() {
        long re = c.getTime();
        System.out.println("It is " + re);
    }
}
