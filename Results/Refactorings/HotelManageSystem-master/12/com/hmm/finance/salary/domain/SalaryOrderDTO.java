import java.util.Date;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hmm.department.entity.Department;
import com.hmm.employee.entity.Employee;
import com.hmm.employee.entity.EmployeeDTO;
public class SalaryOrderDTO {

 private  Long salaryOrderId;

 private  float basicwage;

 private  float overtimefee;

 private  float allowance;

 private  float reducemoney;

 private  Date date;

 private  String empNo;

 private  String empName;

 private  String deptName;


public float getOvertimefee(){
    return overtimefee;
}


public float getAllowance(){
    return allowance;
}


public void setSalaryOrderId(Long salaryOrderId){
    this.salaryOrderId = salaryOrderId;
}


public void setAllowance(float allowance){
    this.allowance = allowance;
}


public void entityToDto(SalaryOrder entity,SalaryOrderDTO dto){
    BeanUtils.copyProperties(entity, dto);
}


public float getReducemoney(){
    return reducemoney;
}


public void setEmpNo(String empNo){
    this.empNo = empNo;
}


public String getDeptName(){
    return deptName;
}


public Long getSalaryOrderId(){
    return salaryOrderId;
}


public String getEmpName(){
    return empName;
}


public void setReducemoney(float reducemoney){
    this.reducemoney = reducemoney;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public String getEmpNo(){
    return empNo;
}


public float getBasicwage(){
    return basicwage;
}


public void setOvertimefee(float overtimefee){
    this.overtimefee = overtimefee;
}


public void setDate(Date date){
    this.date = date;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getDate(){
    return date;
}


public void setBasicwage(float basicwage){
    this.basicwage = basicwage;
}


}