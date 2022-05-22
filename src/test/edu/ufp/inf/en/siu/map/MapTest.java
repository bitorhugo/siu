package test.edu.ufp.inf.en.siu.map;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.edu.ufp.inf.en.models.siu.IO.Upload;
import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.database.tag.Tag;
import main.edu.ufp.inf.en.models.siu.map.Map;



public class MapTest {


    @Test
    public void subgrahpTest () {
        DataBase db = new DataBase();
        Upload.Nodes("/Users/VitorHugo/dev/java/projects/siu/data/in/txt/nodes/nodesTest.txt", db);
        Upload.Ways("/Users/VitorHugo/dev/java/projects/siu/data/in/txt/ways/waysTest.txt", db);

        Map main = new Map(db);
        Map sub = new Map(main, Tag.HIGHWAY);
        for (var v : sub.indeces()) {
            System.out.println("index: " + v + "node: " + sub.getNodeFromIndex(v));
        }
        assertTrue(sub.getGraph().V() == 3);
        assertTrue(sub.getGraph().E() == 4);


    }

}
