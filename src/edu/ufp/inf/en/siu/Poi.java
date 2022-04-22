package edu.ufp.inf.en.siu;

import edu.ufp.inf.en.lp2._1_intro.geometric_figures.Point;

// extends node
public class Poi extends Node{

  private String name;
  private Integer poiId;
  
  public Poi(Integer nodeId, Point coordinates) {
    super(nodeId, coordinates);
  }

  public Poi (Node n) {
    super(n.getNodeId(), n.getCoordinates());
  }

  public Poi (Integer poiId, String name, Integer nodeId, Point coordinates) {
    this(nodeId, coordinates);
    this.name = name;
    this.poiId = poiId;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Integer getPoiId() {
    return poiId;
  }
  public void setPoiId(Integer poiId) {
    this.poiId = poiId;
  }

  


}