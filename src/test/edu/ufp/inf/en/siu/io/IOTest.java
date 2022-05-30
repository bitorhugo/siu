package test.edu.ufp.inf.en.siu.io;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.edu.ufp.inf.en.models.siu.IO.Arquive;
import main.edu.ufp.inf.en.models.siu.IO.ArquiveBIN;
import main.edu.ufp.inf.en.models.siu.IO.Upload;
import main.edu.ufp.inf.en.models.siu.IO.UploadBIN;
import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.map.Map;

public class IOTest {

    @Test
    public void NodesTest() {
        DataBase db = new DataBase();
        DataBase dbb = new DataBase();
        Upload.Nodes(db);

        assertTrue(db.numberOfNodes() == 21);

        Arquive.Nodes(db);

        Upload.Nodes(dbb);

        assertTrue(dbb.numberOfNodes() == 21);

        dbb.listNodes();
    }

    @Test
    public void PoisTest () {
        DataBase db = new DataBase();
        DataBase dbb = new DataBase();
        Upload.Nodes(db);

        assertTrue(db.numberOfPois() == 7);

        Arquive.Nodes(db);

        Upload.Nodes(dbb);

        assertTrue(dbb.numberOfPois() == 7);
    }
    
    @Test
    public void WaysTest() {
        DataBase db = new DataBase();
        DataBase dbb = new DataBase();

        Upload.Ways(db);
        assertTrue(db.numberOfWays() == 48);

        Arquive.Ways(db);

        Upload.Ways(dbb);
        assertTrue(dbb.numberOfWays() == 48);
    }

    @Test
    public void GraphTest() {
        Map map = new Map();
        Upload.Graph(map);

        assertTrue(map.numberOfNodes() == 21);
        assertTrue(map.numberOfWays() == 48);
    }

    @Test
    public void NodesBinTest() {
        DataBase db = new DataBase();
        DataBase dbb = new DataBase();
        Upload.Nodes(db);

        assertTrue(db.numberOfNodes() != 0);

        ArquiveBIN.NodesBIN(db);

        UploadBIN.NodesBIN(dbb);

        assertTrue(dbb.numberOfNodes() == 14);

        dbb.listNodes();

    }

    @Test
    public void PoisBinTest() {
        DataBase db = new DataBase();
        DataBase dbb = new DataBase();
        Upload.Nodes(db);

        ArquiveBIN.PoisBIN(db);

        UploadBIN.PoisBIN(dbb);

        assertTrue(dbb.numberOfPois() == 7);
        dbb.listPoi();
    }

    @Test
    public void WaysBinTest() {
        DataBase db = new DataBase();
        DataBase dbb = new DataBase();
        Upload.Ways(db);

        ArquiveBIN.WaysBIN(db);

        assertTrue(db.numberOfWays() == 48);

        UploadBIN.WaysBIN(dbb);

        assertTrue(dbb.numberOfWays() == 48);

        dbb.listWays();
    }

    @Test
    public void GraphBinTest() {
        Map map = new Map();
        Upload.Graph(map);

        assertTrue(map.numberOfNodes() == 21);
        assertTrue(map.numberOfWays() == 48);

        ArquiveBIN.GraphBIN(map);

        Map test = new Map();
        UploadBIN.GraphBIN(test);

        assertTrue(test.numberOfNodes() == map.numberOfNodes());
        assertTrue(test.numberOfWays() == map.numberOfWays());
    }
}
