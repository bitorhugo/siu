package edu.ufp.inf.en.siu;

import java.util.Objects;
import edu.ufp.inf.en.lp2._1_intro.date.Date;

public class Basic extends User {

  
  public Basic(String name, String address, String idNumber, Date birth) {
    super(name, address, idNumber, birth);
  }

  public Basic(String name, String address, String idNumber, Date birth, String email, String password) {
    super(name, address, idNumber, birth);
    this.setEmail(email);
    this.setPassword(password);
  }

  @Override
  public String toString() {
    return "Basic(" + super.toString() + ")";
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode());
  }

}