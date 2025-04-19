package student.system.Student.Management.System;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/students")
public class StudentManagementSystemApplication {

  private List<Students> studentsList = new ArrayList<>();


  public static void main(String[] args) {
    SpringApplication.run(StudentManagementSystemApplication.class, args);
  }

  public StudentManagementSystemApplication() {
    List<Studentscourses> courses1 = new ArrayList<>();
    courses1.add(new Studentscourses("Java", LocalDate.of(2025, 2, 1), LocalDate.of(2025, 3, 1)));
    studentsList.add(
        new Students("Tanaka Tarou", "Tanaka Tarou", "taro", "aaa@example.com", "Fukuoka", 25,
            "man", courses1));

    List<Studentscourses> courses2 = new ArrayList<>();
    courses2.add(
        new Studentscourses("Python", LocalDate.of(2000, 1, 11), LocalDate.of(2000, 11, 11)));
    studentsList.add(
        new Students("Yamada Hanako", "Yamada Hanako", "hana", "bbb@example.com", "Tokyo", 22,
            "woman", courses2));

    List<Studentscourses> courses3 = new ArrayList<>();
    courses3.add(
        new Studentscourses("JavaScript", LocalDate.of(2025, 3, 1), LocalDate.of(2025, 6, 1)));
    studentsList.add(
        new Students("Suzuki Ichiro", "Suzuki Ichiro", "ichi", "ccc@example.com", "Osaka", 30,
            "man", courses3));
  }


  @GetMapping
  public List<Students> getAllStudents(@RequestParam(required = false) String name) {
    // name が指定されていない、または空の場合は全学生を返す
    if (name == null || name.isEmpty()) {
      return studentsList;
    }
    return studentsList.stream()
        //空白を無視して名前が一致する学生だけをフィルタする（大文字小文字は区別しない）
        .filter(students -> students.getFullName().replaceAll("\\s", "").toLowerCase()
            .contains(name.toLowerCase()))
        // フィルタ結果をリストに変換して返す
        .collect(Collectors.toList());
  }

  @GetMapping("/courses")
  public List<Students> getStudentsByCourse(@RequestParam String courseName) {
    //受講生リストを1人ずつ順番に確認
    return studentsList.stream()
        //各受講生が持っている「コースリスト」をさらに順番に確認
        .filter(student -> student.getCourses().stream()
            //コース名が一致した場合、その受講生は検索対象
            .anyMatch(course -> course.getCourseName().equalsIgnoreCase(courseName)))
        //条件に一致する受講生たちをリストにまとめて返す
        .collect(Collectors.toList());
  }

  //新規登録
  @PostMapping
  public Students addStudents(@RequestBody Students newStudents) {
    studentsList.add(newStudents);
    return newStudents;
    //受信した受講生データをリストに追加し、登録した内容をそのまま返す
  }

  // 学生情報を更新する
  @PutMapping
  public Students updateStudents(@RequestBody Students updatedStudents) {
    // 学生リストの中から名前が一致する学生を探す
    for (int i = 0; i < studentsList.size(); i++) {
      // 名前が一致（大文字小文字は無視）
      if (studentsList.get(i).getFullName().equalsIgnoreCase(updatedStudents.getFullName())) {
        // 一致したら学生情報を更新
        studentsList.set(i, updatedStudents);
        return updatedStudents;
      }
    }
    // 一致する学生がいなかった場合、例外をスローする
    throw new RuntimeException("Student not found: " + updatedStudents.getFullName());
  }

  //削除
  @DeleteMapping
  public String deleteStudents(@RequestParam String name) {
    //名前と一致する学生をリストから削除
    boolean removed = studentsList.removeIf(
        students -> students.getFullName().replaceAll("\\s", "").equalsIgnoreCase(name));
    if (removed) {
      //削除成功時のメッセージ
      return "Deleted: " + name;
    } else {
      //削除失敗時のメッセージ
      return "No students" + name;
    }
  }
}
