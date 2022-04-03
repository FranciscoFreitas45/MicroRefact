package com.hmm.employee.util;
 import java.util.List;
import java.util.Map;
import com.hmm.employee.entity.EmployeeDTO;
public class ExtForm {

 private  boolean success;

 private  String msg;

 private  EmployeeDTO data;

public ExtForm() {
}public ExtForm(boolean success) {
    this.success = success;
}public ExtForm(boolean success, String msg) {
    this.success = success;
    this.msg = msg;
}public ExtForm(boolean success, EmployeeDTO employeeDTOs) {
    this.success = success;
    this.setData(employeeDTOs);
}
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