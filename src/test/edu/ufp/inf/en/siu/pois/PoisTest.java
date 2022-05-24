package test.edu.ufp.inf.en.siu.pois;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.edu.ufp.inf.en.models.siu.database.node.Node;
import main.edu.ufp.inf.en.models.siu.database.poi.Poi;
import main.edu.ufp.inf.en.models.siu.database.tag.Tag;

public class PoisTest {
    
    @Test
    public void addTagTest() {
        Node n = new Node();
        Poi p = new Poi(n);
        p.addTag(Tag.ADDR_STREET, "rua 9 de abril");
        assertTrue(!p.getTags().isEmpty());
        assertTrue(p.getTags().size() == 1);
        assertTrue(p.containsTag(Tag.ADDR_STREET));
    }

    @Test
    public void removeTagTest() {
        Node n = new Node();
        Poi p = new Poi(n);
        Tag t = Tag.ADDR_CITY;

        p.addTag(t, "rua 9 de abril");
        assertTrue(!p.getTags().isEmpty());
        assertTrue(p.getTags().size() == 1);
        assertTrue(p.containsTag(t));

        p.removeTag(t);
        assertTrue(p.getTags().isEmpty());
        assertTrue(!p.containsTag(t));
    }

    @Test
    public void editTagTest() {
        Node node = new Node();
        Poi p = new Poi(node);
        Tag o = Tag.ADDR_STREET;
        Tag n = Tag.ADDR_CITY;

        p.addTag(o, "rua 9 de abril");
        assertTrue(!p.getTags().isEmpty());
        assertTrue(p.getTags().size() == 1);

        
        assertTrue(!p.containsTag(o));
        assertTrue(p.containsTag(n));
    }

    @Test
    public void containsTagTest() {
        Node n = new Node();
        Poi p = new Poi(n);

        Tag one = Tag.ACCESS;
        Tag two = Tag.ADDR_STREET;

        p.addTag(one, "rua 9 de abril");
        assertTrue(!p.getTags().isEmpty());
        assertTrue(p.getTags().size() == 1);
        assertTrue(p.containsTag(one));
        assertTrue(!p.containsTag(two));
    }

    @Test
    public void listTagsTest() {
        Node n = new Node();
        Poi p = new Poi(n);

        p.addTag(Tag.ACCESS, "private");
        p.addTag(Tag.BIKELANE, "yes");
        p.addTag(Tag.ADDR_STREET, "rua 9 de abril");

        p.listTags();
    }

}
