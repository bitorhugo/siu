package edu.ufp.inf.en.siu;

import java.util.Objects;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.SeparateChainingHashST;


public class Way extends DirectedEdge {

  private final Long wayId;
  private SeparateChainingHashST<Tag, String> tags = new SeparateChainingHashST<>();
  
  // add time-weights here aswell
  // car, walk, bus, ...
  // overwrite weight method from edgeweightedigraph
  
  // w is distance-weights
  public Way(Long wayId, int o, int t, double w) {
    super(o, t, w);
    this.wayId = wayId;
  }

  public Way(Long wayId, SeparateChainingHashST<Tag, String> tags, int o, int t, double w) {
    this(wayId, o, t, w);
    this.tags = tags;
  }

  public Long getWayId() {
    return wayId;
  }
  public SeparateChainingHashST<Tag, String> getTags() {
    return tags;
  } 
  public void setTags(SeparateChainingHashST<Tag, String> tags) {
    this.tags = tags;
  }

  public void addTag (Tag t, String value) {
    this.tags.put(t, value);
  }

  public boolean containsTag(Tag t) {
    return this.tags.contains(t);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.tags, super.hashCode());
}
  
  
}