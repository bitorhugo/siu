package edu.ufp.inf.en.lp2._3_course;

import java.util.ArrayList;

import edu.ufp.inf.en.lp2._1_intro.date.Date;
import edu.ufp.inf.en.lp2._1_intro.person.Person;



public class Student extends Person{

  private long number;
  private Date registration;
  private String email;
  private String password;
  private CourseClass courseClass;
  private ArrayList<Grade> grades;

  public Student(String name, String address, String idNumber, Date birth) {
    super(name, address, idNumber, birth);
  }
  
  

  public long getNumber() {
    return number;
  }



  public void setNumber(long number) {
    this.number = number;
  }



  public Date getRegistration() {
    return registration;
  }



  public void setRegistration(Date registration) {
    this.registration = registration;
  }



  public String getEmail() {
    return email;
  }



  public void setEmail(String email) {
    this.email = email;
  }



  public String getPassword() {
    return password;
  }



  public void setPassword(String password) {
    this.password = password;
  }



  public CourseClass getCourseClass() {
    return courseClass;
  }



  public void setCourseClass(CourseClass courseClass) {
    this.courseClass = courseClass;
  }



  public ArrayList<Grade> getGrades() {
    return grades;
  }



  public void setGrades(ArrayList<Grade> grades) {
    this.grades = grades;
  }



  public void addGrade(Grade g) {
    this.grades.add(g);
  }

  public Grade removeGrade(float g, String nd, Date d) {
    for (Grade grade : grades) {
      if (grade.getGrade() == g && grade.getDate().equals(d)) {
        
      }
    }
    return null;
  }

  public Grade changeGrade(float ng, float g, String nd, Date d) {
  return null;
  }

  public Date searchGrade(float g, String nd, Date d) {
  return null;
  }

  public float averageGrades() {
    float avg = 0;

    for (Grade grade: this.grades) {
      avg += grade.getGrade();
    }

    return avg/this.grades.size();
  }

  public Grade maxGrade() {
    Grade max = this.grades.get(0);
    
    for (Grade grade: this.grades) {
      if (max.getGrade() <= grade.getGrade()) {
        max = grade;
      }
    }
    return max;
  }

  public Grade minGrade() {
    Grade min = this.grades.get(0);
    
    for (Grade grade: this.grades) {
      if (min.getGrade() > grade.getGrade()) {
        min = grade;
      }
    }
    return min;
  }

  public void registerInCourseClass(CourseClass cc) {
    this.courseClass = cc;
  }

  public CourseClass unregisterFromCourseClass() {
    CourseClass cc = this.courseClass;
    this.courseClass = null;
    return cc;
  }
    
}