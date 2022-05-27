package test.edu.ufp.inf.en.siu.database;

import org.junit.Test;

import main.edu.ufp.inf.en.models.siu.IO.Upload;
import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.database.tag.Tag;

public class DataBaseTagsTest {

    @Test
    public void listTagValuesTest () {
        DataBase db = new DataBase();
        Upload.Nodes(db);
        Upload.Ways(db);

        db.listTagValues(Tag.HIGHWAY);
    }

}
