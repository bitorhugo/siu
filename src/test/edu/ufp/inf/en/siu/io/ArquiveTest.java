package test.edu.ufp.inf.en.siu.io;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.edu.ufp.inf.en.models.siu.IO.ArquiveBIN;
import main.edu.ufp.inf.en.models.siu.IO.Upload;
import main.edu.ufp.inf.en.models.siu.IO.UploadBIN;
import main.edu.ufp.inf.en.models.siu.database.DataBase;

public class ArquiveTest {
    
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

}
