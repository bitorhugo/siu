package edu.ufp.inf.en.siu;

import java.util.ArrayList;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SeparateChainingHashST;


public class DataBase {
  
  private RedBlackBST<Integer, Node> nodesST = new RedBlackBST<>();
  private RedBlackBST<Integer, Way> waysST = new RedBlackBST<>();
  private RedBlackBST<Integer, Poi> poiST = new RedBlackBST<>();
  private RedBlackBST<String, User> userST = new RedBlackBST<>();
  private SeparateChainingHashST<Tag, ArrayList<Object>> tagsST = new SeparateChainingHashST<>();

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
  public RedBlackBST<String, User> getUserST() {
    return userST;
  }
  public void setUserST(RedBlackBST<String, User> userST) {
    this.userST = userST;
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
   */
  public User removeUser(User u) {
    if (u == null) throw new IllegalArgumentException("argument to removeUser() is null");
    if (!this.userST.contains(u.getIdNumber())) {
      System.out.println("User not present in DataBase");
      return null;
    }
    this.userST.delete(u.getIdNumber());
    Arquive.removedUser(u);
    return u;
  }

  /**
   * edits a user from Database
   * @param o old user to edit
   * @param n new user to replace the old one
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
   */
  public User searchUser(String id) {
    if (id == null) throw new IllegalArgumentException("argument to searchUser() is null");
    return this.userST.get(id);
  }

  /**
   * lists all users from DataBase
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
   */
  public void addNode(Node n) {
    if (n == null) throw new IllegalArgumentException("argument to addNode() is null");
    this.nodesST.put(n.getNodeId(), n);
  }

  /**
   * removes a node from database
   * @param n node to remove
   * @return removed node || null if not found
   */
  public Node removeNode(Node n) {
    if (n == null) throw new IllegalArgumentException("argument to removeNode() is null");
    if (this.nodesST.contains(n.getNodeId())) {
      this.nodesST.delete(n.getNodeId());
      Arquive.removedNode(n);
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
   */
  public Node searchNode(Integer id) {
    if (id == null) throw new IllegalArgumentException("argument for searchNode() is null");
    return this.nodesST.get(id);
  }

  /**
   * lists all nodes from database
   */
  public void listNodes() {
    if (!this.nodesST.isEmpty()) {
      for (Integer i : this.nodesST.keys()) {
        System.out.println("Node: " + i);
      }
    }
  }

  /**
   * adds a way to database
   * @param w way to add
   */
  public void addWay(Way w) {
    if (w == null) throw new IllegalArgumentException("argument to addWay() is null");
    this.waysST.put(w.getWayId(), w);
  }

  /**
   * removes a way from database
   * @param w way to remove
   * @return removed way || null if not found
   */
  public Way removeWay(Way w) {
    if (w == null) throw new IllegalArgumentException("argument to removeWay() is null");
    if (this.waysST.contains(w.getWayId())) {
      this.waysST.delete(w.getWayId());
      Arquive.removedWay(w);
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
   */
  public void editWay(Way o, Way n) {
    if (o == null) throw new IllegalArgumentException("argument 'o' to editWay() is null");
    if (n == null) throw new IllegalArgumentException("argument 'n' to editWay() is null");
    if (removeWay(o) != null) addWay(n);
  }

  /**
   * lists all ways from database
   */
  public void listWay() {
    if (!this.waysST.isEmpty()) {
      for (Integer i : this.waysST.keys()) {
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
   */
  private void addTag (Tag t) { 
    if (t == null) throw new IllegalArgumentException("argument to addTag() is null");
    // create arraylist to insert as a value
    ArrayList<Object> al = new ArrayList<>();

    if (!this.nodesST.isEmpty()) {
      // iterate over nodes
      for (Integer i : this.nodesST.keys()) {
        Node n = this.nodesST.get(i);
        if (n.containsTag(t)) {
          al.add(n);
        }
      }
    }
    if (!this.waysST.isEmpty()) {
      // iterate over ways
      for (Integer i : this.waysST.keys()) {
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
   */
  private void removeTagsObject (Object o, Tag t) {
    if (t == null) throw new IllegalArgumentException("argument to removeTag() is null");
    if (this.tagsST.contains(t)) {
      // remove instance of object from arraylist
      tagsST.get(t).remove(o);
      if (tagsST.get(t).isEmpty()) {
        // remove tag from tagsST
        this.tagsST.delete(t);
        Arquive.removedTag(t);
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
   */
  public Tag removeNodeTag (Node n, Tag t) {
    if (this.nodesST.contains(n.getNodeId())){
      this.nodesST.get(n.getNodeId()).removeTag(t);
      removeTagsObject(n, t);
      Arquive.removedTag(n, t);
      return t;
    }
    return null;
  }

  /**
   * removes specified tag from way
   * @param w way
   * @param t tag
   * @return tag removed if found || null if not found
   */
  public Tag removeWayTag (Way w, Tag t) {
    if (this.nodesST.contains(w.getWayId())){
      this.nodesST.get(w.getWayId()).removeTag(t);
      removeTagsObject(w, t);
      Arquive.removedTag(w, t);
      return t;
    }
    return null;
  }

  /**
   * lists all tags used in database
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

  
}