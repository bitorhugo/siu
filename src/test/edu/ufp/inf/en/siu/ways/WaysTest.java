package test.edu.ufp.inf.en.siu.ways;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.edu.ufp.inf.en.siu.database.Tag;
import main.edu.ufp.inf.en.siu.database.way.Way;

public class WaysTest {
    
    @Test
    public void addTagTest() {
        Way w = new Way(123, 1, 2, 45);
        
        Tag t = Tag.ACCESS;

        w.addTag(t, "private");

        assertTrue(!w.getTags().isEmpty());
        assertTrue(w.getTags().size() == 1);
        assertTrue(w.containsTag(t));
    }

    @Test
    public void removeTagTest() {
        Way w = new Way(123, 1, 2, 45);
        
        Tag t = Tag.ACCESS;

        w.addTag(t, "private");
        assertTrue(!w.getTags().isEmpty());
        assertTrue(w.getTags().size() == 1);
        assertTrue(w.containsTag(t));

        w.removeTag(t);
        assertTrue(w.getTags().isEmpty());
        assertTrue(!w.containsTag(t));
    }   

    @Test
    public void editTagTest() {
        Way w = new Way(123, 1, 2, 45);
        
        Tag o = Tag.ACCESS;
        Tag n = Tag.ADDR_CITY;

        w.addTag(o, "private");
        w.editTag(o, n, "rua 9 de abril");

        assertTrue(!w.containsTag(o));
        assertTrue(w.containsTag(n));
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
