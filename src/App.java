
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdRandom;
import edu.ufp.inf.en.lp2._1_intro.date.Date;
import edu.ufp.inf.en.lp2._1_intro.geometric_figures.Point;
import edu.ufp.inf.en.siu.Admin;
import edu.ufp.inf.en.siu.Basic;
import edu.ufp.inf.en.siu.DataBase;
import edu.ufp.inf.en.siu.Map;
import edu.ufp.inf.en.siu.Node;
import edu.ufp.inf.en.siu.Poi;
import edu.ufp.inf.en.siu.Tag;
import edu.ufp.inf.en.siu.Upload;


public class App {
    public static void main(String[] args) throws Exception {    
    
        DataBase db = new DataBase();

        //TODO: change Date and Long type for Calendar

        Admin b = new Admin("Vitor", "Porto", "38132", new Date(21, 8, 1998), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(b);
        db.removeUser(b);
        
        Upload.Nodes(db);
        Upload.Ways(db);
        db.removeWay(db.getWaysST().get(13797641));
        db.listWays();
        
        Map map = new Map(db);
        System.out.println(map.getGraph());
    }
}
