package edu.ufp.inf.en.lp2._3_course;

import java.util.ArrayList;


public class CourseClass {

  private String name;
  private ArrayList<Student> students;
  private ArrayList<Discipline> disciplines;

  

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArrayList<Student> getStudents() {
    return students;
  }

  public void setStudents(ArrayList<Student> students) {
    this.students = students;
  }

  public ArrayList<Discipline> getDisciplines() {
    return disciplines;
  }

  public void setDisciplines(ArrayList<Discipline> disciplines) {
    this.disciplines = disciplines;
  }

  public ArrayList<Student> searchStudent(String name) {
    ArrayList<Student> s = new ArrayList<>();
    for (Student student : students) {
      if (student.getName() == name) {
        s.add(student);
      }
    }
    return s;
  }

  public Student searchStudent(long number) {
    for (Student student : students) {
      if (student.getNumber() == number) {
        return student;
      }
    }
    return null;
  }

  public Discipline searchDiscipline(String name) {
    for (Discipline discipline : disciplines) {
      if (discipline.getName() == name) {
        return discipline;
      }
    }
    return null;
  }

  public void registerStudent(Student s) {
    this.students.add(s);
  }

  public Student unregisterStudent(String name) {
    Student s;
    for (Student student : students) {
      if (student.getName() == name) {
        s = student;
        students.remove(s);
        return s;
      }
    }
    return null;
  }

  public void associateDiscipline(Discipline d) {
    if (d == null) throw new IllegalArgumentException("argument to associateDiscipline() is null");
    disciplines.add(d);
  }

  public Discipline desassociateDiscipline(String name) {
    Discipline d;
    for (Discipline discipline : disciplines) {
      if (discipline.getName() == name) {
        d = discipline;
        disciplines.remove(d);
        return d;
      }
    }
    return null;
  }

  public void launchGrade(String discName, long studNum, float grade) {
    Grade g = new Grade();
    g.setGrade(grade);
    this.searchDiscipline(discName).associateGrade(g);
  }

  public void associateGrade2Discipline(String discName, long studNum, float grade) {
    
  }

}