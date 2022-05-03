package main.edu.ufp.inf.en.siu;

import java.util.ArrayList;

import edu.princeton.cs.algs4.RedBlackBST;
import main.edu.ufp.inf.en.lp2._1_intro.geometric_figures.Point;

// extends node
public class Poi extends Node{

  // maps timeperiod to user
  private RedBlackBST<TimePeriod, ArrayList<User>> visitorST = new RedBlackBST<>();
  
  public Poi(Integer nodeId, Point coordinates) {
    super(nodeId, coordinates);
  }
  
  public Poi (Node n) {
    super(n.getNodeId(), n.getCoordinates());
  }

  public RedBlackBST<TimePeriod, ArrayList<User>> getVisitorST() {
    return visitorST;
  }
  public void setVisitorST(RedBlackBST<TimePeriod, ArrayList<User>> visitorST) {
    this.visitorST = visitorST;
  }  

  public void addVisitor (User u, TimePeriod tp) {
    if (u == null) throw new IllegalArgumentException("argument to addVisitor() is null");
    // we need to check weather timeperiod already exists in visitors
    if (this.visitorST.contains(tp)) {
      this.visitorST.get(tp).add(u);
    }
    else {
      ArrayList<User> users = new ArrayList<>();
      users.add(u);
      this.visitorST.put(tp, users);
    }
    
  }

}