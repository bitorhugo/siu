package edu.ufp.inf.en.siu;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.ufp.inf.en.lp2._1_intro.geometric_figures.Point;

public class Upload {

    private Upload(){}

    private static final String nodesPath = "data/in/dataset1_nodes.txt";
    private static final String waysPath = "data/in/dataset1_ways_nodepairs.txt";

    /**
     * uploads nodes from nodesPath to database passes as parameter
     * @param db database
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

        int numNodes = in.readInt();
        
        while (in.hasNextLine()) {
            String []lines = in.readLine().split(",");
            if (lines[0].length() > 0) {
                Node n = new Node(Long.parseLong(lines[0]), new Point(Float.parseFloat(lines[1]), Float.parseFloat(lines[2])));
                if (lines.length > 2) {
                    SeparateChainingHashST<String, String> tags = new SeparateChainingHashST<>();
                    for (int i = 3; i < lines.length - 1; i+=2) {
                        tags.put(lines[i], lines [i + 1]);
                    }
                    //n.setTags(tags);
                }
                
                db.addNode(n);
            }
        }

       
    }

}
