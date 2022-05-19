package main.edu.ufp.inf.en.models.siu.database.node;

import java.util.Objects;

import main.edu.ufp.inf.en.models.lp2._1_intro.geometric_figures.Point;


public class Node {

  private Integer nodeId; 
  
  private Point coordinates;
  
  private Integer indexMap;

  public Node () {}

  public Node (Integer nodeId, Point coordinates) {
    this();
    this.nodeId = nodeId;
    this.coordinates = coordinates;
  }

  public Integer getNodeId() {
    return nodeId;
  }
  
  public void setNodeId(Integer nodeId) {
    this.nodeId = nodeId;
  }
  
  public Point getCoordinates() {
    return coordinates;
  }
  
  public void setCoordinates(Point coordinates) {
    this.coordinates = coordinates;
  }
  
  public Integer getIndexMap() {
    return indexMap;
  }
  public void setIndexMap(Integer indexMap) {
    this.indexMap = indexMap;
  }

  

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (this.getClass() != obj.getClass()) return false;
    Node n = (Node) obj;
    return Objects.equals(this.getNodeId(), n.getNodeId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.coordinates);
  }

  @Override
  public String toString() {
      return this.nodeId + "," + this.coordinates;
  }
}