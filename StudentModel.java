
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StudentModel extends AbstractTableModel {
  private static final String[] headers = {"student_id", "Name", "Surname", "Nationality"};
  private final List<StudentInfo> students;

  public StudentModel(List<StudentInfo> students) {
    this.students = students;
  }

  @Override
  public int getRowCount() {
    return students.size();
  }

  @Override
  public int getColumnCount() {
    return headers.length;
  }

  @Override
  public String getColumnName(int column) {
    return headers[column];
  }

  @Override
  public Object getValueAt(int row, int column) {
    StudentInfo student = students.get(row);
    switch (column) {
      case 0:
        return student.getStudentID();
      case 1:
        return student.getNameOfStudent();
      case 2:
        return student.getSurnameOfStudent();
      default:
        return student.getNationalityOfStudent();
    }
  }
}

