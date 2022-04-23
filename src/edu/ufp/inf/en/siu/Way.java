package edu.ufp.inf.en.siu;

import java.util.Objects;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.SeparateChainingHashST;


public class Way extends DirectedEdge {

  private final Integer wayId; // autoboxing
  private SeparateChainingHashST<Tag, String> tags = new SeparateChainingHashST<>();
  
  // add time-weights here aswell
  // car, walk, bus, ...
  // overwrite weight method from edgeweightedigraph
  
  // w is distance-weights
  public Way(int o, int t, double w) {
    super(o, t, w);
    this.wayId = this.hashCode();
  }

  public Way(SeparateChainingHashST<Tag, String> tags, int o, int t, double w) {
    this(o, t, w);
    this.tags = tags;
  }

  public Integer getWayId() {
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