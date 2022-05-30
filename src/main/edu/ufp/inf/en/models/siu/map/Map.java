package main.edu.ufp.inf.en.models.siu.map;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedDFS;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;

import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.database.node.Node;
import main.edu.ufp.inf.en.models.siu.database.poi.Poi;
import main.edu.ufp.inf.en.models.siu.database.tag.Tag;
import main.edu.ufp.inf.en.models.siu.database.transport.Transport;
import main.edu.ufp.inf.en.models.siu.database.way.Way;


public class Map {
    
    private EdgeWeightedDigraph graph;

    private RedBlackBST<Integer, Node> nodes = new RedBlackBST<>();

    public Map() {}

    public Map(int V) {
        this.graph = new EdgeWeightedDigraph(V);
    }

    public Map(int V, int E) {
        this.graph = new EdgeWeightedDigraph(V, E);
    }

    public Map(DataBase db) {
        this(db.numberOfNodes());
        int i = 0;
        try {
            // iterate over all nodes from db and set the correspondent index for them
            for (var nodeId : db.nodesKeys()) {
                Node n = db.searchNode(nodeId);
                //Node n = db.getNodesST().get(v);
                n.setIndexMap(i);
                this.nodes.put(i, n);
                i++;
            }
            // iterate over all ways and add them as directedEdges of graph
            for (var wayId : db.waysKeys()) {
                Way w = db.searchWay(wayId);
                Node o = db.searchNode(w.from());
                //Node o = db.getNodesST().get(w.from());
                //Node t = db.getNodesST().get(w.to());
                Node t = db.searchNode(w.to());
                w = new Way(w.getWayId(), o.getIndexMap(), t.getIndexMap(), w.weight());
                this.graph.addEdge(w);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * constructor by copy
     * used to create subgraphs without specified tag
     * @param map map to use as foundation
     * @param tag tag to not include
     */
    public Map (Map map, Tag tag) {
        // first we must know how many nodes don't have specified tag(s)
        List<Integer> indeces = new ArrayList<>(); // set a list to denote which index to add to subgraph
        
        int count = 0;

        // iterate over all indeces and find which ones to pick
        for (var mapIndex : map.nodes.keys()) {
            Node n = map.nodes.get(mapIndex);
            if (n instanceof Poi) {
                Poi p = (Poi) n;
                if (!p.containsTag(tag)) {
                    indeces.add(mapIndex);
                    this.nodes.put(count, p);
                    count ++;
                }
            }
            else {
                indeces.add(mapIndex);
                this.nodes.put(count, n);
                count ++;
            }
        }

        // create graph with indeces size nodes (indeces are all nodes that don't have the specified tag)
        this.graph = new EdgeWeightedDigraph(indeces.size());

        // connect graph by adding the edges that connect indeces nodes
        for (int i = 0; i < indeces.size(); i++) {
            for (var v : map.graph.adj(indeces.get(i))) {
                Way w = (Way) v;
                if ((indeces.contains(v.from()) && indeces.contains(v.to()))) {
                    // add way
                    this.graph.addEdge(new Way(w.getWayId(), i, indeces.indexOf(v.to()), v.weight()));
                }
            }
        }
    }

    /**
     * Constructor by copy
     * Used to create subgraph without ways with specified tags
     * @param map map to use as foundation
     */
    public Map (Map map, Tag... tags) {
        this(map.graph.V());
        for (Tag t : tags) {
            for (DirectedEdge edge : map.graph.edges()) {
                Way w = (Way) edge;
                if (!w.containsTag(t)) {
                    this.graph.addEdge(w);
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

    public Node getNodeFromIndex(Integer index) {
        return this.nodes.get(index);
    }

    public Node searchNode (int nodeID) {
        for (var index : this.indeces()) {
            Node n = getNodeFromIndex(index);
            if (n.getNodeId() == nodeID) return n;
        }
        return null;
    }

    /**
     * returns number of vertices in graph
     * @return number of nodes
     */
    public int numberOfNodes () {
        return this.graph.V();
    }

    /**
     * return number of ways in graph
     * @return number of edges
     */
    public int numberOfWays () {
        return this.graph.E();
    }

    /**
     * return all map indeces
     * @returnmap indeces
     */
    public Iterable<Integer> indeces() {
        return this.nodes.keys();
    }

    /**
     * updates map graph 
     * @param db database to retrieve updated data from
     */
    public void update (DataBase db) {
        Map map = new Map(db);
        this.setGraph(map.graph);
        this.setNodes(map.nodes);
    }

    /**
     * checks wether graph is strongly connected
     * @return true if connected | false if disconnected
     */
    public boolean isConnected () {
        Digraph digraph = new Digraph(this.graph.V());
        for (DirectedEdge edge : this.graph.edges()) {
            digraph.addEdge(edge.to(), edge.from());
        }
        for (int i = 0; i < digraph.V(); i++) {
            DirectedDFS dfs = new DirectedDFS(digraph, i);
            // count returns the number of vertices reachable from the source vertex
            // if count != from V(), graph is not strongly connected
            if (dfs.count() != this.graph.V()) return false;
        }
        return true;
    }

    /**
     * Calculates the shortest distance between two nodes using a transportation
     * @param transportType the type of transportation used (e.g: bus, walking, ...)
     * @param origin origin node
     * @param destination destination node
     * @return distance in minutes depending on transportation used
     * @author Vitor Hugo
     */
    public double shortestDistance (Transport transport, Node origin, Node destination) {
        if (transport == null) throw new IllegalArgumentException("argument to shortestPath() is null");

        // use djikstras to calculate shortest path
        DijkstraSP dsp = new DijkstraSP(this.graph, origin.getIndexMap());
        
        // print to terminal path from origin to destination
        dsp.pathTo(destination.getIndexMap()).forEach(System.out::println);
        
        // return time value in minutes
        return (dsp.distTo(destination.getIndexMap()) / transport.speed);
    }

    /**
     * Calculates the shortest distance path between two nodes
     * @param origin node to start from 
     * @param destination destination node
     * @return distance in meters 
     * @author Vitor Hugo
     */
    public double shortestDistance (Node origin, Node destination) {
        // use djikstras to calculate shortest path
        DijkstraSP dsp = new DijkstraSP(this.graph, origin.getIndexMap());
        
        // print to terminal path from origin to destination
        if (dsp.hasPathTo(destination.getIndexMap()))
            dsp.pathTo(destination.getIndexMap()).forEach(System.out::println);
    
        return dsp.distTo(destination.getIndexMap());
    }

    /**
     * calculates shortest path between two nodes
     * @param origin origin node
     * @param destination destination node
     * @return shortest path
     * @author Vitor Hugo
     */
    public Iterable<DirectedEdge> shortestPath(Node origin, Node destination) {
        DijkstraSP dsp = new DijkstraSP(this.graph, origin.getIndexMap());
        if (dsp.hasPathTo(destination.getIndexMap())) {
            return dsp.pathTo(destination.getIndexMap());
        }
        else {
            System.out.println("no path form origin to destination found");
            return null;
        }
    }

    /**
     * calculates time weight for each route of a path
     * @param path path
     * @return the amount of time each route in path will take
     */
    public Iterable<Long> shortestPathTime(Iterable<DirectedEdge> path, Transport transport) {
        
        List<Long> times = new ArrayList<>();

        // for each edge of path, calculate its weight in time
        path.forEach(p -> {
            long time = (long) ((p.weight() / transport.speed));
            times.add(time);
        });
        return times;
    }

    /**
     * Max flux between two locations
     * @param origin origin node
     * @param destination destination node
     */
    public void fulkersonFlux (Node origin, Node destination) {
        FlowNetwork fn = new FlowNetwork(new In (""));
        
        int source = searchNode(origin.getNodeId()).getNodeId();
        int sink = searchNode(destination.getNodeId()).getNodeId();

        // compute maximum flow and minimum cut
        FordFulkerson ff = new FordFulkerson(fn, source, sink);
        System.out.println("Max flow from " + source + " to " + sink);
        for (int v = 0; v < fn.V(); v++) {
            for (FlowEdge e : fn.adj(v)) {
                if ((v == e.from()) && e.flow() > 0)
                    System.out.println("   " + e);
            }
        }

        // print min-cut
        System.out.print("Min cut: ");
        for (int v = 0; v < sink; v++) {
            if (ff.inCut(v)) System.out.print(v + " ");
        }
        System.out.println();
        System.out.println("Max flow: " + ff.value());
        
    }

    @Override
    public String toString() {
        return "Map{\n" + this.graph + "}";
    }
}