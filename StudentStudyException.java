public class StudentStudyException extends Exception{

  public StudentStudyException(){
  }
  public StudentStudyException(String message){
    super(message);
  }
  public StudentStudyException(Throwable cause){
    super(cause);
  }
  public StudentStudyException(String message, Throwable cause){
    super(message, cause);
  }
}
