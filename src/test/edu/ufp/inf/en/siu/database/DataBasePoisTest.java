package test.edu.ufp.inf.en.siu.database;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.edu.ufp.inf.en.models.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.database.poi.Poi;
import main.edu.ufp.inf.en.models.siu.database.poi.PoiNotFoundException;

public class DataBasePoisTest {
    

    @Test
    public void addPoiTest() {
        DataBase db = new DataBase();
        Poi poi = new Poi(123, new Point(435.2f, 879f));

        db.addPoi(poi);

        assertTrue(db.numberOfPois() == 1);

        assertTrue(db.numberOfNodes() == 1);
    }

    @Test 
    public void removePoiTest() throws PoiNotFoundException {
        DataBase db = new DataBase();
        Poi poi = new Poi(123, new Point(435.2f, 879f));

        db.addPoi(poi);

        assertTrue(db.numberOfPois() == 1);

        assertTrue(db.numberOfNodes() == 1);

        db.removePoi(poi);

        assertTrue(db.numberOfPois() == 0);

        assertTrue(db.numberOfNodes() == 0);
    }

    @Test
    public void editPoiTest() throws PoiNotFoundException {
        DataBase db = new DataBase();
        Poi o = new Poi(123, new Point(435.2f, 879f));
        Poi n = new Poi(321, new Point(65.2f, 12f));

        db.addPoi(o);
        assertTrue(db.numberOfPois() == 1);

        db.editPoi(o, n);
        assertTrue(!db.containsNode(o));
        assertTrue(db.containsNode(n));

        assertTrue(!db.containsNode(o));
        assertTrue(db.containsNode(n));
    }

    @Test
    public void searchPoiTest() {
        DataBase db = new DataBase();
        Poi p1 = new Poi(123, new Point(435.2f, 879f));
        Poi p2 = new Poi(321, new Point(345.2f, 123f));

        db.addPoi(p1);
        db.addPoi(p2);

        assertTrue(db.numberOfPois() == 2);

        assertTrue(db.numberOfNodes() == 2);

        assertTrue(db.searchPoi(p1.getNodeId()).equals(p1));
        assertTrue(!db.searchPoi(p1.getNodeId()).equals(p2));
        
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
