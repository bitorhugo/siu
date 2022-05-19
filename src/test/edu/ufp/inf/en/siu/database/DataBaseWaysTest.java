package test.edu.ufp.inf.en.siu.database;

import org.junit.Test;

import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.database.way.Way;

import static org.junit.Assert.assertTrue;

public class DataBaseWaysTest {

    @Test
    public void addWayTest() {
        DataBase db = new DataBase();
        Way w = new Way(123, 0, 1, 10);

        db.addWay(w);
        assertTrue(db.numberOfWays() == 1);
    }

    @Test
    public void removeWayTest() {
        DataBase db = new DataBase();
        Way w = new Way(123, 0, 1, 10);

        db.addWay(w);
        assertTrue(db.numberOfWays() == 1);
        db.removeWay(w);
        assertTrue(db.numberOfWays() == 0);
    }

    @Test
    public void editWayTest() {
        DataBase db = new DataBase();
        Way o = new Way(123, 0, 1, 10);
        Way n = new Way(321, 5, 1, 15);

        db.addWay(o);        
        assertTrue(db.numberOfWays() == 1);
        db.editWay(o, n);
        assertTrue(db.numberOfWays() == 1);
        assertTrue(!db.containsWay(o));
        assertTrue(db.containsWay(n));
    }
    
    @Test
    public void searchWayTest() {
        DataBase db = new DataBase();
        Way w2 = new Way(123, 0, 1, 10);
        Way w1 = new Way(321, 5, 1, 15);

        db.addWay(w1);
        assertTrue(db.numberOfWays() == 1);

        assertTrue(db.searchWay(w1.getWayId()).equals(w1));
        assertTrue(!db.searchWay(w1.getWayId()).equals(w2));
    }

    
}
