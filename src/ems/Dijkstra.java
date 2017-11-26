package ems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class Dijkstra {

    private final List<Branch> nodes;
    private final List<Edge> edges;
    private Set<Branch> settledNodes;
    private Set<Branch> unSettledNodes;
    private Map<Branch, Branch> predecessors;
    private Map<Branch, Integer> distance;

    public Dijkstra(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Branch>(graph.getVertexes());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

    public void execute(Branch source) {
        settledNodes = new HashSet<Branch>();
        unSettledNodes = new HashSet<Branch>();
        distance = new HashMap<Branch, Integer>();
        predecessors = new HashMap<Branch, Branch>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Branch node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Branch node) {
        List<Branch> adjacentNodes = getNeighbors(node);
        for (Branch target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(Branch node, Branch target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Branch> getNeighbors(Branch node) {
        List<Branch> neighbors = new ArrayList<Branch>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private Branch getMinimum(Set<Branch> vertexes) {
        Branch minimum = null;
        for (Branch vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Branch vertex) {
        return settledNodes.contains(vertex);
    }

    private int getShortestDistance(Branch destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public ArrayList<Branch> getPath(Branch target) {
        //LinkedList<Branch> path = new LinkedList<Branch>();
    	ArrayList<Branch> path=new ArrayList<Branch>();
        Branch step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

}
