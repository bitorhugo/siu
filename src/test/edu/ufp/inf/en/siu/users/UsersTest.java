package test.edu.ufp.inf.en.siu.users;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import main.edu.ufp.inf.en.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.siu.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class UsersTest {
    
    @Test
    public void addVisitedPoiTest() {
        DataBase db = new DataBase();
        User a = new Admin("Vitor, ", "porto", "1234", null, null, null);
        db.addUser(a);

        Poi p = new Poi(234, new Point(1234, 432));
        db.addPoi(p);

        TimePeriod tp = new TimePeriod(LocalDateTime.now(),
            LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 55, 12)));

        a.addVisitedPoi(p, tp);
        
        assertTrue(!a.getVisitedPoi().isEmpty());
        assertTrue(a.getVisitedPoi().size() == 1);

        assertTrue(!p.getVisitorST().isEmpty());
        assertTrue(p.getVisitorST().size() == 1);
        assertTrue(p.containsVisitor(a));
    }

}
