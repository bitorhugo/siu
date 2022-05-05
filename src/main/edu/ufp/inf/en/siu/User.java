package main.edu.ufp.inf.en.siu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import edu.princeton.cs.algs4.RedBlackBST;
import main.edu.ufp.inf.en.lp2._1_intro.person.Person;

public abstract class User extends Person {

  private String email;
  private String password;
  private DataBase db;
  private RedBlackBST<Integer, ArrayList<TimePeriod>> visitedPoi = new RedBlackBST<>();

  public User(String name, String address, String idNumber, LocalDate birth) {
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

  public RedBlackBST<Integer, ArrayList<TimePeriod>> getVisitedPoi() {
    return this.visitedPoi;
  }

  public void setVisitedPoi(RedBlackBST<Integer, ArrayList<TimePeriod>> visitedPoi) {
    this.visitedPoi = visitedPoi;
  }

  public void addVisitedPoi (Poi p, TimePeriod tp) {
    if (p == null) throw new IllegalArgumentException("argument 'p' to addVisitedPoi() is null");
    if (tp == null) throw new IllegalArgumentException("argument 'tp' to addVisitedPoi() is null");
    // check weather poi is in database
    if (this.db.getPoiST().contains(p.getNodeId())) {
      // check to see if user has visited PoI before
      ArrayList<TimePeriod> timePeriods = this.visitedPoi.get(p.getNodeId());
      // if null users has not visited PoI before
      if (timePeriods == null) {
        timePeriods = new ArrayList<>();
        timePeriods.add(tp);
        this.visitedPoi.put(p.getNodeId(), timePeriods);
      }
      else {
        timePeriods.add(tp);
        this.visitedPoi.put(p.getNodeId(), timePeriods);
      }
      // update list of visitors in PoI
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