package student.system.Student.Management.System;

import java.time.LocalDate;

public class Studentscourses {

  private String courseName;
  private LocalDate startDate;
  private LocalDate endDate;

  public Studentscourses(String courseName, LocalDate startDate, LocalDate endDate) {
    this.courseName = courseName;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;

  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }


  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

}

