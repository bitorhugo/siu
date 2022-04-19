package edu.ufp.inf.en.siu;

import java.util.Objects;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.SeparateChainingHashST;

public class Way extends Edge{

  private final Integer wayId = this.hashCode(); // autoboxing
  private String name; // TODO: ask teacher if way name should be a tag
  private SeparateChainingHashST<Tag, String> tags;
  
  public Way(int o, int t, double w) {
    super(o, t, w);
  }

  public Way(String name, SeparateChainingHashST<Tag, String> tags, int o, int t, double w) {
    this(o, t, w);
    this.name = name;
    this.tags = tags;
  }

  public Integer getWayId() {
    return wayId;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public SeparateChainingHashST<Tag, String> getTags() {
    return tags;
  } 
  public void setTags(SeparateChainingHashST<Tag, String> tags) {
    this.tags = tags;
  }

  public boolean containsTag(Tag t) {
    return this.tags.contains(t);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.tags, super.hashCode());
}
  
  
}