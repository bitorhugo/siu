package main.edu.ufp.inf.en.siu;

import java.time.LocalDate;
import java.util.Objects;

public class Admin extends User {

  public Admin(String name, String address, String idNumber, LocalDate birth, String email, String password) {
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
    
  @Override
  public String toString() {
      return "Admin," + super.toString();
  }
  
  @Override
  public int hashCode() {
      return Objects.hash(super.hashCode());
  }

}