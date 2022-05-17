package main.edu.ufp.inf.en.models.lp2._1_intro.person;
import java.time.LocalDate;


public class Person {

  private String idNumber;
  private String name;
  private String address;
  private LocalDate birth;

  public Person (String name, String address, String idNumber, LocalDate birth) {
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

  public LocalDate getBirth() {
    return birth;
  }

  public void setBirth(LocalDate birth) {
    this.birth = birth;
  }

  public boolean olderThan(Person p) {
    return this.birth.isBefore(p.birth) ? true : false;
  }

  @Override
  public String toString() {
      return this.name + "," + this.address + "," + this.idNumber + "," + this.birth;
  }

  public static void main(String[] args) {
    Person p = new Person("Hugo", "Porto", "38132", LocalDate.of(2001, 12, 9));
    Person q = new Person("Pedro", "Lisboa", "38133", LocalDate.of(1998, 8, 21));
    // unit testing
    System.out.println(p.getName() + " older than " + q.getName() + ": " + p.olderThan(q));
  }
}