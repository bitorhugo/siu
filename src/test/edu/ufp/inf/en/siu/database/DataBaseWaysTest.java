package test.edu.ufp.inf.en.siu.database;

import org.junit.Test;

import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.database.way.Way;
import main.edu.ufp.inf.en.models.siu.database.way.WayAlreadyExistsException;
import main.edu.ufp.inf.en.models.siu.database.way.WayNotFoundException;

import static org.junit.Assert.assertTrue;

public class DataBaseWaysTest {

    @Test
    public void addWayTest() throws WayAlreadyExistsException {
        DataBase db = new DataBase();
        Way w = new Way(123, 0, 1, 10);

        db.addWay(w);
        assertTrue(db.numberOfWays() == 1);
    }

    @Test
    public void removeWayTest() throws WayAlreadyExistsException, WayNotFoundException {
        DataBase db = new DataBase();
        Way w = new Way(123, 0, 1, 10);

        db.addWay(w);
        assertTrue(db.numberOfWays() == 1);
        db.removeWay(w);
        assertTrue(db.numberOfWays() == 0);
    }

    @Test
    public void editWayTest() throws WayAlreadyExistsException {
        DataBase db = new DataBase();
        Way o = new Way(123, 0, 1, 10);
        Way n = new Way(321, 5, 1, 15);

        db.addWay(o);        
        assertTrue(db.numberOfWays() == 1);
        //db.editWay(o, n);
        assertTrue(db.numberOfWays() == 1);
        assertTrue(!db.containsWay(o.getWayId()));
        assertTrue(db.containsWay(n.getWayId()));
    }
    
    @Test
    public void searchWayTest() throws WayAlreadyExistsException, WayNotFoundException {
        DataBase db = new DataBase();
        Way w2 = new Way(123, 0, 1, 10);
        Way w1 = new Way(321, 5, 1, 15);

        db.addWay(w1);
        assertTrue(db.numberOfWays() == 1);

        assertTrue(db.searchWay(w1.getWayId()).equals(w1));
        assertTrue(!db.searchWay(w1.getWayId()).equals(w2));
    }

    
}
