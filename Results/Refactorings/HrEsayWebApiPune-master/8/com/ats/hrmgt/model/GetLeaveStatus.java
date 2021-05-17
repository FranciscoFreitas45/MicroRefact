import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class GetLeaveStatus {

@Id
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


public void setEmpRemarks(String empRemarks){
    this.empRemarks = empRemarks;
}


public void setEmpSname(String empSname){
    this.empSname = empSname;
}


public String getEmpSname(){
    return empSname;
}


public void setUserName(String userName){
    this.userName = userName;
}


public void setEmpFname(String empFname){
    this.empFname = empFname;
}


public void setTrailPkey(int trailPkey){
    this.trailPkey = trailPkey;
}


public String getUserName(){
    return userName;
}


public String getEmpFname(){
    return empFname;
}


public void setMakerEnterDatetime(Date makerEnterDatetime){
    this.makerEnterDatetime = makerEnterDatetime;
}


public int getLeaveStatus(){
    return leaveStatus;
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
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


public void setEmpPhoto(String empPhoto){
    this.empPhoto = empPhoto;
}


public int getEmpId(){
    return empId;
}


public void setEmpMname(String empMname){
    this.empMname = empMname;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getEmpPhoto(){
    return empPhoto;
}


public void setLeaveStatus(int leaveStatus){
    this.leaveStatus = leaveStatus;
}


public void setLeaveId(int leaveId){
    this.leaveId = leaveId;
}


@Override
public String toString(){
    return "GetLeaveStatus [trailPkey=" + trailPkey + ", leaveId=" + leaveId + ", empId=" + empId + ", empRemarks=" + empRemarks + ", leaveStatus=" + leaveStatus + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", empFname=" + empFname + ", empMname=" + empMname + ", empSname=" + empSname + ", empPhoto=" + empPhoto + ", userName=" + userName + "]";
}


}