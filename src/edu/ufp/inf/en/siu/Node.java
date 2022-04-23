package edu.ufp.inf.en.siu;


import java.util.Objects;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.ufp.inf.en.lp2._1_intro.geometric_figures.Point;

public class Node {

  private Integer nodeId; 
  private Point coordinates;
  private RedBlackBST<Integer, Poi> poiST = new RedBlackBST<>();
  private SeparateChainingHashST<Tag, String> tags = new SeparateChainingHashST<>();

  public Node () {
    
  }

  public Node (Integer nodeId, Point coordinates) {
    this();
    this.nodeId = this.hashCode(); // autoboxing
    this.coordinates = coordinates;
  }

  public Integer getNodeId() {
    return nodeId;
  }
  public Point getCoordinates() {
    return coordinates;
  }
  public void setCoordinates(Point coordinates) {
    this.coordinates = coordinates;
  }
  public RedBlackBST<Integer, Poi> getPoiST() {
    return poiST;
  }
  public void setPoiST(RedBlackBST<Integer, Poi> poiST) {
    this.poiST = poiST;
  }
  public SeparateChainingHashST<Tag, String> getTags() {
    return tags;
  }
  public void setTags(SeparateChainingHashST<Tag, String> tags) {
    this.tags = tags;
  }

/**
 * adds a poi to Node
 * @param p poi to add
 */
  public void addPoi (Poi p) {
    if (p == null) throw new IllegalArgumentException("argument to addPoi() is null");
    this.poiST.put(p.getPoiId(), p);
  }

  /**
   * removes a poi from node
   * @param p poi to be removed
   * @return poi if found || null if not found
   */
  public Poi removePoi (Poi p) {
    if (p == null) throw new IllegalArgumentException("argument to removePoi() is null");
    if (this.poiST.contains(p.getPoiId())) {
      this.poiST.delete(p.getPoiId());
      Arquive.removedPoi(p);
      return p;
    }
    else {
      System.out.println("poi not present in node");
      return null;
    }
  }

  /**
   * edits a poi from node
   * @param o old poi
   * @param n new poi
   */
  public void editPoi(Poi o, Poi n) {
    if (o == null) throw new IllegalArgumentException("argument 'o' to editPoi() is null");
    if (n == null) throw new IllegalArgumentException("argument 'n' to editPoi() is null");
    if (removePoi(o) != null) addPoi(n);
  }

  /**
   * lists all poi from node
   */
  public void listPoi() {
    if (!this.poiST.isEmpty()) {
      for (Integer i : this.poiST.keys()) {
        System.out.println("Poi: " + i);
      }
    }
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
   * lists all tags in node
   */
  public void listTags() {
    if (!this.tags.isEmpty()) {
      for (Tag tag : this.tags.keys()) {
        System.out.println("Tag: " + tag);
      }
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

  @Override
  public int hashCode() {
    return Objects.hash(this.coordinates, this.poiST.hashCode(), this.tags.hashCode());
  }

}