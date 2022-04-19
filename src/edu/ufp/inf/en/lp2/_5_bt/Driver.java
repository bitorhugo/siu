package edu.ufp.inf.en.lp2._5_bt;

public class Driver {

  private String name;

  private int numPenalties;

  private String driversLicense;

  private boolean isLicenseApprehended;

  private int points;

  
  public Driver(String name, String licenseNumber) {
    this.name = name;
    this.driversLicense = licenseNumber;
  }

  public String getName() {
    return name;
  }

  public int getNumPenalties() {
    return numPenalties;
  }

  public void setNumPenalties(int numPenalties) {
    this.numPenalties = numPenalties;
  }

  public String getDriversLicense() {
    return driversLicense;
  }

  public boolean isLicenseApprehended() {
    return isLicenseApprehended;
  }

  public void setLicenseApprehended(boolean isLicenseApprehended) {
    this.isLicenseApprehended = isLicenseApprehended;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  public void addPoints(int points) {
    if (!isLicenseApprehended)
      this.points += points;
  }

  public void removePoints(int point) {
    if (!isLicenseApprehended)
      this.points -= points;
    if (this.points < 0) {
      this.isLicenseApprehended = true;
      this.points = 0;
    }
  }

}