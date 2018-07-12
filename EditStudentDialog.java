import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditStudentDialog extends JDialog implements ActionListener {

  private static final String SAVE = "SAVE";
  private static final String CANCEL = "CANCEL";

  private static final int PAD = 10;
  private static final int W_L = 100;
  private static final int W_T = 300;
  private static final int W_B = 120;
  private static final int H_B = 25;


  private final JTextPane txtName = new JTextPane();
  private final JTextPane txtSurName = new JTextPane();
  private final JTextPane txtNationality = new JTextPane();



  private Long studentID;
  private boolean save = false;

  public EditStudentDialog(){
    this(null);
  }


  public EditStudentDialog(StudentInfo student) {
    setLayout(null);
    buildFields();
    initFields(student);
    buildButtons();
    setModal(true);
    setResizable(false);
    setBounds(300, 300, 450, 200);
    setVisible(true);

  }


  private void buildFields() {

    JLabel lblName = new JLabel("Name: ");
    lblName.setHorizontalAlignment(SwingConstants.RIGHT);
    lblName.setBounds(new Rectangle(PAD, 0 * H_B + PAD, W_L, H_B));
    add(lblName);
    txtName.setBounds(new Rectangle(W_L + 2 * PAD, 0 * H_B + PAD, W_T, H_B));
    txtName.setBorder(BorderFactory.createEtchedBorder());
    add(txtName);

    JLabel lblSurName = new JLabel("Surname: ");
    lblSurName.setHorizontalAlignment(SwingConstants.RIGHT);
    lblSurName.setBounds(new Rectangle(PAD, 1 * H_B + PAD, W_L, H_B));
    add(lblSurName);
    txtSurName.setBounds(new Rectangle(W_L + 2 * PAD, 1 * H_B + PAD, W_T, H_B));
    txtSurName.setBorder(BorderFactory.createEtchedBorder());
    add(txtSurName);

    JLabel lblNationality = new JLabel("Nationality: ");
    lblNationality.setHorizontalAlignment(SwingConstants.RIGHT);
    lblNationality.setBounds(new Rectangle(PAD, 2 * H_B + PAD, W_L, H_B));
    add(lblNationality);
    txtNationality.setBounds(new Rectangle(W_L + 2 * PAD, 2 * H_B + PAD, W_T, H_B));
    txtNationality.setBorder(BorderFactory.createEtchedBorder());
    add(txtNationality);

  }

  private void initFields(StudentInfo student) {
    if (student != null) {
      studentID = student.getStudentID();
      txtName.setText(student.getNameOfStudent());
      txtSurName.setText(student.getSurnameOfStudent());
      txtNationality.setText(student.getNationalityOfStudent());
    }
  }

  private void buildButtons() {
    JButton btnSave = new JButton("SAVE");
    btnSave.setActionCommand(SAVE);
    btnSave.addActionListener(this);
    btnSave.setBounds(new Rectangle(PAD, 5 * H_B + PAD, W_B, H_B));
    add(btnSave);

    JButton btnCancel = new JButton("CANCEL");
    btnCancel.setActionCommand(CANCEL);
    btnCancel.addActionListener(this);
    btnCancel.setBounds(new Rectangle(W_B + 2 * PAD, 5 * H_B + PAD, W_B, H_B));
    add(btnCancel);
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    String action = ae.getActionCommand();
    save = SAVE.equals(action);
    setVisible(false);
  }

  public boolean isSave() {
    return save;
  }

  public StudentInfo getStudent() {
    StudentInfo student = new StudentInfo(studentID, txtName.getText(),
            txtSurName.getText(), txtNationality.getText());
    return student;
  }

}
