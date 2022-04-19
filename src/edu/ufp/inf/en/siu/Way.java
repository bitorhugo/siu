package edu.ufp.inf.en.siu;

import edu.princeton.cs.algs4.SeparateChainingHashST;

public class Way {

  private Integer wayId = this.hashCode(); // autoboxing
  private String name;
  private SeparateChainingHashST<Tag, String> tags;
  
  public Way(Integer wayId, String name, SeparateChainingHashST<Tag, String> tags) {
    this.wayId = wayId;
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

  
  
}