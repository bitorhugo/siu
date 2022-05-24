package main.edu.ufp.inf.en.models.siu.IO;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import main.edu.ufp.inf.en.models.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.database.node.Node;
import main.edu.ufp.inf.en.models.siu.database.poi.Poi;
import main.edu.ufp.inf.en.models.siu.database.tag.Tag;
import main.edu.ufp.inf.en.models.siu.database.way.Way;

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
            int numNodes = ois.readInt();
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
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path_ways))) {
            int numWays = ois.readInt();
            for (int i = 0; i < numWays; i++) {
                int wayID = ois.readInt();
                int origin = ois.readInt();
                int destination = ois.readInt();
                double weight = ois.readDouble();
                Way w = new Way(wayID, origin, destination, weight);
                int tagsSize = ois.readInt();
                
                for (int j = 0; j < tagsSize; j++) {
                    Tag t = (Tag) ois.readObject();
                    String tagValue = (String) ois.readObject();
                    w.addTag(t, tagValue);
                }
                db.addWay(w);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void PoisBIN (DataBase db) {
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path_pois))) {
            int numberPois = ois.readInt();
            for (int i = 0; i < numberPois; i++) {
                Poi p = new Poi(ois.readInt(), (Point)ois.readObject());
                SeparateChainingHashST<Tag, String> tags = new SeparateChainingHashST<>();
                int tagsSize = ois.readInt();
                for (int j = 0; j < tagsSize; j++) {
                    Tag t = (Tag) ois.readObject();
                    String tagValue = (String) ois.readObject();
                    tags.put(t, tagValue);
                }
                p.setTags(tags); // set tags
                RedBlackBST<Long, ArrayList<String>> visitorEntrances = new RedBlackBST<>();
                int visitorEntrancesSize = ois.readInt();
                for (int j = 0; j < visitorEntrancesSize; j++) {
                    Long entrance = ois.readLong();
                    ArrayList<String> visitors = new ArrayList<>();
                    int visitorsSize = ois.readInt();
                    for (int k = 0; k < visitorsSize; k++) {
                        String userID = (String) ois.readObject();
                        visitors.add(userID);
                    }
                    visitorEntrances.put(entrance, visitors);
                }
                p.setVisitorsEntrance(visitorEntrances);
                db.addPoi(p);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        
    }

}
