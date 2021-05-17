import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class EmpListForHolidayApprove {

@Id
 private  int id;

 private  int empId;

 private  int delStatus;

 private  int holidayId;

 private  int status;

 private  Date holidate;

 private  String remark;

 private  String empCode;

 private  String empName;

 private  String holidayName;


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getHolidate(){
    return holidate;
}


public int getHolidayId(){
    return holidayId;
}


public int getId(){
    return id;
}


public int getStatus(){
    return status;
}


public int getEmpId(){
    return empId;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public void setStatus(int status){
    this.status = status;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getEmpName(){
    return empName;
}


public String getEmpCode(){
    return empCode;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public int getDelStatus(){
    return delStatus;
}


public void setRemark(String remark){
    this.remark = remark;
}


public void setHolidate(Date holidate){
    this.holidate = holidate;
}


public String getRemark(){
    return remark;
}


public void setHolidayName(String holidayName){
    this.holidayName = holidayName;
}


public void setId(int id){
    this.id = id;
}


public String getHolidayName(){
    return holidayName;
}


@Override
public String toString(){
    return "EmpListForHolidayApprove [id=" + id + ", empId=" + empId + ", delStatus=" + delStatus + ", holidayId=" + holidayId + ", status=" + status + ", holidate=" + holidate + ", remark=" + remark + ", empCode=" + empCode + ", empName=" + empName + ", holidayName=" + holidayName + "]";
}


public void setHolidayId(int holidayId){
    this.holidayId = holidayId;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


}