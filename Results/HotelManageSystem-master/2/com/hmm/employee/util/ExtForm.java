import java.util.List;
import java.util.Map;
import com.hmm.employee.entity.EmployeeDTO;
public class ExtForm {

 private  boolean success;

 private  String msg;

 private  EmployeeDTO data;


public void setSuccess(boolean success){
    this.success = success;
}


public String getMsg(){
    return msg;
}


public void setData(EmployeeDTO data){
    this.data = data;
}


public void setMsg(String msg){
    this.msg = msg;
}


public EmployeeDTO getData(){
    return data;
}


public boolean isSuccess(){
    return success;
}


}