package switchtwentytwenty.project.exception;
 public class BusinessErrorMessage extends Exception{

 public  int GROUP_DESCRIPTION_NOT_FOUND;

 private  String message;

 private  int code;

// Constructor Method
public BusinessErrorMessage(String message, int code) {
    this.message = message;
    this.code = code;
}
public int getErrorCode(){
    return this.code;
}


public String getErrorMessage(){
    return this.message;
}


}