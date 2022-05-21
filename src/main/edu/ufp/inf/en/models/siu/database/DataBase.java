package main.edu.ufp.inf.en.models.siu.database;


import java.time.Instant;
import java.util.ArrayList;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdRandom;
import main.edu.ufp.inf.en.models.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.models.siu.IO.Arquive;
import main.edu.ufp.inf.en.models.siu.database.node.Node;
import main.edu.ufp.inf.en.models.siu.database.node.NodeNotPresentException;
import main.edu.ufp.inf.en.models.siu.database.poi.Poi;
import main.edu.ufp.inf.en.models.siu.database.tag.Tag;
import main.edu.ufp.inf.en.models.siu.database.way.Way;
import main.edu.ufp.inf.en.models.siu.user.User;

// TODO: create exceptions for when a entity is not on database (e.g. removePoi(p) throws PoiNotFoundException)
public class DataBase {
  
  private RedBlackBST<String, User> userST = new RedBlackBST<>();
  
  private RedBlackBST<Integer, Node> nodesST = new RedBlackBST<>();
  
  private RedBlackBST<Integer, Way> waysST = new RedBlackBST<>();
  
  private RedBlackBST<Integer, Poi> poiST = new RedBlackBST<>();
  
  private SeparateChainingHashST<Tag, ArrayList<Object>> tagsST = new SeparateChainingHashST<>();
 
  public void setUserST(RedBlackBST<String, User> userST) {
    this.userST = userST;
  }

  public void setNodesST(RedBlackBST<Integer, Node> nodesST) {
    this.nodesST = nodesST;
  }

  public void setWaysST(RedBlackBST<Integer, Way> waysST) {
    this.waysST = waysST;
  }

  public void setPoiST(RedBlackBST<Integer, Poi> poiST) {
    this.poiST = poiST;
  }

  public SeparateChainingHashST<Tag, ArrayList<Object>> getTagsST() {
    return tagsST;
  }

  public void setTagsST(SeparateChainingHashST<Tag, ArrayList<Object>> tagsST) {
    this.tagsST = tagsST;
  }

  /**
   * Adds user to DataBase
   * @param u user to add
   * @author Vitor Hugo
   */
  public void addUser(User u) {
    this.userST.put(u.getIdNumber(), u);
    // Associate this db to user
    u.setDb(this);
  }
    
  /**
   * removes a user from DataBase and arquives it
   * @param u user to remove
   * @return removed user
   * @author Vitor Hugo
   */
  public User removeUser(User u) {
    if (u == null) throw new IllegalArgumentException("argument to removeUser() is null");
    if (!this.userST.contains(u.getIdNumber())) {
      System.out.println("User not present in DataBase");
      return null;
    }
    this.userST.delete(u.getIdNumber());
    Arquive.User(u);
    return u;
  }

  /**
   * edits a user from Database
   * @param o old user to edit
   * @param n new user to replace the old one
   * @author Vitor Hugo
   */
  public void editUser(User o, User n) {
    if (o == null) throw new IllegalArgumentException("argument 'o' to editUser() is null");
    if (n == null) throw new IllegalArgumentException("argument 'n' to editUser() is null");
    if (removeUser(o) != null) addUser(n);
  }

  /**
   * searches for a user from DataBase
   * @param u user to search
   * @return User if able to present || null is not
   * @author Vitor Hugo
   */
  public User searchUser(String id) {
    if (id == null) throw new IllegalArgumentException("argument to searchUser() is null");
    User u = this.userST.get(id);
    Arquive.searchedUser(u);
    return u;
  }

  /**
   * checks if database contains user
   * @param u user to search for
   * @return true if found || false if not found
   */
  public boolean contains(User u) {
    return this.userST.contains(u.getIdNumber());
  }

  /**
   * returns all keys of usersST
   * @return an iterable containing all user ids
   */
  public Iterable<String> usersKeys() {
    return this.userST.keys();
  }

  /**
   * return the number of users the database has
   * @return
   */
  public int numberOfUsers() {
    return this.userST.size();
  }

  /**
   * lists all users from DataBase
   * @author Vitor Hugo
   */
  public void listUsers() {
    if (!this.userST.isEmpty()) {
      for (String s : this.userST.keys()) {
        System.out.println("User: " + s);
      }
    }
  }

  /**
   * adds a node to database
   * @param n node to add
   * @author Vitor Hugo
   */
  public void addNode(Node n) {
    if (n == null) throw new IllegalArgumentException("argument to addNode() is null");
    if (!containsNode(n))
      this.nodesST.put(n.getNodeId(), n);
    else 
      System.out.println("node already in database");
  }

  /**
   * removes a node from database
   * @param n node to remove
   * @return removed node || null if not found
   * @author Vitor Hugo
   * @throws NodeNotPresentException if {@code n}  not found in database
   */
  public Node removeNode(Node n) throws NodeNotPresentException {
    if (n == null) throw new IllegalArgumentException("argument to removeNode() is null");
    if (this.containsNode(n)) {
      this.nodesST.delete(n.getNodeId());
      Arquive.Node(n);
      return n;
    }
    else throw new NodeNotPresentException();
  }

  /**
   * edits a node from database
   * @param o old node to remove
   * @param n new node to add
   * @author Vitor Hugo
   * @throws NodeNotPresentException
   */
  public void editNode(Node o, Node n) throws NodeNotPresentException {
    if (o == null) throw new IllegalArgumentException("argument 'o' for editNode() is null");
    if (n == null) throw new IllegalArgumentException("argument 'n' for editNode() is null");
    if (removeNode(o) != null) addNode(n);
  }

  /**
   * searches for a node in database
   * @param id id of node to search for
   * @return node to search for || null if not found
   * @author Vitor Hugo
   */
  public Node searchNode(Integer id) {
    if (id == null) throw new IllegalArgumentException("argument for searchNode() is null");
    return this.nodesST.get(id);
  }

  /**
   * checks if node exists in database
   * @param n node
   * @return true if found || false if not
   */
  public boolean containsNode(Node n) {
    return this.nodesST.contains(n.getNodeId());
  }

  /**
   * returns all keys of nodes symbol table
   * @return an iterable of nodesST keys
   * @author Vitor Hugo
   */
  public Iterable<Integer> nodesKeys() {
    return this.nodesST.keys();
  }

  /**
   * return number of nodes in database
   * @return nodesST size
   * @author Vitor Hugo
   */
  public int numberOfNodes() {
    return this.nodesST.size();
  }

  /**
   * lists all nodes from database
   * @author Vitor Hugo
   */
  public void listNodes() {
    if (!this.nodesST.isEmpty()) {
      System.out.println("nodeCount = " + this.nodesST.size());
      for (var i : this.nodesST.keys()) {
        System.out.println("Node: " + i);
      }
    }
  }

  /**
   * adds a way to database
   * @param w way to add
   * @author Vitor Hugo
   */
  public void addWay(Way w) {
    if (w == null) throw new IllegalArgumentException("argument to addWay() is null");
    this.waysST.put(w.getWayId(), w);
  }

  /**
   * removes a way from database
   * @param w way to remove
   * @return removed way || null if not found
   * @author Vitor Hugo
   */
  public Way removeWay(Way w) {
    if (w == null) throw new IllegalArgumentException("argument to removeWay() is null");
    if (this.waysST.contains(w.getWayId())) {
      this.waysST.delete(w.getWayId());
      Arquive.Way(w);
      return w;
    }
    else {
      System.out.println("Way not present in database");
      return null;
    }
  }

  /**
   * edits a way from database
   * @param o old way
   * @param n new way
   * @author Vitor Hugo
   */
  public void editWay(Way o, Way n) {
    if (o == null) throw new IllegalArgumentException("argument 'o' to editWay() is null");
    if (n == null) throw new IllegalArgumentException("argument 'n' to editWay() is null");
    if (removeWay(o) != null) addWay(n);
  }

  /**
   * searches for a specified way in database
   * @param id id of way to look for
   * @return way if found || null if not found
   * @author Vitor Hugo
   */
  public Way searchWay (Integer id) {
    if (id == null) throw new IllegalArgumentException("argument to searchWay() is null");
    return this.waysST.get(id);
  }

  /**
   * checks if database contains way
   * @param w way
   * @return true if found || false if not found
   */
  public boolean containsWay(Way w) {
    return this.waysST.contains(w.getWayId());
  }

  /**
   * returns all keys of ways symbol table
   * @return an iterable of waysST keys
   */
  public Iterable<Integer> waysKeys() {
    return this.waysST.keys();
  }

  /**
   * number of ways in database
   * @return waysST size
   */
  public int numberOfWays() {
    return this.waysST.size();
  }

  /**
   * lists all ways from database
   * @author Vitor Hugo
   */
  public void listWays() {
    if (!this.waysST.isEmpty()) {
      System.out.println("Way count: " + this.waysST.size());
      for (var i : this.waysST.keys()) {
        Way w = this.waysST.get(i);
        System.out.println(w);
      }
    }
  }

  /**
   * adds a poi to database
   * @param p poi
   * @author Vitor Hugo
   */
  public void addPoi (Poi p) {
    if (p == null) throw new IllegalArgumentException("argument to addPoi() is null");
    this.nodesST.put(p.getNodeId(), p);
    this.poiST.put(p.getNodeId(), p);
  }

  /**
   * removes a poi from database
   * @param p poi to remove
   * @return poi removed if found || null if not found
   * @author Vitor Hugo
   */
  public Poi removePoi (Poi p) {
    if (p == null) throw new IllegalArgumentException("argument to addPoi() is null");
    if (this.nodesST.contains(p.getNodeId()) && this.poiST.contains(p.getNodeId())) {
      this.nodesST.delete(p.getNodeId());
      this.poiST.delete(p.getNodeId());
      Arquive.Poi(p);
      return p;
    }
    else {
      System.out.println("poi not present in database");
      return null;
    }
  }

  /**
   * edits a poi from database
   * @param o old poi
   * @param n new poi
   * @author Vitor Hugo
   */
  public void editPoi (Poi o, Poi n) {
    if (o == null) throw new IllegalArgumentException("argument 'o' to addPoi() is null");
    if (n == null) throw new IllegalArgumentException("argument 'n' to addPoi() is null");
    if (removePoi(o) != null) addPoi(n);
  }

  /**
   * searches for a poi in database
   * @param p poi to search for
   * @return poi if found || null if not found
   * @author Vitor Hugo
   */
  public Poi searchPoi (Integer id) {
    if (id == null) throw new IllegalArgumentException("argument to searchPoi() is null");
    return this.poiST.get(id);
  }
  
  /**
   * checks if poi exists in database
   * @param p poi
   * @return true if found || false if not found
   */
  public boolean containsPoi(Poi p) {
    return this.poiST.contains(p.getNodeId());
  }

  /**
   * returns all keys of pois symbol table
   * @return an iterable of poisST keys
   */
  public Iterable<Integer> poisKeys() {
    return this.poiST.keys();
  }

  /**
   * number of pois in database
   * @return poisST size
   */
  public int numberOfPois() {
    return this.poiST.size();
  }

  /**
   * lists all poi from database
   * @author Vitor Hugo
   */
  public void listPoi () {
    if (!this.poiST.isEmpty()) {
      for (var integer : this.poiST.keys()) {
        System.out.println("Poi: " + integer);
      }
    }
  }

  /**
   * adds a tag to database
   * A key-value pair ST is used (SepareteChainingST<Tag, ArrayList<Object>>)
   * key -> tag 't'
   * value -> all nodes and ways where tag 't' is present
   * @param t tag
   * @author Vitor Hugo
   */
  private void addTag (Tag t) { 
    if (t == null) throw new IllegalArgumentException("argument to addTag() is null");
    // create arraylist to insert as a value
    ArrayList<Object> al = new ArrayList<>();

    if (!this.poiST.isEmpty()) {
      // iterate over nodes
      for (var i : this.poiST.keys()) {
        Poi p = this.poiST.get(i);
        if (p.containsTag(t)) {
          al.add(p);
        }
      }
    }
    if (!this.waysST.isEmpty()) {
      // iterate over ways
      for (var i : this.waysST.keys()) {
        Way w = this.waysST.get(i);
        if (w.containsTag(t)) {
          al.add(w);
        }
      }
    }
    this.tagsST.put(t, al);
  }

  /**
   * adds a tag to specified node
   * @param n node
   * @param t tag
   * @param value value of tag
   * @author Vitor Hugo
   */
  public void addPoiTag (Poi p, Tag t, String value) {
    if (t == null) throw new IllegalArgumentException("argument to addNodeTag() is null");
    if (this.poiST.contains(p.getNodeId())) {
      this.poiST.get(p.getNodeId()).addTag(t, value);
      addTag(t);
    }
    else {
      System.out.println("Node is not present in database");
    }
  }

  /**
   * adds a tag to to specified way
   * @param w way
   * @param t tag
   * @param value value of tag
   * @author Vitor Hugo
   */
  public void addWayTag (Way w, Tag t, String value) {
    if (this.waysST.contains(w.getWayId())) {
      this.waysST.get(w.getWayId()).addTag(t, value);
      addTag(t);
    }
    else {
      System.out.println("Way is not present in database");
    }
  }

  /**
   * removes an object instance containing the specified tag
   * @param t tag to remove
   * @return object containing the specified tag
   * @author Vitor Hugo
   */
  private void removeTagsObject (Object o, Tag t) {
    if (t == null) throw new IllegalArgumentException("argument to removeTag() is null");
    if (this.tagsST.contains(t)) {
      // remove instance of object from arraylist
      tagsST.get(t).remove(o);
      if (tagsST.get(t).isEmpty()) {
        // remove tag from tagsST
        this.tagsST.delete(t);
        Arquive.Tag(t);
      }
    }
    else {
      System.out.println("tag not present in database");
    }
  }

  /**
   * removes specified tag from node
   * @param n node
   * @param t tag
   * @return tag removed if found || null if not found
   * @author Vitor Hugo
   */
  public Tag removePoiTag (Node n, Tag t) {
    if (this.poiST.contains(n.getNodeId())){
      this.poiST.get(n.getNodeId()).removeTag(t);
      removeTagsObject(n, t);
      Arquive.Tag(n, t);
      return t;
    }
    return null;
  }

  /**
   * removes specified tag from way
   * @param w way
   * @param t tag
   * @return tag removed if found || null if not found
   * @author Vitor Hugo
   */
  public Tag removeWayTag (Way w, Tag t) {
    if (this.waysST.contains(w.getWayId())){
      this.waysST.get(w.getWayId()).removeTag(t);
      removeTagsObject(w, t);
      Arquive.Tag(w, t);
      return t;
    }
    return null;
  }

  /**
   * lists all tags used in database
   * @author Vitor Hugo
   */
  public void listTags() {
    if (!this.tagsST.isEmpty()) {
      for (Tag t : this.tagsST.keys()) {
        System.out.println("Tag: " + t);
      }
    }
  }

  /**
   * lists all nodes and ways that specified tag is used
   * @param t tag to look for
   * @author Vitor Hugo
   */
  public void listTagValues (Tag t) {
    if (t == null) throw new IllegalArgumentException("argument to lisTagValues() is null");
    if (this.tagsST.contains(t)) {
      for (Object object : this.tagsST.get(t)) {
        System.out.println(object);
      }
    }
  }

  /**
   * lists all nodes that specified tag is used
   * @param t tag to look for
   * @author Vitor Hugo
   */
  public void listTagValuesForNodes (Tag t) {
    if (t == null) throw new IllegalArgumentException("argument to lisTagValues() is null");
    if (this.tagsST.contains(t)) {
      for (Object object : this.tagsST.get(t)) {
        if (object instanceof Node) {
          System.out.println(object);
        }
      }
    }
  }

  /**
   * lists all ways that specified tag is used
   * @param t tag to look for
   * @author Vitor Hugo
   */
  public void listTagValuesForWays (Tag t) {
    if (t == null) throw new IllegalArgumentException("argument to lisTagValues() is null");
    if (this.tagsST.contains(t)) {
      for (Object object : this.tagsST.get(t)) {
        if (object instanceof Way) {
          System.out.println(object);
        }
      }
    }
  }

  /**
   * searches for point of interest visited by user between a certain period
   * @param u user
   * @param start initial timestamp
   * @param end last timestamp
   * @return arraylist containing all pois between start and end || null if not visited any poi
   * @author Vitor Hugo
   */
  public ArrayList<Poi> getUserPoisVisited (User user, Long start, Long end) {
    if (user == null) throw new IllegalArgumentException("argument 'user' to getUserPoisVisited() is null");
    if (start == null) throw new IllegalArgumentException("argument 'start' to getUserPoisVisited() is null");
    if (end == null) throw new IllegalArgumentException("argument 'end' to getUserPoisVisited() is null");
    
    if (this.contains(user)) {
      User u = this.searchUser(user.getIdNumber());
      ArrayList<Poi> pois = new ArrayList<>();
      for (Long l : u.getVisitedPoi().keys(start, end)) {
        pois.add(u.getVisitedPoi().get(l));
      }
      return pois;
    }
    else {
      System.out.println("user didn't visit poi between this time period");
      return null;
    }
  }

  /**
   * searches for point of interest not visited by user between a certain period
   * @param u user
   * @param start initial timestamp
   * @param end last timestamp
   * @return arraylist containing all pois between start and end || null if not visited any poi
   * @author Vitor Hugo
   */
  public ArrayList<Poi> getUserPoisNotVisited (User user, Long start, Long end) {
    if (user == null) throw new IllegalArgumentException("argument 'user' to getUserPoisVisited() is null");
    if (start == null) throw new IllegalArgumentException("argument 'start' to getUserPoisVisited() is null");
    if (end == null) throw new IllegalArgumentException("argument 'end' to getUserPoisVisited() is null");

    ArrayList<Poi> poisNotVisited = new ArrayList<>();
    if (this.userST.contains(user.getIdNumber())) {
      User u = this.userST.get(user.getIdNumber());
      ArrayList<Poi> poisVisited = getUserPoisVisited(u, start, end);
      for (Integer poiId : this.poiST.keys()) {
        Poi p = this.poiST.get(poiId);
        if (!poisVisited.contains(p)) {
          poisNotVisited.add(p);
        }
      }
    }
    return poisNotVisited; 
  }

  /**
   * searches for all users thats visited specified poi between a certain time
   * @param p poi
   * @param start initial timestamp
   * @param end last timestamp
   * @return arraylist containing all users that visited poi between start and end || null if not any
   * @author Vitor Hugo
   */
  public ArrayList<User> getUsersThatVisitedPoi (Poi poi, Long start, Long end) {
    if (poi == null) throw new IllegalArgumentException("argument 'user' to getUserPoisVisited() is null");
    if (start == null) throw new IllegalArgumentException("argument 'start' to getUserPoisVisited() is null");
    if (end == null) throw new IllegalArgumentException("argument 'end' to getUserPoisVisited() is null");

    if (this.poiST.contains(poi.getNodeId())) {
      Poi p = this.poiST.get(poi.getNodeId());
      ArrayList<User> users = new ArrayList<>();
      for (Long l : p.getVisitorsEntrance().keys(start, end)) {
        for (User u : p.getVisitorsEntrance().get(l)) {
          users.add(u);
        }
      }
      return users;
    }
    else {
      System.out.println("poi doesn't have visitors between this time period");
      return null;
    }
  }
  
  /**
   * searches for pois that weren't visited by any users between a certain time
   * @param start initial timestamp
   * @param end last timestamp
   * @return arraylist containing all poi that weren't visited between specified time || null if all were visited
   */
  public ArrayList<Poi> getPoiNotVisited (Long start, Long end) {
    if (start == null) throw new IllegalArgumentException("argument 'start' to getUserPoisVisited() is null");
    if (end == null) throw new IllegalArgumentException("argument 'end' to getUserPoisVisited() is null");

    if (!this.poiST.isEmpty()) {
      ArrayList<Poi> pois = new ArrayList<>();
      for (Integer poiId : this.poiST.keys()) {
        Poi p = this.poiST.get(poiId);
        for (Long timestamp : p.getVisitorsEntrance().keys(start, end)) {
          if (!p.getVisitorsEntrance().get(timestamp).isEmpty()) {
            pois.add(p);
          }
        }
      }
      return pois;
    }
    else {
      System.out.println("no pois under specification found");
      return null;
    }
    
  }

  /**
   * takes a snapshot of the current state of the aplication
   * @author Vitor Hugo
   */
  public void now() {
    long timestamp = Instant.now().getEpochSecond();
    // calculate random number of seconds (15min max)
    Long timespent = StdRandom.uniform(((15*60l)));
    Long start = timestamp - timespent;
    Long end = timestamp + timespent;
    if (!this.poiST.isEmpty()) {
      for (Integer poiId : this.poiST.keys()) {
        Poi p = this.poiST.get(poiId);
        System.out.println(p);
        System.out.println(timespent);
        for (Long ts : p.getVisitorsEntrance().keys(start, end)) {
          System.out.println(p.getVisitorsEntrance().get(ts));
        }
      }
    }
  }
  
  /**
   * searches for semaphores nearby coordinates
   * @param coordinates coordinates
   * @return semaphores found
   */
  public ArrayList<Poi> closestSemaphore (Point coordinates) {
    if (coordinates == null) throw new IllegalArgumentException("arguemnt to colsestSemaphore() is null");

    // random radius to search for sempahores (50-150 m)
    double radius = StdRandom.uniform(50, 150);

    ArrayList<Poi> semaphores = new ArrayList<>();
    for (var v : this.poiST.keys()) {
      Poi p = this.poiST.get(v);
      if (p.getCoordinates().dist(coordinates) <= radius) {
        if (p.containsTag(Tag.SEMAPHORE)) {
          semaphores.add(p);
        }
      }
    }
    return semaphores;
  }

}