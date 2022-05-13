package main.edu.ufp.inf.en.siu.map;

import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.RedBlackBST;
import main.edu.ufp.inf.en.siu.database.DataBase;
import main.edu.ufp.inf.en.siu.database.Node;
import main.edu.ufp.inf.en.siu.database.Tag;
import main.edu.ufp.inf.en.siu.database.Way;
import main.edu.ufp.inf.en.siu.database.Way.Transport;

public class Map {
    
    private EdgeWeightedDigraph graph;
    /**
     * maps graph index to node
     */
    private RedBlackBST<Integer, Node> nodes = new RedBlackBST<>();

    public Map() {}

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
            this.nodes.put(i, n);
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

    /**
     * Constructor by copy.
     * Used to create subgraph
     * @param map map to use as foundation
     */
    public Map (Map map, Tag... tags) {
        this(map.graph.V());
        for (Integer i : map.nodes.keys()) {
            Node n = map.nodes.get(i);
            for (Tag t : tags) {
                if (n.containsTag(t)) {
                    break;
                }
            }

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

    /**
     * Calculates the shortest distance between two nodes using a transportation
     * @param transportType the type of transportation used (e.g: bus, walking, ...)
     * @param origin origin node
     * @param destination destination node
     * @return distance in minutes depending on transportation used
     * @author Vitor Hugo
     */
    public double shortestDistance (String transportType, Node origin, Node destination) {
        if (transportType == null) throw new IllegalArgumentException("argument to shortestPath() is null");

        // use djikstras to calculate shortest path
        DijkstraSP dsp = new DijkstraSP(this.graph, origin.getIndexMap());
        
        // print to terminal path from origin to destination
        dsp.pathTo(destination.getIndexMap()).forEach(System.out::println);
        
        // return time value in minutes
        return (dsp.distTo(destination.getIndexMap())) / (Transport.valueOf(transportType).speed);
    }

    /**
     * Calculates the shortest distance path between two nodes
     * @param origin node to start from 
     * @param destination destination node
     * @return distance in meters 
     */
    public double shortestDistance (Node origin, Node destination) {
        // use djikstras to calculate shortest path
        DijkstraSP dsp = new DijkstraSP(this.graph, origin.getIndexMap());
        
        // print to terminal path from origin to destination
        if (dsp.hasPathTo(destination.getIndexMap()))
            dsp.pathTo(destination.getIndexMap()).forEach(System.out::println);
    
        return dsp.distTo(destination.getIndexMap());
    }

}
