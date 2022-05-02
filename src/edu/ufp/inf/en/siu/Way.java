package edu.ufp.inf.en.siu;

import java.util.ArrayList;
import java.util.Objects;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.SeparateChainingHashST;



public class Way extends DirectedEdge {

  /**
   * average speed at which each means of transport travel (measured in meters/minute)
   */
  private enum TransportSpeed {
    WALKINGSPEED(80),
    CYCLINGSPEED(308.33),
    CITYSPEED(833.333),
    HIGHWAYSPEED(1833.33), 
    BUSSPEED(750);

    final double value;
    private TransportSpeed (double value){
      this.value = value;
    }    
  }

  private final Integer wayId;
  private SeparateChainingHashST<Tag, String> tags = new SeparateChainingHashST<>();
  // key: transportSpeed, value: timeWeight (minutes)
  private SeparateChainingHashST<TransportSpeed, Double> timeWeights = new SeparateChainingHashST<>();
  
  // add time-weights here aswell
  // car, walk, bus, ...
  // overwrite weight method from edgeweightedigraph
  
  // w is distance-weights
  public Way(Integer wayId, int o, int t, double w) {
    super(o, t, w);
    this.wayId = wayId;
    for (var v : TransportSpeed.values()) {
      double timeWeight = (this.weight() / v.value);
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

  public boolean containsTag(Tag t) {
    return this.tags.contains(t);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.tags, super.hashCode());
}
  
  
}