package edu.ufp.inf.en.siu;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.ufp.inf.en.lp2._1_intro.date.Date;
import edu.ufp.inf.en.lp2._1_intro.person.Person;
import edu.ufp.inf.en.lp2._5_bt.Vehicle;

public abstract class User extends Person {

  private String email;
  private String password;
  private Vehicle mobilityType = new Vehicle();
  private DataBase db;
  private RedBlackBST<Integer, Poi> visitedPoi = new RedBlackBST<>();

  public User(String name, String address, String idNumber, Date birth) {
    super(name, address, idNumber, birth);
    
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Vehicle getMobilityType() {
    return mobilityType;
  }

  public void setMobilityType(Vehicle mobilityType) {
    this.mobilityType = mobilityType;
  }

  public DataBase getDb() {
    return db;
  }

  public void setDb(DataBase db) {
    this.db = db;
  }

  public RedBlackBST<Integer, Poi> getVisitedPoi() {
    return this.visitedPoi;
  }

  public void setVisitedPoi(RedBlackBST<Integer, Poi> visitedPoi) {
    this.visitedPoi = visitedPoi;
  }

  @Override
  public String toString() {
      return super.toString() + ", email=" + this.email + ", password=" + this.password;
  }

}