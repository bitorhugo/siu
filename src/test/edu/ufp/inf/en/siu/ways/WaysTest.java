package test.edu.ufp.inf.en.siu.ways;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.edu.ufp.inf.en.models.siu.database.tag.Tag;
import main.edu.ufp.inf.en.models.siu.database.way.Way;

public class WaysTest {
    
    @Test
    public void addTagTest() {
        Way w = new Way(123, 1, 2, 45);
        
        Tag t = Tag.ACCESS;

        w.addTag(t, "private");

        assertTrue(w.numberOfTags() == 1);
        assertTrue(w.containsTag(t));
    }

    @Test
    public void removeTagTest() {
        Way w = new Way(123, 1, 2, 45);
        
        Tag t = Tag.ACCESS;

        w.addTag(t, "private");
        assertTrue(w.numberOfTags() == 1);
        assertTrue(w.containsTag(t));

        w.removeTag(t);
        assertTrue(w.numberOfTags() == 0);
        assertTrue(!w.containsTag(t));
    }   

    @Test
    public void editTagTest() {
        Way w = new Way(123, 1, 2, 45);
        
        Tag o = Tag.ACCESS;
    
        w.addTag(o, "private");
        w.editTag(o, "rua 9 de abril");

        assertTrue(w.containsTag(o));
        assertTrue(w.getTagValue(o).equals("rua 9 de abril"));    
    }

    @Test
    public void listTagsTest() {
        Way w = new Way(123, 1, 2, 45);

        w.addTag(Tag.ACCESS, "private");
        w.addTag(Tag.BIKELANE, "no");
        w.addTag(Tag.CROSSING, "controlled");

        w.listTags();
        
    }

    @Test
    public void weightTest() {
        double weight = 45.234;
        Way w = new Way(123, 1, 2, weight);

        assertTrue(w.weight() == weight);
    }
    
}
