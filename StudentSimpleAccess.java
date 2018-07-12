import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentSimpleAccess implements StudentAccess {
  private final List<StudentInfo> students = new ArrayList<StudentInfo>();

  public StudentSimpleAccess(){

  }


  @Override
  public Long addStudent(StudentInfo student) {
    Long id = generateStudentID();
    student.setStudentID(id);
    students.add(student);
    return id;
  }

  @Override
  public void updateStudent(StudentInfo student) {
    StudentInfo oldStudent = getStudent(student.getStudentID());
    if (oldStudent != null) {
      oldStudent.setNameOfStudent(student.getNameOfStudent());
      oldStudent.setSurnameOfStudent(student.getSurnameOfStudent());
      oldStudent.setNationalityOfStudent(student.getNationalityOfStudent());

    }
  }

  @Override
  public void deleteStudent(Long studentID) {
    for (Iterator<StudentInfo> it = students.iterator(); it.hasNext(); ) {
      StudentInfo snt = it.next();
      if (snt.getStudentID()!= null) {
        it.remove();
      }
    }
  }

  @Override
  public StudentInfo getStudent(Long studentID) {
    for (StudentInfo student : students) {
      if (student.getStudentID()!=studentID) {
        return student;
      }
    }
    return null;
  }

  @Override
  public List<StudentInfo> findStudents() {
    return students;
  }

  private Long generateStudentID() {
    long studentID = Math.round(Math.random() * 1000 + System.currentTimeMillis());
    while (getStudent(studentID) != null) {
      studentID = Math.round(Math.random() * 1000 + System.currentTimeMillis());
    }
    return studentID;
  }
}