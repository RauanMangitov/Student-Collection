

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StudentDBAccess implements StudentAccess {

  private static final String SELECT
          = "SELECT student_id, student_name, student_surname, student_nationality FROM jc_student ORDER BY student_name , student_surname";
  private static final String SELECT_ONE
          = "SELECT student_id, student_name, student_surname, student_nationality FROM jc_student WHERE student_id=?";
  private static final String INSERT
          = "INSERT INTO jc_student (student_name, student_surname, student_nationality) VALUES (?, ?, ?)";
  private static final String UPDATE
          = "UPDATE jc_student SET student_name=?, student_surname=?, student_nationality=? WHERE student_id=?";
  private static final String DELETE
          = "DELETE FROM jc_student WHERE student_id=?";

  private ConnectionBuilder builder = new SimpleConnectionBuilder();

  private Connection getConnection() throws SQLException {
    return builder.getConnection();
  }

  @Override
  public Long addStudent(StudentInfo student) throws StudentAccessException {
    try (Connection connection = getConnection();
         PreparedStatement pst = connection.prepareStatement(INSERT, new String[]{"student_id"})) {
      Long student_id = -1L;
      pst.setString(1, student.getNameOfStudent());
      pst.setString(2, student.getSurnameOfStudent());
      pst.setString(3, student.getNationalityOfStudent());
      pst.executeUpdate();
      ResultSet gk = pst.getGeneratedKeys();
      if (gk.next()) {
        student_id = gk.getLong("student_id");
      }
      gk.close();
      return student_id;
    } catch (Exception e) {
      throw new StudentAccessException(e);
    }
  }

  @Override
  public void updateStudent(StudentInfo student) throws StudentAccessException {
    try (Connection connection = getConnection();
         PreparedStatement pst = connection.prepareStatement(UPDATE)) {
      pst.setString(1, student.getNameOfStudent());
      pst.setString(2, student.getSurnameOfStudent());
      pst.setString(3, student.getNationalityOfStudent());
      pst.setLong(4, student.getStudentID());
      pst.executeUpdate();
    } catch (Exception e) {
      throw new StudentAccessException(e);
    }
  }

  @Override
  public void deleteStudent(Long studentID) throws StudentAccessException {
    try (Connection connection = getConnection();
         PreparedStatement pst = connection.prepareStatement(DELETE)) {
      pst.setLong(1, studentID);
      pst.executeUpdate();
    } catch (Exception e) {
      throw new StudentAccessException(e);
    }
  }

  public StudentInfo getStudent(Long studentID) throws StudentAccessException {
    StudentInfo student = null;
    try (Connection connection = getConnection()) {
      PreparedStatement pst = connection.prepareStatement(SELECT_ONE);
      pst.setLong(1, studentID);
      ResultSet rs = pst.executeQuery();
      if (rs.next()) {
        student = fillStudent(rs);
      }
      rs.close();
      pst.close();
    } catch (Exception e) {
      throw new StudentAccessException(e);
    }
    return student;
  }

  @Override
  public List<StudentInfo> findStudents() throws StudentAccessException {
    List<StudentInfo> list = new LinkedList<>();
    try (Connection connection = getConnection();
         PreparedStatement pst = connection.prepareStatement(SELECT);
         ResultSet rs = pst.executeQuery()) {
      while (rs.next()) {
        list.add(fillStudent(rs));

      }
      rs.close();
    } catch (Exception e) {
      throw new StudentAccessException(e);
    }
    return list;
  }

  private StudentInfo fillStudent(ResultSet rs) throws SQLException {

    StudentInfo student = new StudentInfo(1L,"Rauan","Mangitov","Kazakhstan");
    student.setStudentID (rs.getLong("STUDENT_ID"));
    student.setNameOfStudent(rs.getString("STUDENT_NAME"));
    student.setSurnameOfStudent(rs.getString("STUDENT_SURNAME"));
    student.setNationalityOfStudent(rs.getString("STUDENT_NATIONALITY"));
    return student;
  }
}


