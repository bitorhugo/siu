package main.edu.ufp.inf.en.siu;

import java.time.LocalDate;
import java.util.Objects;

public class Admin extends User {

  public Admin(String name, String address, String idNumber, LocalDate birth, String email, String password) {
    super(name, address, idNumber, birth);
    this.setEmail(email);
    this.setPassword(password);
  }

  /**
   * adds a user
   * @param u user to add
   * @author Vitor Hugo
   */
  public void addUser(User u) {
    this.getDb().addUser(u);
  }

  /**
   * removes a user
   * @param u user to remove
   * @return removed user
   * @author Vitor Hugo
   */
  public User removeUser(User u) {
    return this.getDb().removeUser(u);
  }
    
  /**
   * edits a user
   * @param o old user to edit
   * @param n new user to replace the old one
   * @author Vitor Hugo
   */
  public void editUser(User o, User n) {
    this.getDb().editUser(o, n);
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