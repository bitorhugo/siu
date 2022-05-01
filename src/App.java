
import java.util.ArrayList;

import edu.princeton.cs.algs4.StdRandom;
import edu.ufp.inf.en.lp2._1_intro.date.Date;
import edu.ufp.inf.en.lp2._1_intro.geometric_figures.Point;
import edu.ufp.inf.en.siu.Admin;
import edu.ufp.inf.en.siu.Basic;
import edu.ufp.inf.en.siu.DataBase;
import edu.ufp.inf.en.siu.Node;
import edu.ufp.inf.en.siu.Poi;
import edu.ufp.inf.en.siu.Tag;
import edu.ufp.inf.en.siu.Upload;


public class App {
    public static void main(String[] args) throws Exception {    
    
        DataBase db = new DataBase();

        
        Upload.Nodes(db);
        db.listNodes();
        

    }
}
