import java.io.Serializable;
public class FieldErrorVM implements Serializable{

 private  long serialVersionUID;

 private  String objectName;

 private  String field;

 private  String message;


public String getObjectName(){
    return objectName;
}


public String getField(){
    return field;
}


public String getMessage(){
    return message;
}


}