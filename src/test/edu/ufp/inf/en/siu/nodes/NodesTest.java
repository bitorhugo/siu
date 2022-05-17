package test.edu.ufp.inf.en.siu.nodes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.edu.ufp.inf.en.models.siu.database.node.Node;
import main.edu.ufp.inf.en.models.siu.database.tag.Tag;

public class NodesTest {
    
    @Test
    public void aadTagTest() {
        Node n = new Node();

        n.addTag(Tag.ADDR_STREET, "rua 9 de abril");
        assertTrue(!n.getTags().isEmpty());
        assertTrue(n.getTags().size() == 1);
        assertTrue(n.containsTag(Tag.ADDR_STREET));
    }

    @Test
    public void removeTagTest() {
        Node n = new Node();

        Tag t = Tag.ADDR_CITY;

        n.addTag(t, "rua 9 de abril");
        assertTrue(!n.getTags().isEmpty());
        assertTrue(n.getTags().size() == 1);
        assertTrue(n.containsTag(t));

        n.removeTag(t);
        assertTrue(n.getTags().isEmpty());
        assertTrue(!n.containsTag(t));
    }

    @Test
    public void editTagTest() {
        Node node = new Node();

        Tag o = Tag.ADDR_STREET;
        Tag n = Tag.ADDR_CITY;

        node.addTag(o, "rua 9 de abril");
        assertTrue(!node.getTags().isEmpty());
        assertTrue(node.getTags().size() == 1);

        node.editTag(o, n, "porto");
        assertTrue(!node.containsTag(o));
        assertTrue(node.containsTag(n));
    }

    @Test
    public void containsTagTest() {
        Node n = new Node();

        Tag one = Tag.ACCESS;
        Tag two = Tag.ADDR_STREET;

        n.addTag(one, "rua 9 de abril");
        assertTrue(!n.getTags().isEmpty());
        assertTrue(n.getTags().size() == 1);
        assertTrue(n.containsTag(one));
        assertTrue(!n.containsTag(two));
    }

    @Test
    public void listTagsTest() {
        Node n = new Node();

        n.addTag(Tag.ACCESS, "private");
        n.addTag(Tag.BIKELANE, "yes");
        n.addTag(Tag.ADDR_STREET, "rua 9 de abril");

        n.listTags();
    }

}
