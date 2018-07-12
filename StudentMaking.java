public class StudentMaking {
  public static StudentAccess getStudentAccess(){
    try {
      Class StudentAccessObject = Class.forName(GlobalConfig.getProperty("StudentAccessObject.class"));
      return (StudentAccess) StudentAccessObject.newInstance();
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex){
      ex.printStackTrace();
    }
    return null;
  }
}
