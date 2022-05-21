package main.edu.ufp.inf.en.models.siu.IO;

import edu.princeton.cs.algs4.BinaryIn;
import main.edu.ufp.inf.en.models.siu.database.DataBase;

@SuppressWarnings("unused")
public class UploadBIN {
    
    private static final String path_nodes = "/Users/VitorHugo/dev/java/projects/siu/data/in/bin/nodesBIN.bin";
    private static final String path_ways = "/Users/VitorHugo/dev/java/projects/siu/data/out/bin/waysBIN.bin";
    private static final String path_pois = "/Users/VitorHugo/dev/java/projects/siu/data/out/bin/poisBIN.bin";

    private UploadBIN () {}

    /**
     * uploads nodes from binary format file to database
     * @param db
     */
    public static void NodesBIN (DataBase db) {
        
        
    }

    /**
     * uploads ways from binary format file to database
     * @param db database
     */
    public static void WaysBIN (DataBase db) {
        BinaryIn in = new BinaryIn(path_ways);
        String waysDataSet = in.readString();
        Upload.Ways(waysDataSet, db);
    }

    public static void PoisBIN (DataBase db) {
        
        
        
    }

}
