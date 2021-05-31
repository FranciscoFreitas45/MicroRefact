import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
public class EmpListForHolidayApprove {

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


public String getEmpName(){
    return empName;
}


public String getEmpCode(){
    return empCode;
}


public int getDelStatus(){
    return delStatus;
}


public String getRemark(){
    return remark;
}


public String getHolidayName(){
    return holidayName;
}


}