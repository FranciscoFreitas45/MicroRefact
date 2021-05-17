import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;
@Entity
public class GetClaimAuthority {

@Id
 private  int caPkey;

 private  int empId;

 private  int companyId;

 private  int caIniAuthEmpId;

 private  int caFinAuthEmpId;

 private  String caRepToEmpIds;

 private  int delStatus;

 private  int isActive;

 private  int makerUserId;

 private  String makerEnterDatetime;

 private  int exInt1;

 private  int exInt2;

 private  int exInt3;

 private  String exVar1;

 private  String exVar2;

 private  String exVar3;

 private  String empFname;

 private  String empMname;

 private  String empSname;

 private  String iniEmpFname;

 private  String iniEmpMname;

 private  String iniEmpSname;

 private  String finiEmpFname;

 private  String finiEmpMname;

 private  String finiEmpSname;

 private  String empCode;

 private  String iniEmpCode;

 private  String finiEmpCode;

@Transient
 private List<String> rePortingName;


public String getExVar2(){
    return exVar2;
}


public String getExVar3(){
    return exVar3;
}


public String getExVar1(){
    return exVar1;
}


public int getCaIniAuthEmpId(){
    return caIniAuthEmpId;
}


public void setCaFinAuthEmpId(int caFinAuthEmpId){
    this.caFinAuthEmpId = caFinAuthEmpId;
}


public String getFiniEmpMname(){
    return finiEmpMname;
}


public String getEmpSname(){
    return empSname;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setEmpFname(String empFname){
    this.empFname = empFname;
}


public List<String> getRePortingName(){
    return rePortingName;
}


public void setExInt3(int exInt3){
    this.exInt3 = exInt3;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public String getEmpFname(){
    return empFname;
}


public void setIniEmpSname(String iniEmpSname){
    this.iniEmpSname = iniEmpSname;
}


public void setRePortingName(List<String> rePortingName){
    this.rePortingName = rePortingName;
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
}


public void setIniEmpMname(String iniEmpMname){
    this.iniEmpMname = iniEmpMname;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public void setExVar3(String exVar3){
    this.exVar3 = exVar3;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public int getEmpId(){
    return empId;
}


public void setCaIniAuthEmpId(int caIniAuthEmpId){
    this.caIniAuthEmpId = caIniAuthEmpId;
}


public String getFiniEmpSname(){
    return finiEmpSname;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getFiniEmpCode(){
    return finiEmpCode;
}


public String getIniEmpSname(){
    return iniEmpSname;
}


public void setFiniEmpFname(String finiEmpFname){
    this.finiEmpFname = finiEmpFname;
}


public String getIniEmpCode(){
    return iniEmpCode;
}


public void setCompanyId(int companyId){
    this.companyId = companyId;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setIsActive(int isActive){
    this.isActive = isActive;
}


public String getEmpMname(){
    return empMname;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt3(){
    return exInt3;
}


public int getExInt1(){
    return exInt1;
}


public void setIniEmpFname(String iniEmpFname){
    this.iniEmpFname = iniEmpFname;
}


public void setFiniEmpMname(String finiEmpMname){
    this.finiEmpMname = finiEmpMname;
}


public void setEmpSname(String empSname){
    this.empSname = empSname;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public String getFiniEmpFname(){
    return finiEmpFname;
}


public String getIniEmpMname(){
    return iniEmpMname;
}


public void setCaPkey(int caPkey){
    this.caPkey = caPkey;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public String getCaRepToEmpIds(){
    return caRepToEmpIds;
}


public void setMakerEnterDatetime(String makerEnterDatetime){
    this.makerEnterDatetime = makerEnterDatetime;
}


public int getCaPkey(){
    return caPkey;
}


public int getCaFinAuthEmpId(){
    return caFinAuthEmpId;
}


public void setFiniEmpCode(String finiEmpCode){
    this.finiEmpCode = finiEmpCode;
}


public int getMakerUserId(){
    return makerUserId;
}


public void setCaRepToEmpIds(String caRepToEmpIds){
    this.caRepToEmpIds = caRepToEmpIds;
}


public void setEmpMname(String empMname){
    this.empMname = empMname;
}


public int getIsActive(){
    return isActive;
}


public String getEmpCode(){
    return empCode;
}


public void setIniEmpCode(String iniEmpCode){
    this.iniEmpCode = iniEmpCode;
}


public int getDelStatus(){
    return delStatus;
}


public int getCompanyId(){
    return companyId;
}


@Override
public String toString(){
    return "GetClaimAuthority [caPkey=" + caPkey + ", empId=" + empId + ", companyId=" + companyId + ", caIniAuthEmpId=" + caIniAuthEmpId + ", caFinAuthEmpId=" + caFinAuthEmpId + ", caRepToEmpIds=" + caRepToEmpIds + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", empFname=" + empFname + ", empMname=" + empMname + ", empSname=" + empSname + ", iniEmpFname=" + iniEmpFname + ", iniEmpMname=" + iniEmpMname + ", iniEmpSname=" + iniEmpSname + ", finiEmpFname=" + finiEmpFname + ", finiEmpMname=" + finiEmpMname + ", finiEmpSname=" + finiEmpSname + ", empCode=" + empCode + ", iniEmpCode=" + iniEmpCode + ", finiEmpCode=" + finiEmpCode + ", rePortingName=" + rePortingName + "]";
}


public void setFiniEmpSname(String finiEmpSname){
    this.finiEmpSname = finiEmpSname;
}


public String getIniEmpFname(){
    return iniEmpFname;
}


}