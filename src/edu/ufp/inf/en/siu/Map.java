package edu.ufp.inf.en.siu;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.RedBlackBST;

public class Map {
    
    private EdgeWeightedDigraph graph;
    private RedBlackBST<Integer, Node> nodes = new RedBlackBST<>();

    public Map(int numNodes) {
        this.graph = new EdgeWeightedDigraph(numNodes);
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

}
