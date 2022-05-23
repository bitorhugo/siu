package main.edu.ufp.inf.en.models.siu.IO;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import edu.princeton.cs.algs4.BinaryIn;
import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.database.node.Node;

@SuppressWarnings("unused")
public class UploadBIN {
    
    private static final String path_nodes = "/Users/VitorHugo/dev/java/projects/siu/data/out/bin/nodesBIN.bin";
    private static final String path_ways = "/Users/VitorHugo/dev/java/projects/siu/data/out/bin/waysBIN.bin";
    private static final String path_pois = "/Users/VitorHugo/dev/java/projects/siu/data/out/bin/poisBIN.bin";

    private UploadBIN () {}

    /**
     * uploads nodes from binary format file to database
     * @param db
     */
    public static void NodesBIN (DataBase db) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path_nodes))) {
            // We must read in the same order the object were saved
            Integer numNodes = (Integer) ois.readObject();
            for (int i = 0; i < numNodes; i++) {
                Node n = (Node) ois.readObject();
                db.addNode(n);
            }
        } catch (Exception e) {
            e.getCause();
        }
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
