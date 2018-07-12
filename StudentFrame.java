import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static java.lang.Long.*;

public class StudentFrame extends JFrame implements ActionListener {


  private static final String LOAD = "LOAD";
  private static final String ADD = "ADD";
  private static final String EDIT = "EDIT";
  private static final String DELETE = "DELETE";

  private final StudentManager studentManager = new StudentManager();
  private final JTable studentTable = new JTable();

  public StudentFrame() {
    studentTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(5, 5, 0, 5);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(gridbag);
    buttonPanel.add(createButton(gridbag, gbc, "Load", LOAD));
    buttonPanel.add(createButton(gridbag, gbc, "Add", ADD));
    buttonPanel.add(createButton(gridbag, gbc, "Edit", EDIT));
    buttonPanel.add(createButton(gridbag, gbc, "Delete", DELETE));

    JPanel left = new JPanel();
    left.setLayout(new BorderLayout());
    left.add(buttonPanel, BorderLayout.NORTH);
    add(left, BorderLayout.WEST);

    add(new JScrollPane(studentTable), BorderLayout.CENTER);

    setBounds(100, 200, 900, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    try {
      loadStudent();
    }catch (StudentStudyException ex){
      ex.printStackTrace();
    }
  }

  private JButton createButton(GridBagLayout gridbag, GridBagConstraints gbc, String title, String action) {
    JButton button = new JButton(title);
    button.setActionCommand(action);
    button.addActionListener(this);
    gridbag.setConstraints(button, gbc);
    return button;
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    String action = ae.getActionCommand();
    try {
      switch (action) {
        case LOAD:
          loadStudent();
          break;
        case ADD:
          addStudent();
          break;
        case EDIT:
          editStudent();
          break;
        case DELETE:
          deleteStudent();
          break;
      }
    } catch (StudentStudyException ex){
      JOptionPane.showMessageDialog(this, ex.getMessage());
    }
  }

  private void loadStudent() throws StudentStudyException {
    List<StudentInfo> students = studentManager.findStudents();
    StudentModel sm = new StudentModel(students);
    studentTable.setModel(sm);
  }

  private void addStudent() throws StudentStudyException {
    EditStudentDialog esd = new EditStudentDialog();
    saveStudent(esd);
  }

  private void editStudent() throws StudentStudyException {
    int sr = studentTable.getSelectedRow();
    if (sr != -1) {
      Long id = Long.parseLong(studentTable.getModel().getValueAt(sr, 0).toString());
      StudentInfo snt = studentManager.getStudent(id);
      EditStudentDialog esd = new EditStudentDialog(snt);
      saveStudent(esd);
    } else {
      JOptionPane.showMessageDialog(this, "You need choose a row for editing");
    }
  }

  private void deleteStudent() throws StudentStudyException {
    int sr = studentTable.getSelectedRow();
    if (sr != -1) {
      Long id = parseLong(studentTable.getModel().getValueAt(sr, 0).toString());
      studentManager.deleteStudent(id);
      loadStudent();
    } else {
      JOptionPane.showMessageDialog(this, "You need choose a row for deleting");
    }
  }

  private void saveStudent(EditStudentDialog ecd) throws StudentStudyException {

    if (ecd.isSave()) {

      StudentInfo snt = ecd.getStudent();
      if (snt.getStudentID()!=null){

        studentManager.updateStudent(snt);
      } else {
        studentManager.addStudent(snt);
      }
      loadStudent();
    }
  }
}



