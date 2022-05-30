package test.edu.ufp.inf.en.siu.database;

import org.junit.Test;

import main.edu.ufp.inf.en.models.siu.IO.Upload;
import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.database.node.Node;
import main.edu.ufp.inf.en.models.siu.database.node.NodeNotFoundException;
import main.edu.ufp.inf.en.models.siu.database.way.Way;
import main.edu.ufp.inf.en.models.siu.database.way.WayAlreadyExistsException;
import main.edu.ufp.inf.en.models.siu.database.way.WayNotFoundException;

import static org.junit.Assert.assertTrue;

@SuppressWarnings("unused")
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
    public void editWayTest() throws WayAlreadyExistsException, NodeNotFoundException, WayNotFoundException {
        DataBase db = new DataBase();
        Upload.Nodes(db);
        
        Node a = db.searchNode(10);
        Node b = db.searchNode(11);
        Node c = db.searchNode(12);

        Way w = new Way(0, 10, 11, 123);

        db.addWay(w);
        
        db.editWay(0, b, c, 321);

        Way ww = db.searchWay(0);
        
        assertTrue(ww.from() == b.getNodeId());
        assertTrue(ww.to() == c.getNodeId());
        assertTrue(ww.weight() == 321);
        
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
