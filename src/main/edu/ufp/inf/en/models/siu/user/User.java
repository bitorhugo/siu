package main.edu.ufp.inf.en.models.siu.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

import edu.princeton.cs.algs4.RedBlackBST;
import main.edu.ufp.inf.en.models.lp2._1_intro.person.Person;
import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.database.poi.Poi;
import main.edu.ufp.inf.en.models.siu.database.poi.PoiNotFoundException;

/**
 * @author Vitor Hugo
 */
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
  
  /**
   * visits a point of interest
   * @param poi point of interest
   * @param entrance time of entrance
   * @throws PoiNotFoundException
   */
  public void visitPoi (Poi poi, Long entrance) throws PoiNotFoundException {
    if (poi == null) throw new IllegalArgumentException("argument 'poi' to addVisitedPoi() is null");
    if (entrance == null) throw new IllegalArgumentException("argument 'entrance' to addVisitedPoi() is null");
    // check weather poi is in database
    if (this.db.containsPoi(poi.getNodeId())) {
      Poi p = this.db.searchPoi(poi.getNodeId());
      // update visitors of p
      this.db.searchPoi(p.getNodeId()).addVisitor(this, entrance);
      // add poi to visitedPoi
      this.visitedPoi.put(entrance, p);
    }
  }

  /**
   * adds list of pois to visitPoi
   * @param pois list of pois
   * @param timestamp time user was in/on the pois
   * @throws PoiNotFoundException
   */
  public void visitPoi (List<Integer> pois, List<Long> timestamps) throws PoiNotFoundException {
    // check if for each poi we have a related timestamp
    if (pois.size() != timestamps.size())
      throw new IllegalArgumentException("lists must have the same size");

    // add to visitedPoi
    for (int i = 0; i < pois.size(); i++) {
      Poi p = this.db.searchPoi(pois.get(i));
      if (p != null) {
        p.addVisitor(this, timestamps.get(i));
        this.visitedPoi.put(timestamps.get(i), p); // add user to visitors in poi
      }
    }
    
    this.history();
  }

  /**
   * returns all point of interest visited by user
   */
  public String history () {
    String history = new String();
    if (!this.visitedPoi.isEmpty()) {
      for (Long timestamp : this.visitedPoi.keys()) {
        Integer poiID = this.visitedPoi.get(timestamp).getNodeId();
        LocalDateTime date = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC);
        history += "Poi: " + poiID + " Date visited: " + date + ";";
        System.out.println(history);
      }
    }
    return history;
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
  public int hashCode() {
    return Objects.hash(this.getName(),
                        this.getIdNumber(),
                        this.getAddress(), 
                        this.getBirth(), 
                        this.email, 
                        this.password); 
  }

  @Override
  public String toString() {
      return super.toString() + "," + this.email + "," + this.password;
  }

}