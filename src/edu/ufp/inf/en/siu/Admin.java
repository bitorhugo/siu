package edu.ufp.inf.en.siu;

import java.util.Objects;

import edu.ufp.inf.en.lp2._1_intro.date.Date;

public class Admin extends User {

  public Admin(String name, String address, String idNumber, Date birth, String email, String password) {
    super(name, address, idNumber, birth);
    this.setEmail(email);
    this.setPassword(password);
  }

  public void addUser(User u) {
    this.getDb().addUser(u);
  }

  public User removeUser(User u) {
    return this.getDb().removeUser(u);
  }
    
  public void addVisitedPoi (Poi p) {
    if (p == null) throw new IllegalArgumentException("argument to deleteVisitedPoi() is null");
    Long timestamp = System.currentTimeMillis();
    // first add user to poi visitor list
    p.getVisitorST().put(timestamp, this);
    // then add poi to users visited poi list
    this.getVisitedPoi().put(timestamp, p);
    // in case the database doesn't have the poi, add it
    this.getDb().addPoi(p);
  }

  public Poi deleteVisitedPoi (Poi p) {
    if (p == null) throw new IllegalArgumentException("argument to deleteVisitedPoi() is null");
    for (Long l : this.getVisitedPoi().keys()) {
      if (p.getNodeId() == this.getVisitedPoi().get(l).getNodeId()) {
        this.getVisitedPoi().delete(l);
        return p;
      }
    }
    System.out.println("user hasn't been to poi p before");
    return null;
  }

  public void editVisitedPoi (Poi o, Poi n) {
    if (o == null) throw new IllegalArgumentException("argument 'o' to editVisitedPoi() is null");
    if (n == null) throw new IllegalArgumentException("argument 'n' to editVisitedPoi() is null");
    if (deleteVisitedPoi(o) != null) addVisitedPoi(n);
  }

  public void listVisitedPoi () {
    if (!this.getVisitedPoi().isEmpty()) {
      for (Long l : this.getVisitedPoi().keys()) {
        System.out.println("Visited Poi: " + l);
      }
    }
  }
  
  @Override
  public String toString() {
      return "Admin(" + super.toString() + ")";
  }
  
  @Override
  public int hashCode() {
      return Objects.hash(super.hashCode());
  }

}