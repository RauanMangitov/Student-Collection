import java.util.List;

public interface StudentAccess {

  Long addStudent (StudentInfo student) throws StudentAccessException;

  void updateStudent (StudentInfo student) throws StudentAccessException;

  void deleteStudent (Long studentID) throws StudentAccessException;

  StudentInfo getStudent (Long studentID) throws StudentAccessException;

  List<StudentInfo> findStudents() throws StudentAccessException;

}
