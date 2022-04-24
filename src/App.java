import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.ufp.inf.en.lp2._1_intro.date.Date;
import edu.ufp.inf.en.siu.Admin;
import edu.ufp.inf.en.siu.DataBase;


public class App {
    public static void main(String[] args) throws Exception {

        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();
        st.put("Hugo", 122134);
        st.put("Hugo", 12343);
        st.put("Hugo", 144);
        st.put("Hugo", 125);

        DataBase db = new DataBase();
        Admin a = new Admin("Hugo", "porto", "1234", new Date(21, 8, 1998), "wsfn", "ieuwrfn");
        Admin b = new Admin("andre", "porto", "3211", new Date(21, 8, 1998), "wsfn", "ieuwrfn");
        
        db.addUser(a);
        db.addUser(b);
        a.removeUser(b);

        db.listUsers();
    }
}
