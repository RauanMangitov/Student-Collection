
public class StudentInfo {
  private Long studentID;
  private String nameOfStudent;
  private String surnameOfStudent;
  private String nationalityOfStudent;

  public StudentInfo(Long StudentID, String Name, String Surname,String Nationality) {
    this.studentID = StudentID;
    this.nameOfStudent = Name;
    this.surnameOfStudent = Surname;
    this.nationalityOfStudent = Nationality;
  }



  public void setStudentID(Long StudentID){
    studentID = StudentID;
  }
  public Long getStudentID(){
    return studentID;
  }
  public void setNameOfStudent(String Name) {
    nameOfStudent = Name;
  }
  public String getNameOfStudent() {
    return nameOfStudent;
  }
  public void setSurnameOfStudent(String Surname) {
    surnameOfStudent = Surname;
  }
  public String getSurnameOfStudent() {
    return surnameOfStudent;
  }
  public void setNationalityOfStudent(String Nationality){
    nationalityOfStudent = Nationality;
  }
  public String getNationalityOfStudent(){
    return nationalityOfStudent;
  }

  public String toString(){
    return "StudentID: "+studentID+" Name: "+ nameOfStudent +" Surname : " + surnameOfStudent + " Nationality: "+nationalityOfStudent;
  }
}
