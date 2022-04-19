package edu.ufp.inf.en.lp2._5_bt;

import java.util.ArrayList;
import java.util.List;

import edu.ufp.inf.en.lp2._1_intro.date.Date;

public class TransitPolice {

  public List<PenaltyFee> penaltyFees;

  public void addPenaltyFee(PenaltyFee penaltyFee) {
  /* {exceptions=AlreadyRegisteredMultaException}*/

  }

  public PenaltyFee removePenaltyFee(Date d, String local) {
  return null;
  }

  public ArrayList<PenaltyFee> lookupPenaltyFees(String motive) {
  return null;
  }

  public ArrayList<PenaltyFee> lookupPenaltyFees(Date startDate) {
  return null;
  }

  public int countPenaltyFees(Driver c) {
  return 0;
  }

  public ArrayList<String> allPenaltyFeesMotiveByDriver(Driver d) {
  return null;
  }

  public void seizeLicense(Driver d) {
  }

  public ArrayList<PenaltyFee> lookupPenaltyFeesDriver(Driver d, Date startDate, Date endDate) {
  return null;
  }

  public void lookupSevereAndVerySeverePenaltyFees(String motive) {
  }

}