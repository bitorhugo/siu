package test.edu.ufp.inf.en.siu.users;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.time.Instant;

import main.edu.ufp.inf.en.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.siu.database.DataBase;
import main.edu.ufp.inf.en.siu.database.poi.Poi;
import main.edu.ufp.inf.en.siu.user.Admin;
import main.edu.ufp.inf.en.siu.user.User;

public class UsersTest {
    
    @Test
    public void addVisitedPoiTest() {
        DataBase db = new DataBase();
        User a = new Admin("Vitor, ", "porto", "1234", null, null, null);
        db.addUser(a);

        Poi p = new Poi(234, new Point(1234, 432));
        db.addPoi(p);

        long timestamp = Instant.now().getEpochSecond();

        a.visitPoi(p, timestamp);
        
        assertTrue(!a.getVisitedPoi().isEmpty());
        assertTrue(a.getVisitedPoi().size() == 1);

        assertTrue(!p.getVisitorsEntrance().isEmpty());
        assertTrue(p.getVisitorsEntrance().size() == 1);
    }

    @Test
    public void historyTest() {
        DataBase db = new DataBase();
        User a = new Admin("Vitor, ", "porto", "1234", null, null, null);
        db.addUser(a);

        Poi p = new Poi(234, new Point(1234, 432));
        db.addPoi(p);

        long timestamp = Instant.now().getEpochSecond();

        a.visitPoi(p, timestamp);

        a.history();
        
    }

}
