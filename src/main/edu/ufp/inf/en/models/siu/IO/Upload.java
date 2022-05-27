package main.edu.ufp.inf.en.models.siu.IO;

import java.time.LocalDate;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import main.edu.ufp.inf.en.models.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.database.node.Node;
import main.edu.ufp.inf.en.models.siu.database.poi.Poi;
import main.edu.ufp.inf.en.models.siu.database.tag.Tag;
import main.edu.ufp.inf.en.models.siu.database.way.Way;
import main.edu.ufp.inf.en.models.siu.map.Map;
import main.edu.ufp.inf.en.models.siu.user.*;

public class Upload {

    // private constructor
    private Upload(){}

    private static final String USERSPATH = "data/in/txt/users/users.txt";
    private static final String NODESPATH = "/Users/VitorHugo/dev/java/projects/siu/data/in/txt/nodes/nodes.txt";
    private static final String WAYSPATH = "/Users/VitorHugo/dev/java/projects/siu/data/in/txt/ways/ways.txt";
    private static final String GRAPHPATH = "/Users/VitorHugo/dev/java/projects/siu/data/in/txt/graph_in.txt";

    /**
     * file format:
     *  number of users
     *  UserType,Name,Addr,ID,Birth,Email,Password
     * @param db
     */
    public static void Users(DataBase db) {
        
        In in = new In(USERSPATH);
        try {
            in.readInt();
            while (in.hasNextLine()) {
                String []lines = in.readLine().split(",");
                if (lines[0].length() > 0) {
                    User u;
                    if (lines[0].equals("Admin")) {
                        u = new Admin(lines[1],lines[2], lines[3], LocalDate.parse(lines[4]), lines[5], lines[6]);
                    }
                    else {
                        u = new Basic(lines[1],lines[2], lines[3], LocalDate.parse(lines[4]), lines[5], lines[6]);
                    }
                    db.addUser(u);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }

    }

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
        In in = new In(NODESPATH);

        try {
            in.readInt();
            while (in.hasNextLine()) {
                String []lines = in.readLine().split(",");
                if (lines[0].length() > 0) {
                    Node n = new Node(Integer.parseInt(lines[0]), new Point(Float.parseFloat(lines[1]), Float.parseFloat(lines[2])));
                    // check to see if line contains key-value pairs
                    if (lines.length > 3) {
                        Poi p = new Poi(n);
                        SeparateChainingHashST<Tag, String> tags = new SeparateChainingHashST<>();
                        // iterate over remaining key value pairs of line
                        for (int i = 3; i < lines.length - 1; i+=2) {
                            if (lines[i].contains(":")) {
                                lines[i] = lines[i].replace(':', '_');
                            }
                            tags.put(Tag.valueOf(lines[i].toUpperCase()), lines[i + 1]);
                        }
                        //p.setTags(tags);
                        db.addPoi(p);
                        for (Tag tag : tags.keys()) { // update database tags 
                            db.addPoiTag(p, tag, tags.get(tag));
                        }
                    }
                    else {
                        db.addNode(n);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }        
        
    }

    public static void Nodes(String input, DataBase db) {
        /**
         * File Structure:
         *  - number of nodes
         *  - nodeId, x coordinate, y coordinate, tag key, tag values, tag key, ...
         *   - ...
         */
        if (db == null) throw new IllegalAccessError("argument to uploadDB() is null");
        In in = new In(input);

        try {
            in.readInt();
            while (in.hasNextLine()) {
                String []lines = in.readLine().split(",");
                if (lines[0].length() > 0) {
                    Node n = new Node(Integer.parseInt(lines[0]), new Point(Float.parseFloat(lines[1]), Float.parseFloat(lines[2])));
                    // check to see if line contains key-value pairs
                    if (lines.length > 3) {
                        Poi p = new Poi(n);
                        SeparateChainingHashST<Tag, String> tags = new SeparateChainingHashST<>();
                        // iterate over remaining key value pairs of line
                        for (int i = 3; i < lines.length - 1; i+=2) {
                            if (lines[i].contains(":")) {
                                lines[i] = lines[i].replace(':', '_');
                            }
                            tags.put(Tag.valueOf(lines[i].toUpperCase()), lines[i + 1]);
                        }
                        p.setTags(tags);
                        db.addPoi(p);
                    }
                    else {
                        db.addNode(n);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }        
        
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

        In in = new In(WAYSPATH);

        try {
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
                        //w.setTags(tags);
                        db.addWay(w);
                        for (Tag t : tags.keys()) { // update database tags
                            db.addWayTag(w, t, tags.get(t));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }

    }

    /**
     * uploads ways from input source to database
     * @param input
     * @param db
     */
    public static void Ways(String input, DataBase db) {
        if (db == null) throw new IllegalArgumentException("argument to uploadWays() is null");

        In in = new In(input);

        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        
    }

    /**
     * uploads graph from graphPath to map
     * @param map
     */
    public static void Graph (Map map) {
        In in = new In(GRAPHPATH);
        map.setGraph(new EdgeWeightedDigraph(in));
        in.close();
    }
    
}
