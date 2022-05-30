package main.edu.ufp.inf.en.models.siu.database.poi;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SeparateChainingHashST;

import main.edu.ufp.inf.en.models.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.models.siu.database.node.Node;
import main.edu.ufp.inf.en.models.siu.database.tag.Tag;
import main.edu.ufp.inf.en.models.siu.database.tag.TagNotFoundException;

/**
 * @author Vitor Hugo
 */
public class Poi extends Node {

  private static final int TIME_PERIOD = 5;
  private static final int MAX_TRAFFIC = 5;

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
   * @return tag removed 
   * @throws TagNotFoundException if tag not found in poi
   */
  public Tag removeTag(Tag t) throws TagNotFoundException {
    if (t == null) throw new IllegalArgumentException("argument to removeTag() is null");
    if (this.tags.contains(t)) {
      this.tags.delete(t);
      return t;
    }
    else throw new TagNotFoundException (t + " not found in poi");
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
    else {
      this.addTag(tag, newTagValue);
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

  /**
   * adds a visitor to poi
   * @param userID user id to add as visitor
   * @param entrance time (seconds) of day user visited poi
   */
  public void addVisitor (String userID, Long entrance) {
    if (userID == null) throw new IllegalArgumentException("argument 'u' to addVisitor() is null");
    if (entrance == null) throw new IllegalArgumentException("argument 'entrance' to addVisitor() is null");
    
    // since {@code visitorEntrances} value is an array,
    // check beforehand if there's already an entrance with that timestamp
    ArrayList<String> visitors;
    if (this.visitorsEntrance.contains(entrance)) {
      // if so, just add another user to that entrance
      visitors = this.visitorsEntrance.get(entrance);
      visitors.add(userID);
    }
    else {
      // otherwise create a new array and add user to it
      visitors = new ArrayList<>();
      visitors.add(userID);
      this.visitorsEntrance.put(entrance, visitors);
    }
    
    // after user is inserted, update traffic
    updateTraffic(entrance);
  }

  public Iterable<Long> visitorEntrancesKeys(Long lo, Long hi) {
    return this.visitorsEntrance.keys(lo, hi);
  }

  /**
   * updates the traffic of poi
   * @param timestamp time (seconds) of day
   */
  private void updateTraffic (Long timestamp) {
    if (timestamp == null) throw new IllegalArgumentException("argument to updateTraffic() is null");
    
    // set a time period to look for traffic 
    Long start = timestamp - TimeUnit.MINUTES.toSeconds(TIME_PERIOD);
    Long end = timestamp + TimeUnit.MINUTES.toSeconds(TIME_PERIOD);

    int traffic = 0; // calculate traffic between start and end

    for (var entrance : this.visitorsEntrance.keys(start, end)) {
      traffic += this.visitorsEntrance.get(entrance).size();
      if (traffic > MAX_TRAFFIC) {
        this.editTag(Tag.TRAFFICJAM, "yes");
        this.editTag(Tag.TRAFFIC, String.valueOf(traffic));
        break;
      }
      else {
        try {
          this.removeTag(Tag.TRAFFICJAM);
          this.editTag(Tag.TRAFFIC, String.valueOf(traffic));
        } catch (Exception e) {
          System.out.println(e.getMessage()); 
        }
      }
    }

  }

  /**
   * lists all visitors of poi
   */
  public void listVisitors() {
    for (var v : this.visitorsEntrance.keys()) {
      System.out.println(this.visitorsEntrance.get(v));
    }
  }
  
  @Override
  public String toString() {
    return "Poi{" + super.toString() + "}";
  }

}