package main.edu.ufp.inf.en.siu;

import java.util.ArrayList;

import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.RedBlackBST;

public class Map {
    
    private EdgeWeightedDigraph graph;
    private ArrayList<EdgeWeightedDigraph> subGraphs = new ArrayList<>();
    private RedBlackBST<Integer, Node> nodes = new RedBlackBST<>();

    public Map(int numNodes) {
        this.graph = new EdgeWeightedDigraph(numNodes);
    }

    public Map(int V, int E) {
        this.graph = new EdgeWeightedDigraph(V, E);
    }

    public Map(DataBase db) {
        this(db.getNodesST().size());
        int i = 0;
        // iterate over all nodes from db and set the correspondent index for them
        for (var v : db.getNodesST().keys()) {
            Node n = db.getNodesST().get(v);
            n.setIndexMap(i);
            i++;
        }
        // iterate over all ways and add them as directedEdges of graph
        for (var v : db.getWaysST().keys()) {
            Way w = db.getWaysST().get(v);
            Node o = db.getNodesST().get(w.from());
            Node t = db.getNodesST().get(w.to());
            w = new Way(w.getWayId(), w.getTags(), o.getIndexMap(), t.getIndexMap(), w.weight());
            this.graph.addEdge(w);
        }

    }

    public EdgeWeightedDigraph getGraph() {
        return graph;
    }

    public void setGraph(EdgeWeightedDigraph graph) {
        this.graph = graph;
    }

    public RedBlackBST<Integer, Node> getNodes() {
        return nodes;
    }

    public void setNodes(RedBlackBST<Integer, Node> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<EdgeWeightedDigraph> getSubGraphs() {
        return subGraphs;
    }

    public void setSubGraphs(ArrayList<EdgeWeightedDigraph> subGraphs) {
        this.subGraphs = subGraphs;
    }

    // TODO: ask teacher about this..
    public void createSubGraph (Tag... tags) {
        this.graph.edges().forEach(edge -> {
            Way w = (Way) edge;
            EdgeWeightedDigraph subGraph = new EdgeWeightedDigraph(this.graph.V());
            for (Tag tag : tags) {
                if (w.containsTag(tag)) {
                    subGraph.addEdge(w);
                }
            }
        });
    }

    /**
     * Calculates the shortest path between two nodes using a transportation
     * @param transportType the type of transportation used (e.g: bus, walking, cycling, cityVehicle, highwayVehicle)
     * @param origin origin node
     * @param destination destination node
     * @return distance in meters or seconds depending on transportation used
     * @author Vitor Hugo
     */
    public double shortestPath (String transportType, Node origin, Node destination) {
        if (transportType == null) throw new IllegalArgumentException("argument to shortestPath() is null");

        // set corresponding transportType weight as Way chosenWeight
        this.graph.edges().forEach(edge -> {
                                    Way w = (Way)edge;
                                    w.setChosenWeight(transportType);
                                });

        // use djikstras to calculate shortest path
        DijkstraSP dsp = new DijkstraSP(this.graph, origin.getIndexMap());
        
        // print to terminal path from origin to destination
        //dsp.pathTo(destination.getIndexMap()).forEach(System.out::println);
        return dsp.distTo(destination.getIndexMap());
    }

    /**
     * Calculates the shortest physical path between two nodes
     * @param origin
     * @param destination
     * @return
     */
    public double shortestPath (Node origin, Node destination) {
        
        // use djikstras to calculate shortest path
        DijkstraSP dsp = new DijkstraSP(this.graph, origin.getIndexMap());
        
        // print to terminal path from origin to destination
        if (dsp.hasPathTo(destination.getIndexMap()))
            dsp.pathTo(destination.getIndexMap()).forEach(System.out::println);
    
        return dsp.distTo(destination.getIndexMap());
    }


}
