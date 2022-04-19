package edu.ufp.inf.en.lp2._5_bt;

import edu.ufp.inf.en.lp2._1_intro.date.Date;

public abstract class PenaltyFee implements Comparable<PenaltyFee>, Offense {

  public static float MIN_VALUE = 20.0f;

  public static int MIN_PUNISHMENT = 15;

  private String motive;

  private String local;

  private Date date;

  private Vehicle vehicle;
  
  private Driver driver;
  
  public PenaltyFee (Date date, String motive, String local) {

  }

  public PenaltyFee (Date date, String motive, String local, Driver driver, Vehicle vehicle) {

  }

  

  public String getMotive() {
    return motive;
  }

  public void setMotive(String motive) {
    this.motive = motive;
  }

  public String getLocal() {
    return local;
  }

  public void setLocal(String local) {
    this.local = local;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  public Driver getDriver() {
    return driver;
  }

  public void setDriver(Driver driver) {
    this.driver = driver;
  }

  @Override
  public boolean equals(Object pf) {
    
    
    
    return true;
  }



}