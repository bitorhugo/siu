package edu.ufp.inf.en.siu;

import edu.ufp.inf.en.lp2._1_intro.date.Date;

public class Basic extends User {

  
  public Basic(String name, String address, String idNumber, Date birth) {
    super(name, address, idNumber, birth);
  }

  @Override
  public String toString() {
    return "Basic(" + super.toString() + ")";
  }

}