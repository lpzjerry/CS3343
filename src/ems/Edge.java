package ems;


public class Edge {
    private final String id;
    private final Branch source;
    private final Branch destination;
    private final int weight;

    public Edge(String id, Branch source, Branch destination, int weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public Branch getDestination() {
        return destination;
    }

    public Branch getSource() {
        return source;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }


}