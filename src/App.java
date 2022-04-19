import edu.ufp.inf.en.lp2._1_intro.date.Date;
import edu.ufp.inf.en.siu.Admin;
import edu.ufp.inf.en.siu.DataBase;

public class App {
    public static void main(String[] args) throws Exception {
        DataBase db = new DataBase();
        Admin a = new Admin("Hugo", "porto", "1234", new Date(21, 8, 1998), "wsfn", "ieuwrfn");
        Admin b = new Admin("andre", "porto", "3211", new Date(21, 8, 1998), "wsfn", "ieuwrfn");
        
        db.addUser(a);
        db.addUser(b);
        a.removeUser(b);
        
        db.listUsers();
    }
}
