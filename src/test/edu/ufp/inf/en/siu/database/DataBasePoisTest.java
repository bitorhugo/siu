package test.edu.ufp.inf.en.siu.database;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.edu.ufp.inf.en.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.siu.DataBase;
import main.edu.ufp.inf.en.siu.Poi;

public class DataBasePoisTest {
    

    @Test
    public void addPoiTest() {
        DataBase db = new DataBase();
        Poi poi = new Poi(123, new Point(435.2f, 879f));

        db.addPoi(poi);

        assertTrue(!db.getPoiST().isEmpty());
        assertTrue(db.getPoiST().size() == 1);

        assertTrue(!db.getNodesST().isEmpty());
        assertTrue(db.getNodesST().size() == 1);
    }

    @Test 
    public void removePoiTest() {
        DataBase db = new DataBase();
        Poi poi = new Poi(123, new Point(435.2f, 879f));

        db.addPoi(poi);

        assertTrue(!db.getPoiST().isEmpty());
        assertTrue(db.getPoiST().size() == 1);

        assertTrue(!db.getNodesST().isEmpty());
        assertTrue(db.getNodesST().size() == 1);

        db.removePoi(poi);

        assertTrue(db.getPoiST().isEmpty());

        assertTrue(db.getNodesST().isEmpty());
    }

    @Test
    public void editPoiTest() {
        DataBase db = new DataBase();
        Poi o = new Poi(123, new Point(435.2f, 879f));
        Poi n = new Poi(321, new Point(65.2f, 12f));

        db.addPoi(o);
        assertTrue(!db.getPoiST().isEmpty());
        assertTrue(db.getPoiST().size() == 1);

        db.editPoi(o, n);
        assertTrue(!db.getPoiST().contains(o.getNodeId()));
        assertTrue(db.getPoiST().contains(n.getNodeId()));

        assertTrue(!db.getNodesST().contains(o.getNodeId()));
        assertTrue(db.getNodesST().contains(n.getNodeId()));
    }

    @Test
    public void searchPoiTest() {
        DataBase db = new DataBase();
        Poi p1 = new Poi(123, new Point(435.2f, 879f));
        Poi p2 = new Poi(321, new Point(345.2f, 123f));

        db.addPoi(p1);
        db.addPoi(p2);

        assertTrue(!db.getPoiST().isEmpty());
        assertTrue(db.getPoiST().size() == 2);

        assertTrue(!db.getNodesST().isEmpty());
        assertTrue(db.getNodesST().size() == 2);

        assertTrue(db.searchPoi(p1).equals(p1));
        assertTrue(!db.searchPoi(p1).equals(p2));
        
        assertTrue(db.searchNode(p1.getNodeId()).equals(p1));
        assertTrue(!db.searchNode(p1.getNodeId()).equals(p2));
    }

    @Test
    public void listPoiTest() {
        DataBase db = new DataBase();
        Poi p1 = new Poi(123, new Point(435.2f, 879f));
        Poi p2 = new Poi(321, new Point(345.2f, 123f));

        db.addPoi(p1);
        db.addPoi(p2);

        db.listPoi();
    }

}
