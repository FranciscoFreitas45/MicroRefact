import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class GetClaimApplyAuthwise {

@Id
 private  int caHeadId;

 private  int empId;

 private  String empCode;

 private  String empName;

 private  int projId;

 private  String claimTitle;

 private  String projectTitle;

 private  String caFromDt;

 private  String caToDt;

 private  int claimStatus;

 private  float claimAmount;

 private  String circulatedTo;

 private  String exVar1;

 private  int caIniAuthEmpId;

 private  int caFinAuthEmpId;

 private  String empPhoto;


public void setClaimTitle(String claimTitle){
    this.claimTitle = claimTitle;
}


public String getExVar1(){
    return exVar1;
}


public int getCaIniAuthEmpId(){
    return caIniAuthEmpId;
}


public int getCaHeadId(){
    return caHeadId;
}


public void setCaFromDt(String caFromDt){
    this.caFromDt = caFromDt;
}


public void setCaFinAuthEmpId(int caFinAuthEmpId){
    this.caFinAuthEmpId = caFinAuthEmpId;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public float getClaimAmount(){
    return claimAmount;
}


public String getEmpName(){
    return empName;
}


public void setCaToDt(String caToDt){
    this.caToDt = caToDt;
}


public String getCirculatedTo(){
    return circulatedTo;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public void setCirculatedTo(String circulatedTo){
    this.circulatedTo = circulatedTo;
}


public String getCaFromDt(){
    return caFromDt;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public String getCaToDt(){
    return caToDt;
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


public int getCaFinAuthEmpId(){
    return caFinAuthEmpId;
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


public void setEmpPhoto(String empPhoto){
    this.empPhoto = empPhoto;
}


public int getEmpId(){
    return empId;
}


public void setCaIniAuthEmpId(int caIniAuthEmpId){
    this.caIniAuthEmpId = caIniAuthEmpId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getEmpPhoto(){
    return empPhoto;
}


public String getEmpCode(){
    return empCode;
}


@Override
public String toString(){
    return "GetClaimApplyAuthwise [caHeadId=" + caHeadId + ", empId=" + empId + ", empCode=" + empCode + ", empName=" + empName + ", projId=" + projId + ", claimTitle=" + claimTitle + ", projectTitle=" + projectTitle + ", caFromDt=" + caFromDt + ", caToDt=" + caToDt + ", claimStatus=" + claimStatus + ", claimAmount=" + claimAmount + ", circulatedTo=" + circulatedTo + ", exVar1=" + exVar1 + ", caIniAuthEmpId=" + caIniAuthEmpId + ", caFinAuthEmpId=" + caFinAuthEmpId + ", empPhoto=" + empPhoto + ", getCaHeadId()=" + getCaHeadId() + ", getEmpId()=" + getEmpId() + ", getEmpCode()=" + getEmpCode() + ", getEmpName()=" + getEmpName() + ", getProjId()=" + getProjId() + ", getClaimTitle()=" + getClaimTitle() + ", getProjectTitle()=" + getProjectTitle() + ", getCaFromDt()=" + getCaFromDt() + ", getCaToDt()=" + getCaToDt() + ", getClaimStatus()=" + getClaimStatus() + ", getClaimAmount()=" + getClaimAmount() + ", getCirculatedTo()=" + getCirculatedTo() + ", getExVar1()=" + getExVar1() + ", getCaIniAuthEmpId()=" + getCaIniAuthEmpId() + ", getCaFinAuthEmpId()=" + getCaFinAuthEmpId() + ", getEmpPhoto()=" + getEmpPhoto() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
}


public int getProjId(){
    return projId;
}


public void setProjId(int projId){
    this.projId = projId;
}


}