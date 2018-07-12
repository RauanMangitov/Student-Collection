import java.util.List;

public class StudentManager {
  private final StudentAccess StudentAccessObject;

  public StudentManager() {
    StudentAccessObject = StudentMaking.getStudentAccess();
  }

  public Long addStudent(StudentInfo student) throws StudentStudyException {
    try {
      return StudentAccessObject.addStudent(student);
    } catch (StudentAccessException ex) {
      throw new StudentStudyException(ex);
    }
  }
  public void updateStudent(StudentInfo student) throws StudentStudyException {
    try {
      StudentAccessObject.updateStudent(student);
    }catch (StudentAccessException ex){
      throw new StudentStudyException(ex);
    }
  }

  public void deleteStudent(Long studentID) throws StudentStudyException {
    try {
      StudentAccessObject.deleteStudent(studentID);
    } catch(StudentAccessException ex){
      throw new StudentStudyException(ex);
    }
  }

  public StudentInfo getStudent(Long studentID) throws StudentStudyException {
    try {
      return StudentAccessObject.getStudent(studentID);
    }catch (StudentAccessException ex){
      throw new StudentStudyException(ex);
    }
  }

  public List<StudentInfo> findStudents() throws StudentStudyException {
    try {
      return StudentAccessObject.findStudents();
    }catch (StudentAccessException ex){
      throw new StudentStudyException(ex);
    }
  }
}


