package main.edu.ufp.inf.en.models.siu.IO;

import edu.princeton.cs.algs4.BinaryOut;
import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.database.node.Node;
import main.edu.ufp.inf.en.models.siu.database.poi.Poi;
import main.edu.ufp.inf.en.models.siu.database.way.Way;
import main.edu.ufp.inf.en.models.siu.map.Map;

public class ArquiveBIN {
    
    /**
     * private constructor 
     */
    private ArquiveBIN() {}

    private static final String NEWLINE = "\n";

    private final static String path_nodes_bin = "/Users/VitorHugo/dev/java/projects/siu/data/out/bin/nodesBIN.bin";
    private final static String path_ways_bin = "/Users/VitorHugo/dev/java/projects/siu/data/out/bin/waysBIN.bin";
    private final static String path_pois_bin = "/Users/VitorHugo/dev/java/projects/siu/data/out/bin/poisBIN.bin";
    private final static String path_graph_bin = "/Users/VitorHugo/dev/java/projects/siu/data/out/bin/graphBIN.bin";

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
        out.write(NEWLINE);
        for (var v : db.getPoiST().keys()) {
            Poi p = db.searchPoi(v);
            out.write(p.toString());
            out.write(NEWLINE);
        }
        out.close();
    }

    /**
     * arquives graph from map in binary format
     * @param map map
     */
    public static void GraphBIN (Map map) {
        BinaryOut out = new BinaryOut(path_graph_bin);
        out.write(String.valueOf(map.getGraph().V()));
        out.write(NEWLINE);
        out.write(String.valueOf(map.getGraph().E()));
        out.write(NEWLINE);

        for (var v : map.getGraph().edges()) {
            out.write(v.toString());
            out.write(NEWLINE);
        }
        out.close();
    }
}
