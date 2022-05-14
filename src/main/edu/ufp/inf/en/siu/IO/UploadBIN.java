package main.edu.ufp.inf.en.siu.IO;

import edu.princeton.cs.algs4.BinaryIn;
import main.edu.ufp.inf.en.siu.database.DataBase;

public class UploadBIN {
    
    private static final String path_nodes = "/Users/VitorHugo/dev/java/projects/siu/data/in/bin/nodesBIN.bin";

    private UploadBIN () {}

    public static void NodesBIN (DataBase db) {
        BinaryIn in = new BinaryIn(path_nodes);
        String nodesdataset = in.readString();
        Upload.Nodes(nodesdataset, db);
    }

}
