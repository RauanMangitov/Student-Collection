public class StudentAccessException extends Exception {

  public StudentAccessException(String message){
    super(message);
  }
  public StudentAccessException(Throwable cause){
    super(cause);
  }
  public StudentAccessException(String message,Throwable cause){
    super(message, cause);
  }
}
