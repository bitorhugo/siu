package edu.ufp.inf.en.lp2._1_intro.date;


public class Date {

  private int day;
  private int month;
  private int year;

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public Date (int day, int month, int year) {  
    // check if month is bigger than 12
    if (month > 12 ) throw new IllegalArgumentException("argument month to constructor Date() is bigger than 12");
    
    this.month = month;
    
    // check if days fit in month
    if (day > this.daysMonth()) throw new IllegalArgumentException("argument day to constructor Date() is incorrect");
    
    this.day = day;
    this.year = year;
  }

  public boolean beforeDate(Date d) {
    if (d == null) throw new IllegalArgumentException("argument to beforeDate() is null");
    return this.compareTo(d) == -1 ? true : false;
  }

  public boolean afterDate(Date d) {
    if (d == null) throw new IllegalArgumentException("argument to afterDate() is null");
    return this.compareTo(d) == 1 ? true : false;
  }

  public boolean isLeapYear() {
    if (this.year % 4 == 0) {
      if (this.year % 400 == 0) return true;
      if (this.year % 100 == 0) return false;
    }
    return false;
  }

  public void incrementDate() {
    if (this.day == this.daysMonth()) {
      if (this.month == 12) {
        this.year ++;
        this.month = 1;
        this.day = 1;
        return;
      }
      this.month ++;
      this.day = 1;
      return;
    }
    this.day ++;
  }

  public int differenceYears(Date d) {
    if (d == null) throw new IllegalArgumentException("argument to differenceYears() is null");
    
    // checks which date is older
    if (this.compareTo(d) < 0) {
      // check difference in months
      if (this.month < d.month) return Math.abs(this.year - d.year);
      else if (this.month > d.month) return Math.abs(this.year - d.year) - 1;
      // check difference in days
      else return d.day >= this.day ? Math.abs(this.year - d.year) : Math.abs(this.year - d.year) - 1;
    }
    // opposite logic
    else if (this.compareTo(d) > 0) {
      if (this.month > d.month) return Math.abs(this.year - d.year);
      else if (this.month < d.month) return Math.abs(this.year - d.year) - 1;
      else return d.day <= this.day ? Math.abs(this.year - d.year) : Math.abs(this.year - d.year) - 1;
    }
    // if year is the same return 0
    else return 0;
  }

  public int differenceMonths(Date d) {
    if (d == null) throw new IllegalArgumentException("argument to differenceMonths() is null");

    if (this.year == d.year && this.month == d.month) return 0;

    int years = (d.year - this.year) * 12;
    int months = d.month - this.month;
    int result = Math.abs(years + months);

    if (this.compareTo(d) < 0) {
      return d.day < this.day ? result - 1 : result;
    }
    else if (this.compareTo(d) > 0) {
      return d.day <= this.day ? result : result - 1;
    }
    else return 0;
  }

  public int compareTo(Date d) {
    if (d == null) throw new IllegalArgumentException("argument to compareTo() is null");

    if (this.year < d.year) return -1;
    if (this.year > d.year) return 1;

    if (this.month < d.month) return -1;
    if (this.month > d.month) return 1;
    
    if (this.day < d.day) return -1;
    if (this.day > d.day) return 1;

    return 0;
  }

  public int daysMonth() {
    switch (this.month) {
      case 1: return 31;
      case 2: return this.isLeapYear() ? 29 : 28;
      case 3: return 31;
      case 4: return 30;
      case 5: return 31;
      case 6: return 30;
      case 7: return 31;
      case 8: return 31;
      case 9: return 30;
      case 10: return 31;
      case 11: return 30;
      case 12: return 31;
      default: throw new IllegalCallerException();
    }
  }

  public int daysBetweenMonths (Date begin, Date end) {
    
    if (begin.year != end.year) throw new IllegalArgumentException("arguments year for daysBetweenMonths can't differ");
    
    
    return 0;
  }

  @Override
  public String toString() {
      return this.day + "-" + this.month + "-" + this.year;
  }

  public static void main (String args[]) {
    Date d = new Date(21, 8, 1998);

    // unit testing
    System.out.println("Is before date: " + d.beforeDate(new Date(22, 8, 1998)));
    System.out.println("Is after date: " + d.afterDate(new Date(21, 9, 1999)));
    System.out.println("Is leap year: " + d.isLeapYear());
    System.out.print("One day after " + d + " ");
    d.incrementDate();
    System.out.println(d);
    System.out.println("Difference in years: " + d.differenceYears(new Date(20, 8, 1997)));
    System.out.println("Difference in months: " + d.differenceMonths(new Date(20, 9, 2004)));
    System.out.println("Days in month: " + d.daysMonth());
  }
}