package main.edu.ufp.inf.en.siu;

import java.util.Objects;

import edu.princeton.cs.algs4.RedBlackBST;
import main.edu.ufp.inf.en.lp2._1_intro.date.Date;
import main.edu.ufp.inf.en.lp2._1_intro.person.Person;

public abstract class User extends Person {

  private String email;
  private String password;
  private DataBase db;
  private RedBlackBST<TimePeriod, Poi> visitedPoi = new RedBlackBST<>();

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

  public RedBlackBST<TimePeriod, Poi> getVisitedPoi() {
    return this.visitedPoi;
  }

  public void setVisitedPoi(RedBlackBST<TimePeriod, Poi> visitedPoi) {
    this.visitedPoi = visitedPoi;
  }

  public void addVisitedPoi (Poi p, TimePeriod tp) {
    if (p == null) throw new IllegalArgumentException("argument 'p' to addVisitedPoi() is null");
    if (tp == null) throw new IllegalArgumentException("argument 'tp' to addVisitedPoi() is null");
    // check weather poi is in database
    if (this.db.getPoiST().contains(p.getNodeId())) {
      // add it to visitedPoi of user
      this.visitedPoi.put(tp, p);
      // go to poi and add user to list of visitors
      this.db.getPoiST().get(p.getNodeId()).addVisitor(this, tp);
    }
  }

  @Override
  public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (this.getClass() != obj.getClass()) return false;
      User u = (User) obj;
      return Objects.equals(this.getIdNumber(), u.getIdNumber());
  }

  @Override
  public String toString() {
      return super.toString() + "," + this.email + "," + this.password;
  }

}