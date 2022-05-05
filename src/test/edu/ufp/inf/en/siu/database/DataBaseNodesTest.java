package test.edu.ufp.inf.en.siu.database;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import main.edu.ufp.inf.en.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.siu.*;

public class DataBaseNodesTest {

    @Test
    public void addNodeTest() {
        DataBase db = new DataBase();
        Node n = new Node(1234, new Point(123, 321));

        db.addNode(n);
        assertTrue(!db.getNodesST().isEmpty());
        assertTrue(db.getNodesST().size() == 1);
    }

    @Test
    public void removeNodeTest() {
        DataBase db = new DataBase();
        Node n = new Node(1234, new Point(123, 321));

        db.addNode(n);
        assertTrue(!db.getNodesST().isEmpty());
        assertTrue(db.getNodesST().size() == 1);
        db.removeNode(n);
        assertTrue(db.getNodesST().isEmpty());
    }

    @Test
    public void editNodeTest() {
        DataBase db = new DataBase();
        Node o = new Node(1234, new Point(123, 321));
        Node n = new Node(4567, new Point(123, 321));

        db.addNode(n);
        assertTrue(!db.getNodesST().isEmpty());
        assertTrue(db.getNodesST().size() == 1);
        db.editNode(o, n);
        assertTrue(!db.getNodesST().contains(o.getNodeId()));
        assertTrue(db.getNodesST().contains(n.getNodeId()));
    }

    @Test
    public void searchNodeTest() {
        DataBase db = new DataBase();
        Node n1 = new Node(1234, new Point(123, 321));
        Node n2 = new Node(4567, new Point(176, 353));

        db.addNode(n1);
        assertTrue(!db.getNodesST().isEmpty());
        assertTrue(db.getNodesST().size() == 1);
        
        assertTrue(db.searchNode(n1.getNodeId()).equals(n1));
        assertTrue(!db.searchNode(n1.getNodeId()).equals(n2));
    }
    
}
