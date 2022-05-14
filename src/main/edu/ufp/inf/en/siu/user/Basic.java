package main.edu.ufp.inf.en.siu.user;

import java.time.LocalDate;
import java.util.Objects;

public class Basic extends User {
  
  public Basic(String name, String address, String idNumber, LocalDate birth) {
    super(name, address, idNumber, birth);
  }

  public Basic(String name, String address, String idNumber, LocalDate birth, String email, String password) {
    super(name, address, idNumber, birth);
    this.setEmail(email);
    this.setPassword(password);
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