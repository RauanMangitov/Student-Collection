

public class Application {
  public static void main(String[] args) {


    try{
      GlobalConfig.initGlobalConfig();
    } catch (Exception ex){
      ex.printStackTrace(System.out);
      return;
    }
    StudentFrame sf = new StudentFrame();
    sf.setVisible(true);
  }
}
