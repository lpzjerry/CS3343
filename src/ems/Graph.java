package ems;

import java.util.List;

public class Graph {
    private final List<Branch> vertexes;
    private final List<Edge> edges;

    public Graph(List<Branch> vertexes, List<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public List<Branch> getVertexes() {
        return vertexes;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}