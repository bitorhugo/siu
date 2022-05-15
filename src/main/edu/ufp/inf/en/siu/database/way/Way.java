package main.edu.ufp.inf.en.siu.database.way;

import java.util.Objects;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import main.edu.ufp.inf.en.siu.database.tag.Tag;
import main.edu.ufp.inf.en.siu.database.transport.Transport;

public class Way extends DirectedEdge {

  private final Integer wayId;
  private SeparateChainingHashST<Tag, String> tags = new SeparateChainingHashST<>();
  // key:transport value:timeWeight (minutes)
  private SeparateChainingHashST<Transport, Double> timeWeights = new SeparateChainingHashST<>();
  private Double distanceWeight;
  private double chosenWeight;
  
  public Way(Integer wayId, int o, int t, double w) {
    // by default edge weight is the distanceWeight
    super(o, t, w);
    this.wayId = wayId;
    this.distanceWeight = w;
    this.chosenWeight = w;
    for (var v : Transport.values()) {
      double timeWeight = (w / v.speed);
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
  
  public double getChosenWeight() {
    return chosenWeight;
  }
  public void setChosenWeight(String transportType) {
    this.chosenWeight = timeWeights.get(Transport.valueOf(transportType.toUpperCase()));
  }
  
  public SeparateChainingHashST<Transport, Double> getTimeWeights() {
    return timeWeights;
  }
  public void setTimeWeights(SeparateChainingHashST<Transport, Double> timeWeights) {
    this.timeWeights = timeWeights;
  }
  
  public Double getDistanceWeight() {
    return distanceWeight;
  }
  public void setDistanceWeight(Double distanceWeight) {
    this.distanceWeight = distanceWeight;
  }

  /**
   * adds a tag to way
   * @param t tag
   * @param value value of tag
   */
  public void addTag (Tag t, String value) {
    this.tags.put(t, value);
  }

  /**
   * removes a tag from way
   * @param t tag to remove
   * @return removed tag
   */
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

  /**
   * edits a tag from way
   * @param o old tag
   * @param n new tag
   * @param value value for new tag
   */
  public void editTag (Tag o, Tag n, String value) {
    if (o == null) throw new IllegalArgumentException("argument 'o' to editTag() is null");
    if (n == null) throw new IllegalArgumentException("argument 'n' to editTag() is null");
    if (removeTag(o) != null) addTag(n, value);
  }

  /**
   * checks if way has specified tag
   * @param t tag
   * @return true if found || false if not found
   */
  public boolean containsTag(Tag t) {
    return this.tags.contains(t);
  }

  /**
   * lists all tags from way
   * @return a string with all tags
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

  /*
  @Override
  public double weight() {
    return this.chosenWeight;
  }*/

  @Override
  public int hashCode() {
    return Objects.hash(this.tags, super.hashCode());
}
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (this.getClass() != obj.getClass()) return false;
    Way w = (Way) obj;
    return Objects.equals(this.wayId, w.getWayId());
  }

  /*@Override
  public String toString() {
      return this.wayId + "," + this.from() + "," + this.to() + "," + super.weight() + "," + this.listTags();
  }*/

}