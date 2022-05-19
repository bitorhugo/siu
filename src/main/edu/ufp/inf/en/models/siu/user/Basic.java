package main.edu.ufp.inf.en.models.siu.user;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Vitor Hugo
 */
public class Basic extends User {
  
  /**
   * class constructor
   * @param name name
   * @param address address
   * @param idNumber id number
   * @param birth date of birth
   */
  public Basic(String name, String address, String idNumber, LocalDate birth) {
    super(name, address, idNumber, birth);
  }

  /**
   * class constructor
   * @param name name
   * @param address address
   * @param idNumber id number
   * @param birth date of birth
   * @param email email
   * @param password account password
   */
  public Basic(String name, String address, String idNumber, LocalDate birth, String email, String password) {
    super(name, address, idNumber, birth);
    this.setEmail(email);
    this.setPassword(password);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (this.getClass() != obj.getClass()) return false;
    Basic u = (Basic) obj;
    return Objects.equals(this.getIdNumber(), u.getIdNumber());
  }

  @Override
  public String toString() {
    return "Basic," + super.toString();
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode());
  }

}