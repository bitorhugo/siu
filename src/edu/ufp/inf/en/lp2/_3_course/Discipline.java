package edu.ufp.inf.en.lp2._3_course;

import java.util.ArrayList;




public class Discipline {

  private String name;

  private int ects;

  private short courseSemester;

  private short courseYear;


  

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getEcts() {
    return ects;
  }

  public void setEcts(int ects) {
    this.ects = ects;
  }

  public short getCourseSemester() {
    return courseSemester;
  }

  public void setCourseSemester(short courseSemester) {
    this.courseSemester = courseSemester;
  }

  public short getCourseYear() {
    return courseYear;
  }

  public void setCourseYear(short courseYear) {
    this.courseYear = courseYear;
  }

  public ArrayList<Grade> getGrade() {
    return grade;
  }

  public void setGrade(ArrayList<Grade> grade) {
    this.grade = grade;
  }

  private ArrayList<Grade> grade;
  
  public void associateGrade(Grade g) {
    this.grade.add(g);
  }

}