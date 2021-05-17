import java.util.List;
public class BaseModel {

 private  List<Error> errorList;


public void setErrorList(List<Error> errorList){
    this.errorList = errorList;
}


public List<Error> getErrorList(){
    return errorList;
}


}