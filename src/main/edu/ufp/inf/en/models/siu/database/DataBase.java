package main.edu.ufp.inf.en.models.siu.database;


import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdRandom;
import main.edu.ufp.inf.en.models.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.models.siu.IO.Arquive;
import main.edu.ufp.inf.en.models.siu.database.node.Node;
import main.edu.ufp.inf.en.models.siu.database.poi.Poi;
import main.edu.ufp.inf.en.models.siu.database.poi.PoiAlreadyExistsException;
import main.edu.ufp.inf.en.models.siu.database.poi.PoiNotFoundException;
import main.edu.ufp.inf.en.models.siu.database.tag.Tag;
import main.edu.ufp.inf.en.models.siu.database.way.Way;
import main.edu.ufp.inf.en.models.siu.database.way.WayAlreadyExistsException;
import main.edu.ufp.inf.en.models.siu.database.way.WayNotFoundException;
import main.edu.ufp.inf.en.models.siu.user.User;

import main.edu.ufp.inf.en.models.siu.user.UserAlreadyExistsException;
import main.edu.ufp.inf.en.models.siu.user.UserNotFoundException;
import main.edu.ufp.inf.en.models.siu.database.node.NodeAlreadyExistsException;
import main.edu.ufp.inf.en.models.siu.database.node.NodeNotFoundException;

/**
 * @author Vitor Hugo
 */
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
  public void addUser(User u) throws UserAlreadyExistsException{
    if (!this.containsUser(u.getIdNumber())) {
      this.userST.put(u.getIdNumber(), u);
      // Associate this db to user
      u.setDb(this);
    }
    else throw new UserAlreadyExistsException("User already exists");
  }
    
  /**
   * removes a user from DataBase and arquives it
   * @param u user to remove
   * @return removed user
   * @author Vitor Hugo
   * @throws UserNotFoundException
   */
  public User removeUser(User u) throws UserNotFoundException {
    if (u == null) throw new IllegalArgumentException("argument to removeUser() is null");
    if (this.containsUser(u.getIdNumber())) {
      this.userST.delete(u.getIdNumber());
      Arquive.User(u);
      return u;
    }
    else throw new UserNotFoundException("User not found");
  }

  /**
   * edits a user from database
   * @param id id of user to be edited
   * @param name new name
   * @param address new address
   * @param birth new birth
   * @throws UserNotFoundException if {@code userID} is not present in database
   */
  public void editUser(String userID, String name, String address, LocalDate birth) throws UserNotFoundException {
    if (userID == null) throw new IllegalArgumentException("argument 'o' to editUser() is null");
    if (name == null) throw new IllegalArgumentException("argument 'name' to editUser() is null");
    if (address == null) throw new IllegalArgumentException("argument 'address' to editUser() is null");
    if (birth == null) throw new IllegalArgumentException("argument 'birth' to editUser() is null");
    User u = this.searchUser(userID);
    u.setName(name);
    u.setAddress(address);
    u.setBirth(birth);
  }

  /**
   * searches for a user from DataBase
   * @param u user to search
   * @return User if able to present || null is not
   * @author Vitor Hugo
   */
  public User searchUser(String id) throws UserNotFoundException{
    if (id == null) throw new IllegalArgumentException("argument to searchUser() is null");
    if (this.containsUser(id)) {
      User u = this.userST.get(id);
      Arquive.searchedUser(u);
      return u;
    }
    else {
      throw new UserNotFoundException("User not found");
    }
  }

  /**
   * checks if database contains user
   * @param u user to search for
   * @return true if found || false if not found
   */
  public boolean containsUser(String id) {
    return this.userST.contains(id);
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
  public void addNode(Node n) throws NodeAlreadyExistsException {
    if (n == null) throw new IllegalArgumentException("argument to addNode() is null");
    if (!containsNode(n.getNodeId()))
      this.nodesST.put(n.getNodeId(), n);
    else throw new NodeAlreadyExistsException("Node already exists");
  }

  /**
   * removes a node from database
   * @param n node to remove
   * @return removed node || null if not found
   * @author Vitor Hugo
   * @throws NodeNotFoundException if {@code n}  not found in database
   */
  public Node removeNode(Node n) throws NodeNotFoundException {
    if (n == null) throw new IllegalArgumentException("argument to removeNode() is null");
    if (this.containsNode(n.getNodeId())) {
      this.nodesST.delete(n.getNodeId());
      Arquive.Node(n);
      System.gc();
      return n;
    }
    else throw new NodeNotFoundException();
  }

  /**
   * edits a node from database
   * @param o old node to remove
   * @param n new node to add
   * @author Vitor Hugo
   * @throws NodeNotFoundException
   */
  public void editNode(Integer nodeID, Point coordinates) throws NodeNotFoundException {
    if (nodeID == null) throw new IllegalArgumentException("argument 'nodeID' for editNode() is null");
    if (coordinates == null) throw new IllegalArgumentException("argument 'coordinates' for editNode() is null");
    Node n = this.searchNode(nodeID);
    n.setCoordinates(coordinates);
  }

  /**
   * searches for a node in database
   * @param nodeID node id
   * @return wanted node
   * @throws NodeNotFoundException if {@code nodeID} not found in database
   */
  public Node searchNode(Integer nodeID) throws NodeNotFoundException {
    if (nodeID == null) throw new IllegalArgumentException("argument for searchNode() is null");
    if (this.containsNode(nodeID)) {
      return this.nodesST.get(nodeID);
    }
    else throw new NodeNotFoundException();
  }

  /**
   * checks if node exists in database
   * @param nodeID node id
   * @return true if found || false if not found
   */
  public boolean containsNode(Integer nodeID) {
    return this.nodesST.contains(nodeID);
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
   * @throws WayAlreadyExistsException
   */
  public void addWay(Way w) throws WayAlreadyExistsException {
    if (w == null) throw new IllegalArgumentException("argument to addWay() is null");
    if (!this.containsWay(w.getWayId())) {
      this.waysST.put(w.getWayId(), w);
    }
    else throw new WayAlreadyExistsException();
  }

  /**
   * removes a way from database
   * @param w way to remove
   * @return removed way || null if not found
   * @throws WayNotFoundException
   */
  public Way removeWay(Way w) throws WayNotFoundException {
    if (w == null) throw new IllegalArgumentException("argument to removeWay() is null");
    if (this.containsWay(w.getWayId())) {
      this.waysST.delete(w.getWayId());
      Arquive.Way(w);
      return w;
    }
    else throw new WayNotFoundException("way not found");
  }

  /**
   * edits a way from database
   * @param o old way
   * @param n new way
   * @throws WayNotFoundException
   */
  public void editWay(Integer wayID, Node origin, Node destination, double weight) throws WayNotFoundException {
    if (wayID == null) throw new IllegalArgumentException("argument 'wayID' to editWay() is null");
    if (origin == null) throw new IllegalArgumentException("argument 'origin' to editWay() is null");
    if (destination == null) throw new IllegalArgumentException("argument 'destination' to editWay() is null");
    Way w = this.searchWay(wayID);
    try {
      this.removeWay(w);
      this.addWay(new Way(wayID, origin.getNodeId(), destination.getNodeId(), weight));
    } catch (WayAlreadyExistsException e) {
      e.printStackTrace();
    }
  }

  /**
   * searches for a specified way in database
   * @param id id of way to look for
   * @return way if found || null if not found
   * @author Vitor Hugo
   * @throws WayNotFoundException
   */
  public Way searchWay (Integer wayID) throws WayNotFoundException {
    if (wayID == null) throw new IllegalArgumentException("argument to searchWay() is null");
    if (this.containsWay(wayID)) {
      return this.waysST.get(wayID);
    }
    else throw new WayNotFoundException("way not found");
  }

  /**
   * checks if database contains way
   * @param w way
   * @return true if found || false if not found
   */
  public boolean containsWay(Integer wayID) {
    return this.waysST.contains(wayID);
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
   * @throws PoiAlreadyExistsException
   */
  public void addPoi (Poi p) throws PoiAlreadyExistsException {
    if (p == null) throw new IllegalArgumentException("argument to addPoi() is null");
    if (!this.containsPoi(p.getNodeId())) {
      this.nodesST.put(p.getNodeId(), p);
      this.poiST.put(p.getNodeId(), p);
    }
    else throw new PoiAlreadyExistsException("Poi already exists");
  }

  /**
   * removes a poi from database
   * @param p poi to remove
   * @return poi removed if found || null if not found
   * @author Vitor Hugo
   * @throws PoiNotFoundException
   */
  public Poi removePoi (Poi p) throws PoiNotFoundException {
    if (p == null) throw new IllegalArgumentException("argument to addPoi() is null");
    if (this.containsPoi(p.getNodeId())) {
      this.poiST.delete(p.getNodeId());
      Arquive.Poi(p);
      return p;
    }
    else throw new PoiNotFoundException("Poi not found");
  }

  /**
   * edits a poi from database
   * @param o old poi
   * @param n new poi
   * @author Vitor Hugo
   * @throws PoiNotFoundException
   */
  public void editPoi (Integer poiID, Point coordinates) throws PoiNotFoundException {
    if (poiID == null) throw new IllegalArgumentException("argument 'poiID' to addPoi() is null");
    if (coordinates == null) throw new IllegalArgumentException("argument 'coordinates' to addPoi() is null");
    Poi p = this.searchPoi(poiID);
    p.setCoordinates(coordinates);
  }

  /**
   * searches for a poi in database
   * @param p poi to search for
   * @return poi if found || null if not found
   * @throws PoiNotFoundException
   */
  public Poi searchPoi (Integer poiID) throws PoiNotFoundException {
    if (poiID == null) throw new IllegalArgumentException("argument to searchPoi() is null");
    if (this.containsPoi(poiID)) {
      return this.poiST.get(poiID);
    }
    else throw new PoiNotFoundException("Poi not found");
  }
  
  /**
   * checks if poi exists in database
   * @param p poi
   * @return true if found || false if not found
   */
  public boolean containsPoi(Integer poiID) {
    return this.poiST.contains(poiID);
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
  public List<Poi> getUserPoisVisited (User user, Long start, Long end) throws UserNotFoundException {
    if (user == null) throw new IllegalArgumentException("argument 'user' to getUserPoisVisited() is null");
    if (start == null) throw new IllegalArgumentException("argument 'start' to getUserPoisVisited() is null");
    if (end == null) throw new IllegalArgumentException("argument 'end' to getUserPoisVisited() is null");
    
    User u = this.searchUser(user.getIdNumber());
    List<Poi> pois = new ArrayList<>();
    for (Long l : u.getVisitedPoi().keys(start, end)) {
      pois.add(u.getVisitedPoi().get(l));
    }
    return pois;
  }

  /**
   * searches for point of interest not visited by user between a certain period
   * @param u user
   * @param start initial timestamp
   * @param end last timestamp
   * @return arraylist containing all pois between start and end || null if not visited any poi
   * @author Vitor Hugo
   */
  public List<Poi> getUserPoisNotVisited (User user, Long start, Long end) throws UserNotFoundException{
    if (user == null) throw new IllegalArgumentException("argument 'user' to getUserPoisVisited() is null");
    if (start == null) throw new IllegalArgumentException("argument 'start' to getUserPoisVisited() is null");
    if (end == null) throw new IllegalArgumentException("argument 'end' to getUserPoisVisited() is null");

    List<Poi> poisNotVisited = new ArrayList<>();
    
    User u = this.searchUser(user.getIdNumber());
    List<Poi> poisVisited = getUserPoisVisited(u, start, end);
    for (Integer poiId : this.poiST.keys()) {
      Poi p = this.poiST.get(poiId);
      if (!poisVisited.contains(p)) {
        poisNotVisited.add(p);
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
   */ 
  public List<User> getUsersThatVisitedPoi (Poi poi, Long start, Long end) {
    if (poi == null) throw new IllegalArgumentException("argument 'user' to getUserPoisVisited() is null");
    if (start == null) throw new IllegalArgumentException("argument 'start' to getUserPoisVisited() is null");
    if (end == null) throw new IllegalArgumentException("argument 'end' to getUserPoisVisited() is null");
    if (this.containsPoi(poi.getNodeId())) {
      List<User> users = new ArrayList<>();
      try {
        Poi p = this.searchPoi(poi.getNodeId());
        for (Long l : p.getVisitorsEntrance().keys(start, end)) {
          for (String userID : p.getVisitorsEntrance().get(l)) {
            users.add(this.searchUser(userID));
          }
        }
        return users;
      } catch (PoiNotFoundException e) {
        e.printStackTrace();
      } catch (UserNotFoundException e) {
        e.printStackTrace();
      }

    }
      return null;
    }
    
  
  /**
   * searches for pois that weren't visited by any users between a certain time
   * @param start initial timestamp
   * @param end last timestamp
   * @return arraylist containing all poi that weren't visited between specified time || null if all were visited
   */
  public List<Poi> getPoiNotVisited (Long start, Long end) {
    if (start == null) throw new IllegalArgumentException("argument 'start' to getUserPoisVisited() is null");
    if (end == null) throw new IllegalArgumentException("argument 'end' to getUserPoisVisited() is null");

    if (!this.poiST.isEmpty()) {
      List<Poi> pois = new ArrayList<>();
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
      try {
        for (Integer poiID : this.poisKeys()) {
          Poi p = this.searchPoi(poiID);
          System.out.println(p);
          System.out.println(timespent);
          for (Long ts : p.getVisitorsEntrance().keys(start, end)) {
            System.out.println(p.getVisitorsEntrance().get(ts));
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  
  /**
   * searches for semaphores nearby coordinates
   * @param coordinates coordinates
   * @return semaphores found
   */
  public List<Poi> closestSemaphore (Point coordinates) {
    if (coordinates == null) throw new IllegalArgumentException("arguemnt to closestSemaphore() is null");

    // random radius to search for sempahores (50-150 m)
    double radius = StdRandom.uniform(50, 300);

    List<Poi> semaphores = new ArrayList<>();
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

  /**
   * searches for poi containing tag nearby coordinates
   * @param tag tag to look for
   * @param coordinates coordinates to search around
   * @return list of poi containing {@code tag} nearby {@code coordinates}
   * @throws PoiNotFoundException if no such poi is in database
   */
  public List<Poi> closestPois (Tag tag, Point coordinates) throws PoiNotFoundException {
    if (tag == null) throw new IllegalArgumentException("argument 'tag' to closestPois() is null");
    if (coordinates == null) throw new IllegalArgumentException("argument 'coordinates' to closestPois() is null");

    double radius = StdRandom.uniform(50, 300);

    List<Poi> pois = new ArrayList<>();
    for (var poiID : this.poisKeys()) {
      Poi p = searchPoi(poiID);
      if (p.getCoordinates().dist(coordinates) <= radius) {
        if (p.containsTag(tag)) {
          pois.add(p);
        }
      }
    }
    return pois;
  }

}