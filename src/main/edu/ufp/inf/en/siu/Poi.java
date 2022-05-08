package main.edu.ufp.inf.en.siu;

import java.util.ArrayList;

import edu.princeton.cs.algs4.RedBlackBST;
import main.edu.ufp.inf.en.lp2._1_intro.geometric_figures.Point;

// extends node
public class Poi extends Node{

  // maps timeperiod to user
  private RedBlackBST<String, ArrayList<TimePeriod>> visitorST = new RedBlackBST<>();
  private Long averageTimeSpent; // measured in seconds
  
  public Poi(Integer nodeId, Point coordinates) {
    super(nodeId, coordinates);
  }
  
  public Poi (Node n) {
    super(n.getNodeId(), n.getCoordinates());
  }
  
  public RedBlackBST<String, ArrayList<TimePeriod>> getVisitorST() {
    return visitorST;
  }
  public void setVisitorST(RedBlackBST<String, ArrayList<TimePeriod>> visitorST) {
    this.visitorST = visitorST;
  }
  public Long getAverageTimeSpent() {
    return averageTimeSpent;
  }
  public void setAverageTimeSpent(Long averageTimeSpent) {
    this.averageTimeSpent = averageTimeSpent;
  }

  public void addVisitor (User u, TimePeriod tp) {
    if (u == null) throw new IllegalArgumentException("argument to addVisitor() is null");
    // we need to check weather timeperiod already exists in visitors
    ArrayList<TimePeriod> timePeriods;
    if (this.containsVisitor(u)) {
      timePeriods = this.visitorST.get(u.getIdNumber());
      timePeriods.add(tp);
      this.visitorST.put(u.getIdNumber(), timePeriods);
    }
    else {
      timePeriods = new ArrayList<>();
      timePeriods.add(tp);
      this.visitorST.put(u.getIdNumber(), timePeriods);
    }
  }

  public void listVisitors() {
    if (!this.visitorST.isEmpty()) {
      for (var v : this.visitorST.keys()) {
        System.out.println("User: " + v);
      }
    }
  }

  public boolean containsVisitor (User u) {
    if (u == null) throw new IllegalArgumentException("argument to containsUser() is null");
    return this.visitorST.contains(u.getIdNumber()) ? true : false;
  }
  
  @Override
  public String toString() {
      return super.toString() + "," + this.averageTimeSpent;
  }
}