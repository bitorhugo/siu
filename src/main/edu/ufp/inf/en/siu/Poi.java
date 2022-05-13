package main.edu.ufp.inf.en.siu;

import java.util.ArrayList;

import edu.princeton.cs.algs4.RedBlackBST;
import main.edu.ufp.inf.en.lp2._1_intro.geometric_figures.Point;

// extends node
public class Poi extends Node{

  // maps timeperiod to user
  private RedBlackBST<String, ArrayList<TimePeriod>> visitorST = new RedBlackBST<>();
  
  private RedBlackBST<Long, ArrayList<User>> visitorsEntrance = new RedBlackBST<>();
  private RedBlackBST<Long, ArrayList<User>> visitorsExit = new RedBlackBST<>();
  
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
  
  /*
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
  */

  public RedBlackBST<Long, ArrayList<User>> getVisitorsEntrance() {
    return visitorsEntrance;
  }

  public void setVisitorsEntrance(RedBlackBST<Long, ArrayList<User>> visitorsEntrance) {
    this.visitorsEntrance = visitorsEntrance;
  }

  // TODO: refactor for standalone use, for each timestamp only one user is allowed
  public void addVisitor (User u, Long entrance) {
    if (u == null) throw new IllegalArgumentException("argument 'u' to addVisitor() is null");
    if (entrance == null) throw new IllegalArgumentException("argument 'entrance' to addVisitor() is null");
    
    ArrayList<User> visitors;
    if (this.visitorsEntrance.contains(entrance)) {
      visitors = this.visitorsEntrance.get(entrance);
      visitors.add(u);
    }
    else {
      visitors = new ArrayList<>();
      visitors.add(u);
      this.visitorsEntrance.put(entrance, visitors);
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
      return super.toString();
  }
}