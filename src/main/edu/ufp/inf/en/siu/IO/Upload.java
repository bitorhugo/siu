package main.edu.ufp.inf.en.siu.IO;

import java.util.Scanner;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import main.edu.ufp.inf.en.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.siu.database.DataBase;
import main.edu.ufp.inf.en.siu.database.Node;
import main.edu.ufp.inf.en.siu.database.Tag;
import main.edu.ufp.inf.en.siu.database.Way;
import main.edu.ufp.inf.en.siu.map.Map;

public class Upload {

    // private constructor
    private Upload(){}

    private static final String nodesPath = "data/in/txt/nodes.txt";
    private static final String waysPath = "data/in/txt/ways2.txt";
    private static final String graphPath = "data/in/txt/map_in.txt";

    /**
     * uploads nodes from nodesPath to database
     * @param db database
     * @author Vitor Hugo
     */
    public static void Nodes(DataBase db) {
        /**
         * File Structure:
         *  - number of nodes
         *  - nodeId, x coordinate, y coordinate, tag key, tag values, tag key, ...
         *   - ...
         */
        if (db == null) throw new IllegalAccessError("argument to uploadDB() is null");
        In in = new In(nodesPath);

        in.readInt();
        
        while (in.hasNextLine()) {
            String []lines = in.readLine().split(",");
            if (lines[0].length() > 0) {
                Node n = new Node(Integer.parseInt(lines[0]), new Point(Float.parseFloat(lines[1]), Float.parseFloat(lines[2])));
                // check to see if line contains key-value pairs
                if (lines.length > 2) {
                    SeparateChainingHashST<Tag, String> tags = new SeparateChainingHashST<>();
                    // iterate over remaining key value pairs of line
                    for (int i = 3; i < lines.length - 1; i+=2) {
                        if (lines[i].contains(":")) {
                            lines[i] = lines[i].replace(':', '_');
                        }
                        tags.put(Tag.valueOf(lines[i].toUpperCase()), lines[i + 1]);
                    }
                    n.setTags(tags);
                }
                db.addNode(n);
            }
        }
        in.close();
    }

    /**
     * uploads nodes from input source to database
     * @param input source
     * @param db database
     */
    public static void Nodes(String input, DataBase db) {
        if (db == null) throw new IllegalAccessError("argument to uploadDB() is null");
        In in = new In(new Scanner(input));

        in.readInt();
        while (in.hasNextLine()) {
            String []lines = in.readLine().split(",");
            if (lines[0].length() > 0) {
                Node n = new Node(Integer.parseInt(lines[0]), new Point(Float.parseFloat(lines[1]), Float.parseFloat(lines[2])));
                // check to see if line contains key-value pairs
                if (lines.length > 2) {
                    SeparateChainingHashST<Tag, String> tags = new SeparateChainingHashST<>();
                    // iterate over remaining key value pairs of line
                    for (int i = 3; i < lines.length - 1; i+=2) {
                        if (lines[i].contains(":")) {
                            lines[i] = lines[i].replace(':', '_');
                        }
                        tags.put(Tag.valueOf(lines[i].toUpperCase()), lines[i + 1]);
                    }
                    n.setTags(tags);
                }
                db.addNode(n);
            }
        }
        in.close();
    }

    /**
     * uploads ways from waysPath to database 
     * @param db database
     * @author Vitor Hugo
     */
    public static void Ways(DataBase db) {
        /**
         * File Structure:
         *  - number of ways
         *  - wayId, originNode, destinationNode, weight, tag, value, tag, value, ...
         *  - ...
         */
        if (db == null) throw new IllegalArgumentException("argument to uploadWays() is null");

        In in = new In(waysPath);
        in.readInt(); // discard number of ways

        while (in.hasNextLine()) {
            String []lines = in.readLine().split(",");
            if (lines[0].length() > 0) {
                Way w = new Way(Integer.parseInt(lines[0]), Integer.parseInt(lines[1]), Integer.parseInt(lines[2]), Double.parseDouble(lines[3]));
                // check to see if line contains key-value pairs
                if (lines.length > 3) {
                    SeparateChainingHashST<Tag, String> tags = new SeparateChainingHashST<>();
                    // iterate over remaining key value pairs of line
                    for (int i = 4; i < lines.length - 1; i+=2) {
                        if (lines[i].contains(":")) {
                            lines[i] = lines[i].replace(':', '_');
                        }
                        tags.put(Tag.valueOf(lines[i].toUpperCase()), lines[i + 1]);
                    }
                    w.setTags(tags);
                }
                db.addWay(w);
            }
        }
        in.close();
    }

    public static void Ways(String input, DataBase db) {
        if (db == null) throw new IllegalArgumentException("argument to uploadWays() is null");

        In in = new In(new Scanner(input));
        in.readInt(); // discard number of ways

        while (in.hasNextLine()) {
            String []lines = in.readLine().split(",");
            if (lines[0].length() > 0) {
                Way w = new Way(Integer.parseInt(lines[0]), Integer.parseInt(lines[1]), Integer.parseInt(lines[2]), Double.parseDouble(lines[3]));
                // check to see if line contains key-value pairs
                if (lines.length > 3) {
                    SeparateChainingHashST<Tag, String> tags = new SeparateChainingHashST<>();
                    // iterate over remaining key value pairs of line
                    for (int i = 4; i < lines.length - 1; i+=2) {
                        if (lines[i].contains(":")) {
                            lines[i] = lines[i].replace(':', '_');
                        }
                        tags.put(Tag.valueOf(lines[i].toUpperCase()), lines[i + 1]);
                    }
                    w.setTags(tags);
                }
                db.addWay(w);
            }
        }
        in.close();
    }

    /**
     * uploads graph from graphPath to map
     * @param map
     */
    public static void Graph (Map map) {
        In in = new In(graphPath);
        map.setGraph(new EdgeWeightedDigraph(in));
        in.close();
    }
    
}
