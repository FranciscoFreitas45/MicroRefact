import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
public class GetLeaveStatus {

 private  int trailPkey;

 private  int leaveId;

 private  int empId;

 private  String empRemarks;

 private  int leaveStatus;

 private  int makerUserId;

 private  Date makerEnterDatetime;

 private  String empFname;

 private  String empMname;

 private  String empSname;

 private  String empPhoto;

 private  String userName;


public String getEmpMname(){
    return empMname;
}


public String getEmpSname(){
    return empSname;
}


public String getUserName(){
    return userName;
}


public String getEmpFname(){
    return empFname;
}


public int getLeaveStatus(){
    return leaveStatus;
}


// 
@JsonFormat(locale = "Locale.ENGLISH", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy hh:mm:ss a")
public Date getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public int getMakerUserId(){
    return makerUserId;
}


public String getEmpRemarks(){
    return empRemarks;
}


public int getTrailPkey(){
    return trailPkey;
}


public int getLeaveId(){
    return leaveId;
}


public int getEmpId(){
    return empId;
}


public String getEmpPhoto(){
    return empPhoto;
}


}