package student.system.Student.Management.System;

import java.util.List;

public class Students {


  private String fullName;
  private String furigana;
  private String nickname;
  private String email;
  private String region;
  private Integer age;
  private String gender;
  private List<Studentscourses> courses;


  public Students(String fullName, String furigana, String nickname, String email, String region,
      int age, String gender,
      List<Studentscourses> courses) {
    this.fullName = fullName;
    this.furigana = furigana;
    this.nickname = nickname;
    this.email = email;
    this.region = region;
    this.age = age;
    this.gender = gender;
    this.courses = courses;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getFullName() {
    return fullName;
  }

  public String getFurigana() {
    return furigana;
  }

  public void setFurigana(String furigana) {
    this.furigana = furigana;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public List<Studentscourses> getCourses() {
    return courses;
  }

  public void setCourses(List<Studentscourses> courses) {
    this.courses = courses;
  }
}

