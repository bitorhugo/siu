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
    
  public void addPoi (Poi p) {
    this.getVisitedPoi().put((int)System.currentTimeMillis(), p);
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