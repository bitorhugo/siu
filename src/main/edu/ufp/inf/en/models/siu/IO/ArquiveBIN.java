package main.edu.ufp.inf.en.models.siu.IO;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import edu.princeton.cs.algs4.BinaryOut;
import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.database.node.Node;
import main.edu.ufp.inf.en.models.siu.database.poi.Poi;
import main.edu.ufp.inf.en.models.siu.database.way.Way;
import main.edu.ufp.inf.en.models.siu.map.Map;
import main.edu.ufp.inf.en.models.siu.user.User;

@SuppressWarnings("unused")
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
        // try with resources automatically closes file :)
       try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path_nodes_bin))) {
           Integer numNodes = db.numberOfNodes();
           oos.writeObject(numNodes);
           for (Integer nodeID : db.nodesKeys()) {
               Node n = db.searchNode(nodeID);
               if (!(n instanceof Poi)) {
                   oos.writeObject(n);
               }
           }
           oos.flush();
       } catch (Exception e) {
           e.printStackTrace();
       }
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
        // can't serialize algs4 dts..
                // loop through dt and arquive content
                /*
                Poi p = (Poi) n;
                int tagsSize = p.getTags().size();
                oos.writeInt(tagsSize); // save hash table size
                for (var tags : p.getTags().keys()) {
                    oos.writeObject(tags); // save key
                    oos.writeObject(p.getTags().get(tags)); // save value
                }
                int visitorsEntranceSize = p.getVisitorsEntrance().size(); // save bst size
                oos.writeInt(visitorsEntranceSize);
                for (var entrance : p.getVisitorsEntrance().keys()) {
                    oos.writeLong(entrance); // save key
                    List<String> visitors = p.getVisitorsEntrance().get(entrance); // get value
                    oos.writeInt(visitors.size()); // save value size
                    for (var userID : visitors) {
                        oos.writeObject(userID); // save value
                    }
                }*/
    }

    /**
     * arquives graph from map in binary format
     * @param map map
     */
    public static void GraphBIN (Map map) {
        
    }
}
