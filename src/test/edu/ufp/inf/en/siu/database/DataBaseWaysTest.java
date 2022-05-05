package test.edu.ufp.inf.en.siu.database;

import java.time.LocalDate;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import main.edu.ufp.inf.en.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.siu.*;

public class DataBaseWaysTest {

    @Test
    public void addWayTest() {
        DataBase db = new DataBase();
        Way w = new Way(123, 0, 1, 10);

        db.addWay(w);
        assertTrue(!db.getWaysST().isEmpty());
        assertTrue(db.getWaysST().size() == 1);
    }
    
}
