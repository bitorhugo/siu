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
    // first add user to poi visitor list
    p.getVisitorST().put(System.currentTimeMillis(), this);
    // then add poi to users visited poi list
    this.getVisitedPoi().put(p.getNodeId(), p);
    // in case the database does'nt have the poi, add it
    this.getDb().addPoi(p);
  }

  public Poi deleteVisitedPoi (Poi p) {
    if (p == null) throw new IllegalArgumentException("argument to deleteVisitedPoi() is null");
    if (this.getVisitedPoi().contains(p.getNodeId())) {
      this.getVisitedPoi().delete(p.getNodeId());
      return p;
    }
    else {
      System.out.println("user hasn't been to poi p before");
      return null;
    }
  }

  public void editVisitedPoi (Poi o, Poi n) {
    if (o == null) throw new IllegalArgumentException("argument 'o' to editVisitedPoi() is null");
    if (n == null) throw new IllegalArgumentException("argument 'n' to editVisitedPoi() is null");
    if (deleteVisitedPoi(o) != null) addVisitedPoi(n);
  }

  public void listVisitedPoi () {
    if (!this.getVisitedPoi().isEmpty()) {
      for (Integer integer : this.getVisitedPoi().keys()) {
        System.out.println("Visited Poi: " + integer);
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