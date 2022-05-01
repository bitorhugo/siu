package edu.ufp.inf.en.siu;


import edu.princeton.cs.algs4.Out;

/**
 * Static class for arquiving purposes
 */
public class Arquive {
    
    private static final String path_users = "data/users_out.txt";
    private static final String path_nodes = "data/nodes_out.txt";
    private static final String path_ways = "data/ways_out.txt";
    private static final String path_poi = "data/poi_out.txt";
    private static final String path_tags = "data/tags_out.txt";

    public static void User (User u) {
        Out out = new Out(path_users);
        out.print(u.getClass().getSimpleName() + ";" + u.getName() + ";" + u.getAddress() + ";"
                + u.getIdNumber() + ";" + u.getBirth() + ";" + u.getEmail() + ";" + u.getPassword());
    }

    public static void Node (Node n) {
        Out out = new Out(path_nodes);
        out.print(n);
    }

    public static void Poi(Poi p) {
        Out out = new Out(path_poi);
        out.print(p);        
    }

    public static void Way(Way w) {
        Out out = new Out(path_ways);
        out.print(w);
    }

    public static void Tag(Tag t) {
        Out out = new Out(path_tags);
        out.print(t);
    }

    public static void Tag(Node n, Tag t) {
        Out out = new Out(path_tags);
        out.print(t);
    }

    public static void Tag(Way w, Tag t) {
        Out out = new Out(path_tags);
        out.print(w);
        out.print(t);
        out.println();
    }

}
