package main.edu.ufp.inf.en.siu.IO;


import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Out;
import main.edu.ufp.inf.en.siu.database.DataBase;
import main.edu.ufp.inf.en.siu.database.Tag;
import main.edu.ufp.inf.en.siu.database.node.Node;
import main.edu.ufp.inf.en.siu.database.poi.Poi;
import main.edu.ufp.inf.en.siu.database.way.Way;
import main.edu.ufp.inf.en.siu.user.User;

/**
 * Static class for arquiving purposes
 */
public class Arquive {

    // private constructor
    private Arquive() {}

    private static final String NEWLINE = "\n";
    
    private static final String path_users = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/users_out.txt";
    private static final String path_searchedUsers = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/searched_users_out.txt";
    private static final String path_datasetUsers = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/dataset_users_out.txt";

    private static final String path_nodes = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/nodes_out.txt";
    private static final String path_searchedNodes = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/searched_nodes_out.txt";
    private static final String path_datasetNodes = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/dataset_nodes_out.txt";
    
    private static final String path_ways = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/ways_out.txt";
    private static final String path_searchedWays = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/searched-ways_out.txt";
    private static final String path_datasetWays = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/dataset_ways_out.txt";

    private static final String path_poi = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/poi_out.txt";
    private static final String path_searchedPoi = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/searched_poi_out.txt";
    private static final String path_datasetPois = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/dataset_pois_out.txt";

    private static final String path_tags = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/tags_out.txt";
    private static final String path_searchedTags = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/searched_tags_out.txt";

    private static final String path_graph = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/graph_out.txt";

    public static void User (User u) {
        Out out = new Out(path_users);
        out.print(u);
    }

    public static void Users (DataBase db) {
        Out out = new Out(path_datasetUsers);
        out.print(db.getUserST().size() + NEWLINE);
        for (var v : db.getUserST().keys()) {
            User u = db.getUserST().get(v);
            out.print(u + NEWLINE);
        }
    }

    public static void searchedUser (User u) {
        Out out = new Out(path_searchedUsers);
        out.print(u);
    }

    public static void Node (Node n) {
        Out out = new Out(path_nodes);
        out.print(n);
    }

    public static void Nodes (DataBase db) {
        Out out = new Out(path_datasetNodes);
        out.print(db.getNodesST().size() + NEWLINE);
        for (var v : db.getNodesST().keys()) {
            Node n = db.searchNode(v);
            out.print(n + NEWLINE);
        }
    }

    public static void searchedNode (Node n) {
        Out out = new Out(path_searchedNodes);
        out.print(n);
    }

    public static void Poi(Poi p) {
        Out out = new Out(path_poi);
        out.print(p);        
    }

    public static void Pois (DataBase db) {
        Out out = new Out(path_datasetPois);
        out.print(db.getPoiST().size() + NEWLINE);
        for (var v : db.getPoiST().keys()) {
            Poi u = db.searchPoi(v);
            out.print(u + NEWLINE);
        }
    }

    public static void searchedPoi(Poi p) {
        Out out = new Out(path_searchedPoi);
        out.print(p);        
    }

    public static void Way(Way w) {
        Out out = new Out(path_ways);
        out.print(w);
    }
    
    public static void Ways (DataBase db) {
        Out out = new Out(path_datasetWays);
        out.print(db.getWaysST().size() + NEWLINE);
        for (var v : db.getWaysST().keys()) {
            Way u = db.searchWay(v);
            out.print(u + NEWLINE);
        }
    }

    public static void searchedWay(Way w) {
        Out out = new Out(path_searchedWays);
        out.print(w);
    }

    public static void Tag(Tag t) {
        Out out = new Out(path_tags);
        out.print(t);
    }

    public static void searchedTag(Tag t) {
        Out out = new Out(path_searchedTags);
        out.print(t);
    }

    public static void Tag(Node n, Tag t) {
        Out out = new Out(path_tags);
        out.print(t);
    }

    public static void searchedTag(Node n, Tag t) {
        Out out = new Out(path_searchedTags);
        out.print(t);
    }

    public static void Tag(Way w, Tag t) {
        Out out = new Out(path_tags);
        out.print(w);
        out.print(t);
        out.println();
    }

    public static void searchedTag(Way w, Tag t) {
        Out out = new Out(path_searchedTags);
        out.print(w);
        out.print(t);
        out.println();
    }

    public static void graph (EdgeWeightedDigraph graph) {
        Out out = new Out(path_graph);
        out.print(graph);
    }
}
