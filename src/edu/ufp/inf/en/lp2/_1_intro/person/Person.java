package edu.ufp.inf.en.lp2._1_intro.person;
import java.time.LocalDate;

import edu.ufp.inf.en.lp2._1_intro.date.Date;


public class Person {

  private String idNumber;
  private String name;
  private String address;
  private Date birth;

  public Person (String name, String address, String idNumber, Date birth) {
    this.name = name;
    this.address = address;
    this.idNumber = idNumber;
    this.birth = birth;
  }

  public String getIdNumber() {
    return idNumber;
  }

  public void setIdNumber(String idNumber) {
    this.idNumber = idNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Date getBirth() {
    return birth;
  }

  public void setBirth(Date birth) {
    this.birth = birth;
  }

  public int age() {
    // get current date
    LocalDate currentDate = LocalDate.now();
    return this.birth.differenceYears(new Date(currentDate.getDayOfMonth(), currentDate.getMonthValue(), currentDate.getYear()));
  }

  public boolean olderThan(Person p) {
    return this.birth.beforeDate(p.birth) ? true : false;
  }

  @Override
  public String toString() {
      return this.name + "," + this.address + "," + this.idNumber + "," + this.birth;
  }

  public static void main(String[] args) {
    Person p = new Person("Hugo", "Porto", "38132", new Date(21, 8, 1998));
    Person q = new Person("Pedro", "Lisboa", "38133", new Date(8, 12, 1998));
    // unit testing
    System.out.println(p.getName() + " age: " + p.age());
    System.out.println(p.getName() + " older than " + q.getName() + ": " + p.olderThan(q));
  }
}