package test.edu.ufp.inf.en.siu.map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.princeton.cs.algs4.DirectedEdge;

import main.edu.ufp.inf.en.models.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.models.siu.IO.Upload;
import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.database.node.Node;
import main.edu.ufp.inf.en.models.siu.database.node.NodeAlreadyExistsException;
import main.edu.ufp.inf.en.models.siu.database.node.NodeNotFoundException;
import main.edu.ufp.inf.en.models.siu.database.tag.Tag;
import main.edu.ufp.inf.en.models.siu.database.transport.Transport;
import main.edu.ufp.inf.en.models.siu.database.way.WayNotFoundException;
import main.edu.ufp.inf.en.models.siu.map.Map;


public class MapTest {

    @Test
    public void subGraphTest () {
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

    @Test
    public void updateTest() {
        DataBase db = new DataBase();
        Upload.Nodes("/Users/VitorHugo/dev/java/projects/siu/data/in/txt/nodes/nodesTest.txt", db);
        Upload.Ways("/Users/VitorHugo/dev/java/projects/siu/data/in/txt/ways/waysTest.txt", db);

        Map main = new Map(db);
        int control = main.numberOfNodes();

        try {
            db.addNode(new Node(100, new Point(1000, 1000)));
        } catch (NodeAlreadyExistsException e) {
            e.getMessage();
        }
        main.update(db);

        int test = main.numberOfNodes();

        assertTrue(main.numberOfNodes() == db.numberOfNodes());
        assertTrue(test == control + 1);
    }

    @Test
    public void isConnectedTest() {
        DataBase dbControl = new DataBase();
        Upload.Nodes("/Users/VitorHugo/dev/java/projects/siu/data/in/txt/nodes/nodesTest.txt", dbControl);
        Upload.Ways("/Users/VitorHugo/dev/java/projects/siu/data/in/txt/ways/waysTest.txt", dbControl);

        Map control = new Map(dbControl);
        assertTrue(control.isConnected());
        
        DataBase dbTest = new DataBase();
        Upload.Nodes("/Users/VitorHugo/dev/java/projects/siu/data/in/txt/nodes/nodesTest.txt", dbTest);
        Upload.Ways("/Users/VitorHugo/dev/java/projects/siu/data/in/txt/ways/notConnectedWaysTest.txt", dbTest);
        
        Map test = new Map(dbTest);

        assertFalse(test.isConnected());
    }

    @Test
    public void shortestDistanceTest() throws NodeNotFoundException {
        DataBase db = new DataBase();
        Upload.Nodes("/Users/VitorHugo/dev/java/projects/siu/data/in/txt/nodes/nodesTest.txt", db);
        Upload.Ways("/Users/VitorHugo/dev/java/projects/siu/data/in/txt/ways/waysTest.txt", db);

        Map main = new Map(db);

        Node origin = db.searchNode(0);
        Node destination = db.searchNode(3);
        double distTo = main.shortestDistance(origin, destination);

        assertTrue(distTo == 2);
    }

    @Test
    public void shortestDistanceWithTransportTest() throws NodeNotFoundException {
        DataBase db = new DataBase();
        Upload.Nodes("/Users/VitorHugo/dev/java/projects/siu/data/in/txt/nodes/nodesTest.txt", db);
        Upload.Ways("/Users/VitorHugo/dev/java/projects/siu/data/in/txt/ways/waysTest.txt", db);

        Map main = new Map(db);

        Node origin = db.searchNode(0);
        Node destination = db.searchNode(3);

        double distTo = main.shortestDistance(origin, destination);
        assertTrue(distTo == 2);
        
        for (var transport : Transport.values()) {            
            double distToBus = main.shortestDistance(transport, origin, destination);
            assertTrue(distToBus == distTo/transport.speed);
        }
    }

    @Test
    public void shortestPathTest () throws NodeNotFoundException, WayNotFoundException {
        DataBase db = new DataBase();
        Upload.Nodes("/Users/VitorHugo/dev/java/projects/siu/data/in/txt/nodes/nodesTest.txt", db);
        Upload.Ways("/Users/VitorHugo/dev/java/projects/siu/data/in/txt/ways/waysTest.txt", db);

        Map main = new Map(db);

        Node origin = db.searchNode(0);
        Node destination = db.searchNode(3);

        // control list
        // (id:1 0->2)
        // (id:4 2->3)
        List<DirectedEdge> control = List.of(db.searchWay(1), db.searchWay(4));

        List<DirectedEdge> test = new ArrayList<>();
        main.shortestPath(origin, destination).forEach(edge -> {test.add(edge);});   
        main.shortestPathTime(main.shortestPath(origin, destination), Transport.BUS);
            
        assertTrue(control.equals(test));
    }

    @Test
    public void shortestPathTime() throws NodeNotFoundException {
        DataBase db = new DataBase();
        Upload.Nodes("/Users/VitorHugo/dev/java/projects/siu/data/in/txt/nodes/nodesTest.txt", db);
        Upload.Ways("/Users/VitorHugo/dev/java/projects/siu/data/in/txt/ways/waysTest.txt", db);

        Map main = new Map(db);

        Node origin = db.searchNode(0);
        Node destination = db.searchNode(3);

        Iterable<DirectedEdge> path = main.shortestPath(origin, destination);

        List<Long> timeControl = new ArrayList<>();
        Transport foot = Transport.WALKING;
        for (var p : main.shortestPath(origin, destination)) {
            long time = (long) (p.weight() / foot.speed);
            timeControl.add(time);
        }
        
        List<Long> timeTest = new ArrayList<>();
        
        main.shortestPathTime(path, foot).forEach(t -> {timeTest.add(t);});

        assertTrue(timeControl.equals(timeTest));

    }

}
