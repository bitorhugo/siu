package test.edu.ufp.inf.en.siu.database;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import main.edu.ufp.inf.en.siu.database.DataBase;
import main.edu.ufp.inf.en.siu.database.Way;

public class DataBaseWaysTest {

    @Test
    public void addWayTest() {
        DataBase db = new DataBase();
        Way w = new Way(123, 0, 1, 10);

        db.addWay(w);
        assertTrue(!db.getWaysST().isEmpty());
        assertTrue(db.getWaysST().size() == 1);
    }

    @Test
    public void removeWayTest() {
        DataBase db = new DataBase();
        Way w = new Way(123, 0, 1, 10);

        db.addWay(w);
        assertTrue(!db.getWaysST().isEmpty());
        assertTrue(db.getWaysST().size() == 1);
        db.removeWay(w);
        assertTrue(db.getWaysST().isEmpty());
    }

    @Test
    public void editWayTest() {
        DataBase db = new DataBase();
        Way o = new Way(123, 0, 1, 10);
        Way n = new Way(321, 5, 1, 15);

        db.addWay(o);
        assertTrue(!db.getWaysST().isEmpty());
        assertTrue(db.getWaysST().size() == 1);
        db.editWay(o, n);
        assertTrue(db.getWaysST().size() == 1);
        assertTrue(!db.getWaysST().contains(o.getWayId()));
        assertTrue(db.getWaysST().contains(n.getWayId()));
    }
    
    @Test
    public void searchWayTest() {
        DataBase db = new DataBase();
        Way w2 = new Way(123, 0, 1, 10);
        Way w1 = new Way(321, 5, 1, 15);

        db.addWay(w1);
        assertTrue(!db.getWaysST().isEmpty());
        assertTrue(db.getWaysST().size() == 1);

        assertTrue(db.searchWay(w1).equals(w1));
        assertTrue(!db.searchWay(w1).equals(w2));
    }

    
}
