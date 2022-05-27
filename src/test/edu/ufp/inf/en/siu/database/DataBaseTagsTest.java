package test.edu.ufp.inf.en.siu.database;

import org.junit.Test;

import main.edu.ufp.inf.en.models.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.models.siu.IO.Upload;
import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.database.tag.Tag;

public class DataBaseTagsTest {

    @Test
    public void listDataWithTagTest () {
        DataBase db = new DataBase();
        Upload.Nodes(db);
        Upload.Ways(db);

        db.listDataWithTag(Tag.HIGHWAY);
    }

    @Test   
    public void closestTrafficLightsTest () {
        DataBase db = new DataBase();
        Upload.Nodes(db);
        Upload.Ways(db);
        Point coordinate = new Point (0, 500);
        db.closestTrafficLights(coordinate);
        System.out.println(db.closestTrafficLights(coordinate)); 

    }
}
