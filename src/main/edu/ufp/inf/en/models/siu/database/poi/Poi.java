package main.edu.ufp.inf.en.models.siu.database.poi;

import java.io.Serializable;
import java.util.ArrayList;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import main.edu.ufp.inf.en.models.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.models.siu.database.node.Node;
import main.edu.ufp.inf.en.models.siu.database.tag.Tag;
import main.edu.ufp.inf.en.models.siu.user.User;

// extends node
@SuppressWarnings("unused")
public class Poi extends Node {

  private SeparateChainingHashST<Tag, String> tags = new SeparateChainingHashST<>();
  
  private RedBlackBST<Long, ArrayList<String>> visitorsEntrance = new RedBlackBST<>();
  
  //private RedBlackBST<Long, ArrayList<User>> visitorsExit = new RedBlackBST<>();
  
  public Poi(Integer nodeId, Point coordinates) {
    super(nodeId, coordinates);
  }
  
  public Poi (Node n) {
    super(n.getNodeId(), n.getCoordinates());
  }
  
  public SeparateChainingHashST<Tag, String> getTags() {
    return tags;
  }

  public void setTags(SeparateChainingHashST<Tag, String> tags) {
    this.tags = tags;
  }

  public RedBlackBST<Long, ArrayList<String>> getVisitorsEntrance() {
    return visitorsEntrance;
  }

  public void setVisitorsEntrance(RedBlackBST<Long, ArrayList<String>> visitorsEntrance) {
    this.visitorsEntrance = visitorsEntrance;
  }

  /**
   * adds a tag to node
   * @param t tag
   * @param value value associated with the tag (e.g. BUILDING=TRUE)
   */
  public void addTag(Tag t, String value) {
    if (t == null) throw new IllegalArgumentException("argument 't' to addTag() is null");
    if (value == null) throw new IllegalArgumentException("argument 'value' to addTag() is null");
    this.tags.put(t, value);
  }

  /**
   * removes a tag from node
   * @param t tag to remove
   * @return tag removed || null if not found
   */
  public Tag removeTag(Tag t) {
    if (t == null) throw new IllegalArgumentException("argument to removeTag() is null");
    if (this.tags.contains(t)) {
      this.tags.delete(t);
      return t;
    }
    else {
      System.out.println("tag is not present in node");
      return null;
    }
  }

  /**
   * edits tag from poi
   * @param tag tag to be edited
   * @param newTagValue new value associated with tag
   */
  public void editTag(Tag tag, String newTagValue) {
    if (tag == null) throw new IllegalArgumentException("argument 'o' to editTag() is null");
    if (newTagValue == null) throw new IllegalArgumentException("argument 'n' to editTag() is null");
    if (this.containsTag(tag)) {
      tag.setValue(newTagValue);
    }
  }

  /**
   * checks if node contains a certain tag
   * @param t tag to search
   * @return true if found || false if not 
   */
  public boolean containsTag(Tag t) {
    return this.tags.contains(t);
  }

  /**
   * lists all tags in from way
   * @return a string containing all tags
   */
  public String listTags() {
    String str = new String();
    for (var tag : this.tags.keys()) {
      String tagValue = this.tags.get(tag);
      str += "{" + tag + "," + tagValue + "}";
    }
    return str;
  }

  public void addVisitor (String userID, Long entrance) {
    if (userID == null) throw new IllegalArgumentException("argument 'u' to addVisitor() is null");
    if (entrance == null) throw new IllegalArgumentException("argument 'entrance' to addVisitor() is null");
    
    ArrayList<String> visitors;
    if (this.visitorsEntrance.contains(entrance)) {
      visitors = this.visitorsEntrance.get(entrance);
      visitors.add(userID);
    }
    else {
      visitors = new ArrayList<>();
      visitors.add(userID);
      this.visitorsEntrance.put(entrance, visitors);
    }
    
  }

  public void listVisitors() {
    for (var v : this.visitorsEntrance.keys()) {
      System.out.println(this.visitorsEntrance.get(v));
    }
  }
  
  @Override
  public String toString() {
      return super.toString();
  }

}