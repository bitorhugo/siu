package edu.ufp.inf.en.siu;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.ufp.inf.en.lp2._1_intro.geometric_figures.Point;

public class Upload {

    private Upload(){}

    private static final String nodesPath = "data/in/dataset1_nodes.txt";
    private static final String waysPath = "data/in/dataset1_ways_nodepairs.txt";

    /**
     * uploads nodes from nodesPath to database
     * @param db database
     * @author Vitor Hugo
     */
    public static void uploadNodes(DataBase db) {
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
                Node n = new Node(Long.parseLong(lines[0]), new Point(Float.parseFloat(lines[1]), Float.parseFloat(lines[2])));
                // check to see if line contains key-value pairs
                if (lines.length > 2) {
                    SeparateChainingHashST<Tag, String> tags = new SeparateChainingHashST<>();
                    // iterate over remaining key value pairs of line
                    for (int i = 3; i < lines.length - 1; i+=2) {
                        tags.put(Tag.valueOf(lines[i].toUpperCase()), lines[i + 1]);
                    }
                    n.setTags(tags);
                }
                db.addNode(n);
            }
        }
    }

    /**
     * uploads ways from waysPath to database 
     * @param db database
     * @author Vitor Hugo
     */
    public static void uploadWays(DataBase db) {
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
                Way w = new Way(Long.parseLong(lines[0]), Long.parseLong(lines[1]), Long.parseLong(lines[2]), Double.parseDouble(lines[3]));
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

    }

    

}
