package main.edu.ufp.inf.en.siu.IO;

import edu.princeton.cs.algs4.BinaryOut;
import main.edu.ufp.inf.en.siu.database.DataBase;
import main.edu.ufp.inf.en.siu.database.Node;
import main.edu.ufp.inf.en.siu.database.Poi;
import main.edu.ufp.inf.en.siu.database.Way;

public class ArquiveBIN {
    
    /**
     * private constructor 
     */
    private ArquiveBIN() {}

    private final static String path_nodes_bin = "/Users/VitorHugo/dev/java/projects/siu/data/out/bin/nodesBIN.bin";
    private final static String path_ways_bin = "/Users/VitorHugo/dev/java/projects/siu/data/out/bin/waysBIN.bin";
    private final static String path_pois_bin = "/Users/VitorHugo/dev/java/projects/siu/data/out/bin/poisBIN.bin";

    /**
     * arquives nodes st from datase in binary format
     * FORMAT:
     *  number of nodes
     *  node n
     *  node n + 1
     *  ...
     * @param db database
     */
    public static void NodesBIN(DataBase db) {
        BinaryOut out = new BinaryOut(path_nodes_bin);
        out.write(String.valueOf(db.getNodesST().size()));
        out.write("\n");
        for (var v : db.getNodesST().keys()) {
            Node n = db.searchNode(v);
            out.write(n.toString());
            out.write("\n");
        }
        out.close();
    }

    /**
     * arquives ways st from database in bianry format
     *  FORMAT:
     *  number of ways
     *  way w
     *  way w + 1
     *  ...
     * @param db database
     */
    public static void WaysBIN(DataBase db) {
        BinaryOut out = new BinaryOut(path_ways_bin);
        out.write(db.getWaysST().size());
        for (var v : db.getWaysST().keys()) {
            Way w = db.searchWay(v);
            out.write(w.toString());
        }
        out.close();
    }
    
    /**
     * arquives pois st from database in binary format
     *  FORMAT:
     *  number of pois
     *  poi p
     *  poi p + 1
     *  ...
     * @param db database
     */
    public static void PoisBIN(DataBase db) {
        BinaryOut out = new BinaryOut(path_pois_bin);
        out.write(db.getPoiST().size());
        for (var v : db.getPoiST().keys()) {
            Poi p = db.searchPoi(v);
            out.write(p.toString());
        }
        out.close();
    }

}
