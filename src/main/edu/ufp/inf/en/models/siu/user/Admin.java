package main.edu.ufp.inf.en.models.siu.user;

import java.time.LocalDate;
import java.util.Objects;

import main.edu.ufp.inf.en.models.siu.database.node.Node;
import main.edu.ufp.inf.en.models.siu.database.node.NodeNotFoundException;
import main.edu.ufp.inf.en.models.siu.database.poi.Poi;
import main.edu.ufp.inf.en.models.siu.database.poi.PoiNotFoundException;
import main.edu.ufp.inf.en.models.siu.database.way.Way;

/**
 * @author Vitor Hugo
 */
public class Admin extends User {

  /**
   * class constructor
   * @param name name
   * @param address address
   * @param idNumber id number
   * @param birth date of birth
   * @param email email
   * @param password account password
   */
  public Admin(String name, String address, String idNumber, LocalDate birth, String email, String password) {
    super(name, address, idNumber, birth);
    this.setEmail(email);
    this.setPassword(password);
  }

  /**
   * adds a user
   * @param u user to add   
   */
  public void addUser(User u) {
    this.getDb().addUser(u);
  }

  /**
   * removes a user
   * @param u user to remove
   * @return removed user
   */
  public User removeUser(User u) {
    return this.getDb().removeUser(u);
  }
    
  /**
   * edits a user
   * @param o old user to edit
   * @param n new user to replace the old one
   */
  public void editUser(User o, User n) {
    this.getDb().editUser(o, n);
  }

  /**
   * adds a node to database
   */
  public void addNode (Node n) {
    this.getDb().addNode(n);
  }

  /**
   * removes a node from database
   * @param n node to be removed
   * @return removed node if successful || null if not 
   * @throws NodeNotFoundException
   */
  public Node removeNode (Node n) throws NodeNotFoundException {
    this.getDb().removeNode(n);
    return n;
  }

  public Node removeNode (Integer id) throws NodeNotFoundException {
    return removeNode(new Node (id, null));
  }

  /**
   * edits a node from database
   * @param o old way
   * @param n new way
   * @throws NodeNotFoundException
   */
  public void editNode(Node o, Node n) throws NodeNotFoundException {
    this.getDb().editNode(o, n);
  }

  /**
   * adds a poi to dabatase
   * @param p
   */
  public void addPoi(Poi p) {
    this.getDb().addPoi(p);
  }

  /**
   * removes a poi from database
   * @param p poi to be removed
   * @return removed poi
   * @throws PoiNotFoundException
   */
  public Poi removePoi (Poi p) throws PoiNotFoundException {
    this.getDb().removePoi(p);
    return p;
  }

  /**
   * removes a poi given its id
   * @param id poi id
   * @return removed poi
   * @throws PoiNotFoundException
   */
  public Poi removePoi(Integer id) throws PoiNotFoundException {
    return removePoi(new Poi(id, null));
  }

  /**
   * edits a poi from database
   * @param o old poi
   * @param n new poi
   * @throws PoiNotFoundException
   */
  public void editPoi(Poi o, Poi n) throws PoiNotFoundException {
    this.getDb().editPoi(o, n);
  }

  /**
   * adds a way to database
   */
  public void addWay(Way w) {
    this.getDb().addWay(w);
  }

  /**
   * removes a way from database
   * @param w way to be removed
   * @return removed way
   */
  public Way removeWay(Way w) {
    this.getDb().removeWay(w);
    return w;
  }

  /**
   * edits a way from database
   * @param o old way
   * @param n new way
   */
  public void editWay(Way o, Way n) {
    this.getDb().editWay(o, n);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (this.getClass() != obj.getClass()) return false;
    Admin u = (Admin) obj;
    return Objects.equals(this.getIdNumber(), u.getIdNumber());
  }

  @Override
  public String toString() {
      return "Admin," + super.toString();
  }
  
  @Override
  public int hashCode() {
      return Objects.hash(super.hashCode());
  }

}