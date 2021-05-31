import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class GetClaimHead {

@Id
 private  int caHeadId;

 private  int empId;

 private  int projectId;

 private  String projectTitle;

 private  String claimTitle;

 private  String claimFromDate;

 private  String claimToDate;

 private  float claimAmount;

 private  int claimFinalStatus;

 private  String exVar1;

 private  String empFname;

 private  String empSname;

 private  String empCode;

 private  String empDeptName;


public void setClaimFinalStatus(int claimFinalStatus){
    this.claimFinalStatus = claimFinalStatus;
}


public void setClaimTitle(String claimTitle){
    this.claimTitle = claimTitle;
}


public String getExVar1(){
    return exVar1;
}


public String getClaimToDate(){
    return claimToDate;
}


public int getProjectId(){
    return projectId;
}


public int getCaHeadId(){
    return caHeadId;
}


public void setEmpSname(String empSname){
    this.empSname = empSname;
}


public float getClaimAmount(){
    return claimAmount;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public int getClaimFinalStatus(){
    return claimFinalStatus;
}


public String getEmpSname(){
    return empSname;
}


public String getClaimFromDate(){
    return claimFromDate;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setEmpFname(String empFname){
    this.empFname = empFname;
}


public String getEmpFname(){
    return empFname;
}


public void setProjectTitle(String projectTitle){
    this.projectTitle = projectTitle;
}


public void setEmpDeptName(String empDeptName){
    this.empDeptName = empDeptName;
}


public void setCaHeadId(int caHeadId){
    this.caHeadId = caHeadId;
}


public String getProjectTitle(){
    return projectTitle;
}


public String getClaimTitle(){
    return claimTitle;
}


public void setClaimAmount(float claimAmount){
    this.claimAmount = claimAmount;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public void setClaimToDate(String claimToDate){
    this.claimToDate = claimToDate;
}


public String getEmpDeptName(){
    return empDeptName;
}


public void setClaimFromDate(String claimFromDate){
    this.claimFromDate = claimFromDate;
}


public String getEmpCode(){
    return empCode;
}


public void setProjectId(int projectId){
    this.projectId = projectId;
}


@Override
public String toString(){
    return "GetClaimHead [caHeadId=" + caHeadId + ", empId=" + empId + ", projectId=" + projectId + ", projectTitle=" + projectTitle + ", claimTitle=" + claimTitle + ", claimFromDate=" + claimFromDate + ", claimToDate=" + claimToDate + ", claimAmount=" + claimAmount + ", claimFinalStatus=" + claimFinalStatus + ", exVar1=" + exVar1 + ", empFname=" + empFname + ", empSname=" + empSname + ", empCode=" + empCode + ", empDeptName=" + empDeptName + ", getCaHeadId()=" + getCaHeadId() + ", getEmpId()=" + getEmpId() + ", getProjectId()=" + getProjectId() + ", getProjectTitle()=" + getProjectTitle() + ", getClaimTitle()=" + getClaimTitle() + ", getClaimFromDate()=" + getClaimFromDate() + ", getClaimToDate()=" + getClaimToDate() + ", getClaimAmount()=" + getClaimAmount() + ", getClaimFinalStatus()=" + getClaimFinalStatus() + ", getExVar1()=" + getExVar1() + ", getEmpFname()=" + getEmpFname() + ", getEmpSname()=" + getEmpSname() + ", getEmpCode()=" + getEmpCode() + ", getEmpDeptName()=" + getEmpDeptName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
}


}