import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class EmployeeInfo {

@Id
 private  int empId;

 private  String empCode;

 private  int companyId;

 private  int empCatId;

 private  int empTypeId;

 private  int empDeptId;

 private  int locId;

 private  String empFname;

 private  String empMname;

 private  String empSname;

 private  String empPhoto;

 private  String empMobile1;

 private  String empMobile2;

 private  String empEmail;

 private  String empAddressTemp;

 private  String empAddressPerm;

 private  String empBloodgrp;

 private  String empEmergencyPerson1;

 private  String empEmergencyNo1;

 private  String empEmergencyPerson2;

 private  String empEmergencyNo2;

 private  float empRatePerhr;

 private  String empJoiningDate;

 private  int empPrevExpYrs;

 private  int empPrevExpMonths;

 private  String empLeavingDate;

 private  String empLeavingReason;

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

 private  boolean error;


public String getEmpEmergencyPerson1(){
    return empEmergencyPerson1;
}


public String getExVar2(){
    return exVar2;
}


public String getExVar3(){
    return exVar3;
}


public String getEmpEmergencyPerson2(){
    return empEmergencyPerson2;
}


public String getEmpJoiningDate(){
    return empJoiningDate;
}


public int getLocId(){
    return locId;
}


public String getExVar1(){
    return exVar1;
}


public void setEmpLeavingDate(String empLeavingDate){
    this.empLeavingDate = empLeavingDate;
}


public String getEmpSname(){
    return empSname;
}


public void setEmpTypeId(int empTypeId){
    this.empTypeId = empTypeId;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setEmpFname(String empFname){
    this.empFname = empFname;
}


public String getEmpAddressTemp(){
    return empAddressTemp;
}


public void setExInt3(int exInt3){
    this.exInt3 = exInt3;
}


public String getEmpFname(){
    return empFname;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public int getEmpTypeId(){
    return empTypeId;
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public int getEmpDeptId(){
    return empDeptId;
}


public void setEmpJoiningDate(String empJoiningDate){
    this.empJoiningDate = empJoiningDate;
}


public void setExVar3(String exVar3){
    this.exVar3 = exVar3;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public int getEmpCatId(){
    return empCatId;
}


public void setEmpMobile1(String empMobile1){
    this.empMobile1 = empMobile1;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getEmpPhoto(){
    return empPhoto;
}


public void setEmpMobile2(String empMobile2){
    this.empMobile2 = empMobile2;
}


public String getEmpLeavingDate(){
    return empLeavingDate;
}


public void setError(boolean error){
    this.error = error;
}


public void setEmpEmail(String empEmail){
    this.empEmail = empEmail;
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


public void setEmpLeavingReason(String empLeavingReason){
    this.empLeavingReason = empLeavingReason;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt3(){
    return exInt3;
}


public String getEmpEmergencyNo2(){
    return empEmergencyNo2;
}


public int getExInt1(){
    return exInt1;
}


public void setEmpCatId(int empCatId){
    this.empCatId = empCatId;
}


public String getEmpEmergencyNo1(){
    return empEmergencyNo1;
}


public void setEmpSname(String empSname){
    this.empSname = empSname;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public void setEmpDeptId(int empDeptId){
    this.empDeptId = empDeptId;
}


public String getEmpLeavingReason(){
    return empLeavingReason;
}


public int getEmpPrevExpYrs(){
    return empPrevExpYrs;
}


public boolean isError(){
    return error;
}


public void setEmpRatePerhr(float empRatePerhr){
    this.empRatePerhr = empRatePerhr;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setMakerEnterDatetime(String makerEnterDatetime){
    this.makerEnterDatetime = makerEnterDatetime;
}


public void setEmpBloodgrp(String empBloodgrp){
    this.empBloodgrp = empBloodgrp;
}


public void setEmpPrevExpYrs(int empPrevExpYrs){
    this.empPrevExpYrs = empPrevExpYrs;
}


public void setEmpAddressTemp(String empAddressTemp){
    this.empAddressTemp = empAddressTemp;
}


public int getEmpPrevExpMonths(){
    return empPrevExpMonths;
}


public int getMakerUserId(){
    return makerUserId;
}


public void setLocId(int locId){
    this.locId = locId;
}


public void setEmpEmergencyNo2(String empEmergencyNo2){
    this.empEmergencyNo2 = empEmergencyNo2;
}


public void setEmpPhoto(String empPhoto){
    this.empPhoto = empPhoto;
}


public void setEmpEmergencyNo1(String empEmergencyNo1){
    this.empEmergencyNo1 = empEmergencyNo1;
}


public void setEmpMname(String empMname){
    this.empMname = empMname;
}


public void setEmpEmergencyPerson2(String empEmergencyPerson2){
    this.empEmergencyPerson2 = empEmergencyPerson2;
}


public int getIsActive(){
    return isActive;
}


public void setEmpPrevExpMonths(int empPrevExpMonths){
    this.empPrevExpMonths = empPrevExpMonths;
}


public String getEmpCode(){
    return empCode;
}


public void setEmpEmergencyPerson1(String empEmergencyPerson1){
    this.empEmergencyPerson1 = empEmergencyPerson1;
}


public int getDelStatus(){
    return delStatus;
}


public int getCompanyId(){
    return companyId;
}


public String getEmpBloodgrp(){
    return empBloodgrp;
}


public String getEmpAddressPerm(){
    return empAddressPerm;
}


@Override
public String toString(){
    return "EmployeeInfo [empId=" + empId + ", empCode=" + empCode + ", companyId=" + companyId + ", empCatId=" + empCatId + ", empTypeId=" + empTypeId + ", empDeptId=" + empDeptId + ", locId=" + locId + ", empFname=" + empFname + ", empMname=" + empMname + ", empSname=" + empSname + ", empPhoto=" + empPhoto + ", empMobile1=" + empMobile1 + ", empMobile2=" + empMobile2 + ", empEmail=" + empEmail + ", empAddressTemp=" + empAddressTemp + ", empAddressPerm=" + empAddressPerm + ", empBloodgrp=" + empBloodgrp + ", empEmergencyPerson1=" + empEmergencyPerson1 + ", empEmergencyNo1=" + empEmergencyNo1 + ", empEmergencyPerson2=" + empEmergencyPerson2 + ", empEmergencyNo2=" + empEmergencyNo2 + ", empRatePerhr=" + empRatePerhr + ", empJoiningDate=" + empJoiningDate + ", empPrevExpYrs=" + empPrevExpYrs + ", empPrevExpMonths=" + empPrevExpMonths + ", empLeavingDate=" + empLeavingDate + ", empLeavingReason=" + empLeavingReason + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + "]";
}


public String getEmpMobile1(){
    return empMobile1;
}


public String getEmpEmail(){
    return empEmail;
}


public void setEmpAddressPerm(String empAddressPerm){
    this.empAddressPerm = empAddressPerm;
}


public float getEmpRatePerhr(){
    return empRatePerhr;
}


public String getEmpMobile2(){
    return empMobile2;
}


}