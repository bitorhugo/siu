package main.edu.ufp.inf.en.siu;

import java.util.Objects;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.SeparateChainingHashST;

public class Way extends DirectedEdge {

  /**
   * average speed at which each means of transport travel (measured in meters/minute)
   */
  private enum Transport {
    WALKING(80),
    CYCLING(308.33),
    CITYVEHICLE(833.333),
    HIGHWAYVEHICLE(1833.33), 
    BUS(750);

    final double speed;
    private Transport (double speed){
      this.speed = speed;
    }    
  }

  private final Integer wayId;
  
  private SeparateChainingHashST<Tag, String> tags = new SeparateChainingHashST<>();
  
  // key: transport, value: timeWeight (minutes)
  private SeparateChainingHashST<Transport, Double> timeWeights = new SeparateChainingHashST<>();
  
  private double chosenWeight;
  
  public Way(Integer wayId, int o, int t, double w) {
    super(o, t, w);
    this.wayId = wayId;
    this.chosenWeight = w;
    for (var v : Transport.values()) {
      double timeWeight = (this.weight() / v.speed);
      timeWeights.put(v, timeWeight);
    }
  }

  public Way(Integer wayId, SeparateChainingHashST<Tag, String> tags, int o, int t, double w) {
    this(wayId, o, t, w);
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

  public Tag removeTag (Tag t) {
    if (t == null) throw new IllegalArgumentException("argument to removeTag() is null");
    if (this.tags.contains(t)) {
      this.tags.delete(t);
      return t;
    }
    else {
      System.out.println("tag is not present in way");
      return null;
    }
  }

  public StringBuilder listTags() {
    StringBuilder str = new StringBuilder();
    if (this.tags.isEmpty()) {
      for (var v : this.tags.keys()) {
        str.append(v);
        str.append(",");
        str.append(this.tags.get(v));
        str.append(",");
      }
      System.out.println(str);
    }
    return str;
  }

  public boolean containsTag(Tag t) {
    return this.tags.contains(t);
  }

  @Override
  public double weight() {
    return this.chosenWeight;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.tags, super.hashCode());
}
  
  @Override
  public String toString() {
      return this.wayId + "," + this.from() + "," + this.to() + "," + super.weight() + "," + this.listTags();
  }

}