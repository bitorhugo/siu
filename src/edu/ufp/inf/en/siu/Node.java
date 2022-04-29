package edu.ufp.inf.en.siu;


import java.util.Objects;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.ufp.inf.en.lp2._1_intro.geometric_figures.Point;

public class Node {

  private Long nodeId; 
  private Point coordinates;
  private SeparateChainingHashST<Tag, String> tags = new SeparateChainingHashST<>();

  public Node () {
    
  }

  public Node (Long nodeId, Point coordinates) {
    this();
    this.nodeId = nodeId;
    this.coordinates = coordinates;
  }

  public Long getNodeId() {
    return nodeId;
  }
  public void setNodeId(Long nodeId) {
    this.nodeId = nodeId;
  }
  public Point getCoordinates() {
    return coordinates;
  }
  public void setCoordinates(Point coordinates) {
    this.coordinates = coordinates;
  }
  public SeparateChainingHashST<Tag, String> getTags() {
    return tags;
  }
  public void setTags(SeparateChainingHashST<Tag, String> tags) {
    this.tags = tags;
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
    return Objects.hash(this.coordinates, this.tags.hashCode());
  }

}