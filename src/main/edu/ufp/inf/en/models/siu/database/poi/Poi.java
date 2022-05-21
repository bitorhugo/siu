package main.edu.ufp.inf.en.models.siu.database.poi;

import java.util.ArrayList;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import main.edu.ufp.inf.en.models.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.models.siu.database.node.Node;
import main.edu.ufp.inf.en.models.siu.database.tag.Tag;
import main.edu.ufp.inf.en.models.siu.user.User;

// extends node
@SuppressWarnings("unused")
public class Poi extends Node{

  private SeparateChainingHashST<Tag, String> tags = new SeparateChainingHashST<>();
  
  private RedBlackBST<Long, ArrayList<User>> visitorsEntrance = new RedBlackBST<>();
  
  private RedBlackBST<Long, ArrayList<User>> visitorsExit = new RedBlackBST<>();
  
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

  public RedBlackBST<Long, ArrayList<User>> getVisitorsEntrance() {
    return visitorsEntrance;
  }

  public void setVisitorsEntrance(RedBlackBST<Long, ArrayList<User>> visitorsEntrance) {
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
   * edits a tag from node
   * @param o old tag to remove
   * @param n new tag to insert
   * @param nValue value of new tag (e.g. BUILDING=TRUE)
   */
  public void editTag(Tag o, Tag n, String nValue) {
    if (o == null) throw new IllegalArgumentException("argument 'o' to editTag() is null");
    if (n == null) throw new IllegalArgumentException("argument 'n' to editTag() is null");
    if (removeTag(o) != null) addTag(n, nValue);
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
   * lists all tags in node
   */
  public StringBuilder listTags() {
    StringBuilder str = new StringBuilder();
    if (!this.tags.isEmpty()) {
      int i = 0;
      for (var v : this.tags.keys()) {
        if (i == this.tags.size() - 1) {
          str.append(v);
          str.append(",");
          str.append(this.tags.get(v));
        }
        else {
          str.append(v);
          str.append(",");
          str.append(this.tags.get(v));
          str.append(",");
        }
        i ++;
      }
      System.out.println(str);
    }
    return str;
  }

  public void addVisitor (User u, Long entrance) {
    if (u == null) throw new IllegalArgumentException("argument 'u' to addVisitor() is null");
    if (entrance == null) throw new IllegalArgumentException("argument 'entrance' to addVisitor() is null");
    
    ArrayList<User> visitors;
    if (this.visitorsEntrance.contains(entrance)) {
      visitors = this.visitorsEntrance.get(entrance);
      visitors.add(u);
    }
    else {
      visitors = new ArrayList<>();
      visitors.add(u);
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