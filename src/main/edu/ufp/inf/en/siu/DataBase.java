package main.edu.ufp.inf.en.siu;


import java.time.LocalDateTime;
import java.util.ArrayList;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SeparateChainingHashST;


public class DataBase {
  
  private RedBlackBST<String, User> userST = new RedBlackBST<>();
  
  private RedBlackBST<Integer, Node> nodesST = new RedBlackBST<>();
  
  private RedBlackBST<Integer, Way> waysST = new RedBlackBST<>();
  
  private RedBlackBST<Integer, Poi> poiST = new RedBlackBST<>();
  
  private SeparateChainingHashST<Tag, ArrayList<Object>> tagsST = new SeparateChainingHashST<>();
 
  public RedBlackBST<String, User> getUserST() {
    return userST;
  }

  public void setUserST(RedBlackBST<String, User> userST) {
    this.userST = userST;
  }

  public RedBlackBST<Integer, Node> getNodesST() {
    return nodesST;
  }

  public void setNodesST(RedBlackBST<Integer, Node> nodesST) {
    this.nodesST = nodesST;
  }

  public RedBlackBST<Integer, Way> getWaysST() {
    return waysST;
  }

  public void setWaysST(RedBlackBST<Integer, Way> waysST) {
    this.waysST = waysST;
  }

  public RedBlackBST<Integer, Poi> getPoiST() {
    return poiST;
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
    return this.userST.get(id);
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
    this.nodesST.put(n.getNodeId(), n);
  }

  /**
   * removes a node from database
   * @param n node to remove
   * @return removed node || null if not found
   * @author Vitor Hugo
   */
  public Node removeNode(Node n) {
    if (n == null) throw new IllegalArgumentException("argument to removeNode() is null");
    if (this.nodesST.contains(n.getNodeId())) {
      this.nodesST.delete(n.getNodeId());
      Arquive.Node(n);
      return n;
    }
    else {
      System.out.println("Node not present in database");
      return null;
    }
  }

  /**
   * edits a node from database
   * @param o old node to remove
   * @param n new node to add
   * @author Vitor Hugo
   */
  public void editNode(Node o, Node n) {
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
   * @param w way to search for
   * @return way if found || null if not found
   * @author Vitor Hugo
   */
  public Way searchWay (Way w) {
    if (w == null) throw new IllegalArgumentException("argument to searchWay() is null");
    return this.waysST.get(w.getWayId());
  }

  /**
   * lists all ways from database
   * @author Vitor Hugo
   */
  public void listWays() {
    if (!this.waysST.isEmpty()) {
      System.out.println("Way count: " + this.waysST.size());
      for (var i : this.waysST.keys()) {
        System.out.println("Way: " + i);
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

    if (!this.nodesST.isEmpty()) {
      // iterate over nodes
      for (var i : this.nodesST.keys()) {
        Node n = this.nodesST.get(i);
        if (n.containsTag(t)) {
          al.add(n);
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
  public void addNodeTag (Node n, Tag t, String value) {
    if (t == null) throw new IllegalArgumentException("argument to addNodeTag() is null");
    if (this.nodesST.contains(n.getNodeId())) {
      this.nodesST.get(n.getNodeId()).addTag(t, value);
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
  public Tag removeNodeTag (Node n, Tag t) {
    if (this.nodesST.contains(n.getNodeId())){
      this.nodesST.get(n.getNodeId()).removeTag(t);
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
    if (this.nodesST.contains(w.getWayId())){
      this.nodesST.get(w.getWayId()).removeTag(t);
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
  public Poi searchPoi (Poi p) {
    if (p == null) throw new IllegalArgumentException("argumento to searchPoi() is null");
    return this.poiST.get(p.getNodeId());
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
   * searches for point of interest visited by user between a certain period
   * @param u user
   * @param start initial timestamp
   * @param end last timestamp
   * @return arraylist containing all pois between start and end || null if not visited any poi
   * @author Vitor Hugo
   */
  public ArrayList<Poi> getUserPoisVisited (User u, TimePeriod tp) {
    if (u == null) throw new IllegalArgumentException("argument 'u' to getUserPoisVisited() is null");
    if (tp == null) throw new IllegalArgumentException("argument 'tp' to getUserPoisVisited() is null");
    if (this.userST.contains(u.getIdNumber())) {
      ArrayList<Poi> pois = new ArrayList<>();
      for (var poi : u.getVisitedPoi().keys()) {
        ArrayList<TimePeriod> times = u.getVisitedPoi().get(poi);
        for (var timePeriod : times) {
          if ((timePeriod.getStart().isAfter(tp.getStart()) || timePeriod.getStart().isEqual(tp.getStart()))
              && (timePeriod.getEnd().isBefore(tp.getEnd()) || timePeriod.getEnd().isEqual(tp.getEnd()))) {
            pois.add(this.poiST.get(poi));
          }
        }
      }
      return pois;
    }
    else return null;
  }

  /**
   * searches for point of interest not visited by user between a certain period
   * @param u user
   * @param start initial timestamp
   * @param end last timestamp
   * @return arraylist containing all pois between start and end || null if not visited any poi
   * @author Vitor Hugo
   */
  public ArrayList<Poi> getUserPoisNotVisited (User u, TimePeriod tp) {
    if (u == null) throw new IllegalArgumentException("argument 'u' to getUserPoisNotVisited() is null");
    if (tp == null) throw new IllegalArgumentException("argument 'tp' to getUserPoisNotVisited() is null");
    if (this.userST.contains(u.getIdNumber())) {
      ArrayList<Poi> pois = new ArrayList<>();
      // iterate over all point of interest
      for (Integer poi : this.poiST.keys()) {
        Poi p = this.poiST.get(poi);
        // if the user doesn't exist in visitors add it to list
        if (!p.containsVisitor(u)) {
          pois.add(p);
        }
        // otherwise search in timeperiod list for each user
        else {
          LocalDateTime start = tp.getStart();
          LocalDateTime end = tp.getEnd();
          for (String visitor : p.getVisitorST().keys()) {
            ArrayList<TimePeriod> times = p.getVisitorST().get(visitor);
            for (TimePeriod t : times) {
              if ((t.getStart().isAfter(start) || t.getStart().isEqual(start)) 
                  && (t.getEnd().isBefore(end) || t.getEnd().isEqual(end))) {
                pois.add(p);
              }
            }
          }
        }
      }
      return pois;
    }
    return null;    
  }

  /**
   * searches for all users thats visited specified poi between a certain time
   * @param p poi
   * @param start initial timestamp
   * @param end last timestamp
   * @return arraylist containing all users that visited poi between start and end || null if not any
   * @author Vitor Hugo
   */
  public ArrayList<User> getUsersThatVisitedPoi (Poi p, TimePeriod tp) {
    if (p == null) throw new IllegalArgumentException("argument 'p' to getUsersThatVisitedPoi() is null");
    if (tp == null) throw new IllegalArgumentException("argument 'tp' to getUsersThatVisitedPoi() is null");

    if (this.poiST.contains(p.getNodeId())) {
      ArrayList<User> users = new ArrayList<>();
      for (Integer poiId : this.poiST.keys()) {
        Poi poi = this.poiST.get(poiId);
        for (String userId : poi.getVisitorST().keys()) {
          LocalDateTime start = tp.getStart();
          LocalDateTime end = tp.getEnd();
          ArrayList<TimePeriod> times = poi.getVisitorST().get(userId);
          for (TimePeriod t : times) {
            if ((t.getStart().isAfter(start) || t.getStart().isEqual(start)) && (t.getEnd().isBefore(end) || t.getEnd().isEqual(end)))
            users.add(this.userST.get(userId));
          }
        }
      }
      return users;
    }

    return null;
  }
  
  /**
   * searches for pois that weren't visited by any users between a certain time
   * @param start initial timestamp
   * @param end last timestamp
   * @return arraylist containing all poi that weren't visited between specified time || null if all were visited
   */
  public ArrayList<Poi> getPoiNotVisited (Long start, Long end) {
    return null;
  }

  /**
   * takes a snapshot of the current state of the aplication
   * @author Vitor Hugo
   */
  public void now() {
    LocalDateTime currentTime = LocalDateTime.now();
    if (!this.poiST.isEmpty()) {
      for (Integer poisId : this.poiST.keys()) {
        Poi p = this.poiST.get(poisId);
        TimePeriod currentTimePeriod = new TimePeriod(currentTime.minusSeconds(p.getAverageTimeSpent()),
                                                      currentTime.plusSeconds(p.getAverageTimeSpent()));
        System.out.println(p);
        ArrayList<User> currentUsers = getUsersThatVisitedPoi(p, currentTimePeriod);
        currentUsers.forEach(System.out::println);
      }
    }
  }

  

}