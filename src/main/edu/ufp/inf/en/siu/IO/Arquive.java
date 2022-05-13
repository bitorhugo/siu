package main.edu.ufp.inf.en.siu.IO;


import edu.princeton.cs.algs4.Out;
import main.edu.ufp.inf.en.siu.database.Node;
import main.edu.ufp.inf.en.siu.database.Poi;
import main.edu.ufp.inf.en.siu.database.Tag;
import main.edu.ufp.inf.en.siu.database.Way;
import main.edu.ufp.inf.en.siu.user.User;

/**
 * Static class for arquiving purposes
 */
public class Arquive {

    // private constructor
    private Arquive() {}
    
    private static final String path_users = "/Users/VitorHugo/dev/java/projects/siu/data/out/users_out.txt";
    private static final String path_searchedUsers = "/Users/VitorHugo/dev/java/projects/siu/data/out/searched_users_out.txt";

    private static final String path_nodes = "/Users/VitorHugo/dev/java/projects/siu/data/out/nodes_out.txt";
    private static final String path_searchedNodes = "/Users/VitorHugo/dev/java/projects/siu/data/out/searched_nodes_out.txt";
    
    private static final String path_ways = "/Users/VitorHugo/dev/java/projects/siu/data/out/ways_out.txt";
    private static final String path_searchedWays = "/Users/VitorHugo/dev/java/projects/siu/data/out/searched-ways_out.txt";

    private static final String path_poi = "/Users/VitorHugo/dev/java/projects/siu/data/out/poi_out.txt";
    private static final String path_searchedPoi = "/Users/VitorHugo/dev/java/projects/siu/data/out/searched_poi_out.txt";

    private static final String path_tags = "/Users/VitorHugo/dev/java/projects/siu/data/out/tags_out.txt";
    private static final String path_searchedTags = "/Users/VitorHugo/dev/java/projects/siu/data/out/searched_tags_out.txt";

    public static void User (User u) {
        Out out = new Out(path_users);
        out.print(u);
    }

    public static void searchedUser (User u) {
        Out out = new Out(path_searchedUsers);
        out.print(u);
    }

    public static void Node (Node n) {
        Out out = new Out(path_nodes);
        out.print(n);
    }

    public static void searchedNode (Node n) {
        Out out = new Out(path_searchedNodes);
        out.print(n);
    }

    public static void Poi(Poi p) {
        Out out = new Out(path_poi);
        out.print(p);        
    }

    public static void searchedPoi(Poi p) {
        Out out = new Out(path_searchedPoi);
        out.print(p);        
    }

    public static void Way(Way w) {
        Out out = new Out(path_ways);
        out.print(w);
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

}
