package ems;

import java.util.ArrayList;

public class Invoker {
    private ArrayList<Command> history = new ArrayList<Command>();

    public void StoreAndExecute(Command cmd) {
        this.history.add(cmd);
        cmd.execute();
    }

    public int getSize() {
        return this.history.size();
    }
}
