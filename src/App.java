
import edu.princeton.cs.algs4.StdRandom;
import edu.ufp.inf.en.lp2._1_intro.date.Date;
import edu.ufp.inf.en.lp2._1_intro.geometric_figures.Point;
import edu.ufp.inf.en.siu.Admin;
import edu.ufp.inf.en.siu.Basic;
import edu.ufp.inf.en.siu.DataBase;
import edu.ufp.inf.en.siu.Node;
import edu.ufp.inf.en.siu.Poi;


public class App {
    public static void main(String[] args) throws Exception {

        DataBase db = new DataBase();
        Admin a = new Admin("Hugo", "porto", "1234", new Date(21, 8, 1998), "wsfn", "ieuwrfn");
        Admin b = new Admin("Camila", "porto", "3211", new Date(9, 12, 2001), "sadasd", "sdf");
        Basic c = new Basic("Nicole", "porto", "3479", new Date(24, 6, 1967), "jijfd", "kuf");

        db.addUser(a);
        a.addUser(b);
        a.addUser(c);
        a.removeUser(b);

        db.listUsers();

        for (int i = 0; i < 10; i++) {
            Node n = new Node(i, new Point(StdRandom.uniform(-100, 100), StdRandom.uniform(-100, 100)));
            a.addVisitedPoi(new Poi(n));
        }

        a.listVisitedPoi();
        a.getDb().listNodes();
    

    }
}
