package edu.ufp.inf.en.lp2._3_course;

import edu.ufp.inf.en.lp2._1_intro.date.Date;



public class Grade {

  private float grade;
  private Date date;
  private Discipline discipline;
  private Student student;

  

  public float getGrade() {
    return grade;
  }

  public void setGrade(float grade) {
    this.grade = grade;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Discipline getDiscipline() {
    return discipline;
  }

  public void setDiscipline(Discipline discipline) {
    this.discipline = discipline;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Discipline associatedDiscipline() {
    return this.discipline;
  }

  public void associateDiscipline(Discipline d) {
    this.discipline = d;
  }

  public Student associatedStudent() {
    return this.student;
  }

  public void associateStudent(Student s) {
    this.student = s;
  }

}