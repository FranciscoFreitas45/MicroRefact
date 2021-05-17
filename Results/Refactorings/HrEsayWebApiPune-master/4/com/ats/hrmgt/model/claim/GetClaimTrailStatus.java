import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class GetClaimTrailStatus {

@Id
 private  int claimTrailPkey;

 private  int claimId;

 private  int empId;

 private  String empRemarks;

 private  String empCode;

 private  String caFromDt;

 private  String caToDt;

 private  String projectTitle;

 private  int claimStatus;

 private  String claimTitle;

 private  String claimAmount;

 private  String userName;

 private  Date makerEnterDatetime;

 private  String empFname;

 private  String empMname;

 private  String empSname;


public String getEmpMname(){
    return empMname;
}


public void setClaimTitle(String claimTitle){
    this.claimTitle = claimTitle;
}


public void setEmpRemarks(String empRemarks){
    this.empRemarks = empRemarks;
}


public void setCaFromDt(String caFromDt){
    this.caFromDt = caFromDt;
}


public void setClaimTrailPkey(int claimTrailPkey){
    this.claimTrailPkey = claimTrailPkey;
}


public void setEmpSname(String empSname){
    this.empSname = empSname;
}


public String getClaimAmount(){
    return claimAmount;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public String getEmpSname(){
    return empSname;
}


public void setCaToDt(String caToDt){
    this.caToDt = caToDt;
}


public void setUserName(String userName){
    this.userName = userName;
}


public void setEmpFname(String empFname){
    this.empFname = empFname;
}


public String getCaFromDt(){
    return caFromDt;
}


public String getUserName(){
    return userName;
}


public String getCaToDt(){
    return caToDt;
}


public String getEmpFname(){
    return empFname;
}


public void setMakerEnterDatetime(Date makerEnterDatetime){
    this.makerEnterDatetime = makerEnterDatetime;
}


public void setProjectTitle(String projectTitle){
    this.projectTitle = projectTitle;
}


public int getClaimStatus(){
    return claimStatus;
}


public void setClaimStatus(int claimStatus){
    this.claimStatus = claimStatus;
}


@JsonFormat(locale = "Locale.ENGLISH", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy hh:mm:ss a")
public Date getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public String getEmpRemarks(){
    return empRemarks;
}


public String getProjectTitle(){
    return projectTitle;
}


public int getClaimId(){
    return claimId;
}


public String getClaimTitle(){
    return claimTitle;
}


public void setClaimAmount(String claimAmount){
    this.claimAmount = claimAmount;
}


public int getEmpId(){
    return empId;
}


public void setEmpMname(String empMname){
    this.empMname = empMname;
}


public int getClaimTrailPkey(){
    return claimTrailPkey;
}


public void setClaimId(int claimId){
    this.claimId = claimId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getEmpCode(){
    return empCode;
}


@Override
public String toString(){
    return "GetClaimTrailStatus [claimTrailPkey=" + claimTrailPkey + ", claimId=" + claimId + ", empId=" + empId + ", empRemarks=" + empRemarks + ", empCode=" + empCode + ", caFromDt=" + caFromDt + ", caToDt=" + caToDt + ", projectTitle=" + projectTitle + ", claimStatus=" + claimStatus + ", claimTitle=" + claimTitle + ", claimAmount=" + claimAmount + ", userName=" + userName + ", makerEnterDatetime=" + makerEnterDatetime + ", empFname=" + empFname + ", empMname=" + empMname + ", empSname=" + empSname + ", getClaimTitle()=" + getClaimTitle() + ", getClaimAmount()=" + getClaimAmount() + ", getClaimTrailPkey()=" + getClaimTrailPkey() + ", getClaimId()=" + getClaimId() + ", getEmpId()=" + getEmpId() + ", getEmpRemarks()=" + getEmpRemarks() + ", getClaimStatus()=" + getClaimStatus() + ", getUserName()=" + getUserName() + ", getMakerEnterDatetime()=" + getMakerEnterDatetime() + ", getEmpFname()=" + getEmpFname() + ", getEmpMname()=" + getEmpMname() + ", getEmpSname()=" + getEmpSname() + ", getEmpCode()=" + getEmpCode() + ", getCaFromDt()=" + getCaFromDt() + ", getCaToDt()=" + getCaToDt() + ", getProjectTitle()=" + getProjectTitle() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
}


}