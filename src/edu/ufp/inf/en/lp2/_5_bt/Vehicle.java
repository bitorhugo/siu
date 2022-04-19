package edu.ufp.inf.en.lp2._5_bt;

public class Vehicle {

  private String registration;

  private String brand;

  private String model;

  private int cilinders;

  public Vehicle (String brand, String model, int cilinders, String registration) {
    this.brand = brand; 
    this.model = model;
    this.cilinders = cilinders;
    this.registration = registration;
  }

  public Vehicle() {
}

public String getRegistration() {
    return registration;
  }

  public void setRegistration(String registration) {
    this.registration = registration;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getCilinders() {
    return cilinders;
  }

  public void setCilinders(int cilinders) {
    this.cilinders = cilinders;
  }
  
}