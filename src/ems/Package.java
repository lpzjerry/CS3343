package ems;

/**
 * Created by Pengze Liu on 24/9/2017.
 */
public class Package {
    private int packageID;
    private String packageName;
    private int weight;
    private Sender sender;
    private Receiver receiver;

    public Package(int _packageID, String _packageName, int _weight, Sender _sender, Receiver _receiver){
        this.packageID = _packageID;
        this.packageName = _packageName;
        this.weight = _weight;
        this.sender = _sender;
        this.receiver = _receiver;
    }

    public int getID(){
        return this.packageID;
    }

    public String getName(){
        return this.packageName;
    }

    public int getWeight(){
        return this.weight;
    }

    public Sender getSender(){
        return this.sender;
    }

    public Receiver getReceiver(){
        return this.receiver;
    }
}
