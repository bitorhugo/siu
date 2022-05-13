package main.edu.ufp.inf.en.siu.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

import edu.princeton.cs.algs4.RedBlackBST;
import main.edu.ufp.inf.en.lp2._1_intro.person.Person;
import main.edu.ufp.inf.en.siu.database.DataBase;
import main.edu.ufp.inf.en.siu.database.Poi;

public abstract class User extends Person {

  private String email;
  private String password;
  private DataBase db;
  private RedBlackBST<Long, Poi> visitedPoi = new RedBlackBST<>();

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
  public RedBlackBST<Long, Poi> getVisitedPoi() {
    return visitedPoi;
  }
  public void setVisitedPoi(RedBlackBST<Long, Poi> visitedPoi) {
    this.visitedPoi = visitedPoi;
  }
  
  public void addVisitedPoi (Poi poi, Long entrance) {
    if (poi == null) throw new IllegalArgumentException("argument 'poi' to addVisitedPoi() is null");
    if (entrance == null) throw new IllegalArgumentException("argument 'entrance' to addVisitedPoi() is null");
    // check weather poi is in database
    if (this.db.getPoiST().contains(poi.getNodeId())) {
      Poi p = this.db.searchPoi(poi.getNodeId());
      // update visitors of p
      this.db.searchPoi(p.getNodeId()).addVisitor(this, entrance);
      // add poi to visitedPoi
      this.visitedPoi.put(entrance, p);
    }
  }

  public void history () {
    if (!this.visitedPoi.isEmpty()) {
      for (Long timestamp : this.visitedPoi.keys()) {
        System.out.println("Poi " + this.visitedPoi.get(timestamp).getNodeId()
                            + " Date visited: " + LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC));
      }
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