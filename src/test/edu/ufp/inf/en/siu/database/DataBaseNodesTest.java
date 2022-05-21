package test.edu.ufp.inf.en.siu.database;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import main.edu.ufp.inf.en.models.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.database.node.Node;
import main.edu.ufp.inf.en.models.siu.database.node.NodeNotFoundException;

public class DataBaseNodesTest {

    @Test
    public void addNodeTest() {
        DataBase db = new DataBase();
        Node n = new Node(1234, new Point(123, 321));

        db.addNode(n);
        System.out.println(n);
        assertTrue(db.numberOfNodes() == 1);
    }

    @Test
    public void removeNodeTest() throws NodeNotFoundException {
        DataBase db = new DataBase();
        Node n = new Node(1234, new Point(123, 321));

        db.addNode(n);
        assertTrue(db.numberOfNodes() == 1);
        db.removeNode(n);
        assertTrue(db.numberOfNodes() == 0);   
    }

    @Test
    public void editNodeTest() throws NodeNotFoundException {
        DataBase db = new DataBase();
        Node o = new Node(1234, new Point(123, 321));
        Node n = new Node(4567, new Point(123, 321));

        db.addNode(n);
        assertTrue(db.numberOfNodes() == 1);
        db.editNode(o, n);
        assertTrue(!db.containsNode(o));
        assertTrue(db.containsNode(n));
    }

    @Test
    public void searchNodeTest() throws NodeNotFoundException {
        DataBase db = new DataBase();
        Node n1 = new Node(1234, new Point(123, 321));
        Node n2 = new Node(4567, new Point(176, 353));

        db.addNode(n1);
        assertTrue(db.numberOfNodes() == 1);
        
        assertTrue(db.searchNode(n1.getNodeId()).equals(n1));
        assertTrue(!db.searchNode(n1.getNodeId()).equals(n2));
    }
    
}
