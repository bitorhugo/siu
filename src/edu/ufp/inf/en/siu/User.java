package edu.ufp.inf.en.siu;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.ufp.inf.en.lp2._1_intro.date.Date;
import edu.ufp.inf.en.lp2._1_intro.person.Person;

public abstract class User extends Person {

  private String email;
  private String password;
  private DataBase db;
  private RedBlackBST<String, Poi> visitedPoi = new RedBlackBST<>();

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

  public DataBase getDb() {
    return db;
  }

  public void setDb(DataBase db) {
    this.db = db;
  }

  public RedBlackBST<String, Poi> getVisitedPoi() {
    return this.visitedPoi;
  }

  public void setVisitedPoi(RedBlackBST<String, Poi> visitedPoi) {
    this.visitedPoi = visitedPoi;
  }


  @Override
  public String toString() {
      return super.toString() + "," + this.email + "," + this.password;
  }

}