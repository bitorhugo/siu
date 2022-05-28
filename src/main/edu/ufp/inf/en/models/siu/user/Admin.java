package main.edu.ufp.inf.en.models.siu.user;

import java.time.LocalDate;
import java.util.Objects;

import main.edu.ufp.inf.en.models.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.models.siu.database.node.Node;
import main.edu.ufp.inf.en.models.siu.database.node.NodeAlreadyExistsException;
import main.edu.ufp.inf.en.models.siu.database.node.NodeNotFoundException;
import main.edu.ufp.inf.en.models.siu.database.poi.Poi;
import main.edu.ufp.inf.en.models.siu.database.poi.PoiAlreadyExistsException;
import main.edu.ufp.inf.en.models.siu.database.poi.PoiNotFoundException;
import main.edu.ufp.inf.en.models.siu.database.way.Way;
import main.edu.ufp.inf.en.models.siu.database.way.WayAlreadyExistsException;
import main.edu.ufp.inf.en.models.siu.database.way.WayNotFoundException;

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
  public void addUser(User u) throws UserAlreadyExistsException{
    this.getDb().addUser(u);
  }

  /**
   * removes a user
   * @param u user to remove
   * @return removed user
   */
  public User removeUser(User u) throws UserNotFoundException{
    return this.getDb().removeUser(u);
  }
    
  /**
   * edits a user from database
   * @param id id of user to be edited
   * @param name new name
   * @param address new address
   * @param birth new birth
   * @throws UserNotFoundException if {@code userID} is not present in database
   */
  public void editUser(String userID, String name, String address, LocalDate birth) throws UserNotFoundException{
    this.getDb().editUser(userID, name, address, birth);
  }

  /**
   * adds a node to database
   * @param n node to add
   * @throws NodeAlreadyExistsException if database already contains {@code n}
   */
  public void addNode (Node n) throws NodeAlreadyExistsException {
    this.getDb().addNode(n);
  }

  /**
   * removes a node from database
   * @param n node to be removed
   * @return removed node 
   * @throws NodeNotFoundException
   */
  public Node removeNode (Node n) throws NodeNotFoundException {
    this.getDb().removeNode(n);
    return n;
  }

  /**
   * removes a node from database
   * @param id id of node to remove
   * @return removed node
   * @throws NodeNotFoundException if {@code n} is not found
   */
  public Node removeNode (Integer id) throws NodeNotFoundException {
    return removeNode(new Node (id, null));
  }

  /**
   * edits a node from database
   * @param o old way
   * @param n new way
   * @throws NodeNotFoundException
   */
  public void editNode(Integer nodeID, Point coordinates) throws NodeNotFoundException {
    this.getDb().editNode(nodeID, coordinates);
  }

  /**
   * adds a poi to dabatase
   * @param p
   * @throws PoiAlreadyExistsException
   */
  public void addPoi(Poi p) throws PoiAlreadyExistsException {
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
  public void editPoi(Integer poiID, Point coordinates) throws PoiNotFoundException {
    this.getDb().editPoi(poiID, coordinates);
  }

  /**
   * adds a way to database
   * @throws WayAlreadyExistsException
   */
  public void addWay(Way w) throws WayAlreadyExistsException {
    this.getDb().addWay(w);
  }

  /**
   * removes a way from database
   * @param w way to be removed
   * @return removed way
   * @throws WayNotFoundException
   */
  public Way removeWay(Way w) throws WayNotFoundException {
    this.getDb().removeWay(w);
    return w;
  }

  /**
   * edits a way from database
   * @param o old way
   * @param n new way
   * @throws WayNotFoundException
   */
  public void editWay(Integer wayID, Node origin, Node destination, double weight) throws WayNotFoundException {
    this.getDb().editWay(wayID, origin, destination, weight);
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