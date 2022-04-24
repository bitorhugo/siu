package edu.ufp.inf.en.siu;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.ufp.inf.en.lp2._1_intro.geometric_figures.Point;

// extends node
public class Poi extends Node{

  // maps timestamp to user
  private RedBlackBST<Long, User> visitorST = new RedBlackBST<>();
  
  public Poi(Integer nodeId, Point coordinates) {
    super(nodeId, coordinates);
  }
  public Poi (Node n) {
    super(n.getNodeId(), n.getCoordinates());
  }

  public RedBlackBST<Long, User> getVisitorST() {
    return visitorST;
  }
  public void setVisitorST(RedBlackBST<Long, User> visitorST) {
    this.visitorST = visitorST;
  }  

  public void addVisitor (User u) {
    if (u == null) throw new IllegalArgumentException("argument to addVisitor() is null");
    this.visitorST.put(System.currentTimeMillis(), u);
  }

  public User removeVisitor (Long timestamp) {
    if (timestamp == null) throw new IllegalArgumentException("argument to addVisitor() is null");
    if (this.visitorST.contains(timestamp)) {
      User u = this.visitorST.get(timestamp);
      this.visitorST.delete(timestamp);
      return u;
    }
    else {
      System.out.println("Visitor not present in visitoST");
      return null;
    }
  }

}