import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class EmpJsonData {

@Id
 private  int empId;

 private  String empCode;

 private  String cmpCode;

 private  int empType;

 private  int departId;

 private  int designationId;

 private  int locationId;

 private  String firstName;

 private  String middleName;

 private  String surname;

 private  int isEmp;

 private  int currentShiftid;

 private  String empCategory;

 private  int salaryTypeId;

 private  String salBasis;

 private  String cmpJoiningDate;

 private  int holidayCatId;

 private  int weekEndCatId;

 private  String dailyHr;

 private  String monthlyHrTarget;

 private  String monthlyMinimumTarget;

 private  String monthlyOtHr;


public int getLocationId(){
    return locationId;
}


public void setCurrentShiftid(int currentShiftid){
    this.currentShiftid = currentShiftid;
}


public String getCmpJoiningDate(){
    return cmpJoiningDate;
}


public void setCmpCode(String cmpCode){
    this.cmpCode = cmpCode;
}


public void setEmpType(int empType){
    this.empType = empType;
}


public void setSalaryTypeId(int salaryTypeId){
    this.salaryTypeId = salaryTypeId;
}


public int getDesignationId(){
    return designationId;
}


public int getSalaryTypeId(){
    return salaryTypeId;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public String getMiddleName(){
    return middleName;
}


public String getMonthlyHrTarget(){
    return monthlyHrTarget;
}


public int getHolidayCatId(){
    return holidayCatId;
}


public int getIsEmp(){
    return isEmp;
}


public String getEmpCategory(){
    return empCategory;
}


public void setSalBasis(String salBasis){
    this.salBasis = salBasis;
}


public void setIsEmp(int isEmp){
    this.isEmp = isEmp;
}


public void setSurname(String surname){
    this.surname = surname;
}


public int getEmpType(){
    return empType;
}


public void setMiddleName(String middleName){
    this.middleName = middleName;
}


public void setMonthlyMinimumTarget(String monthlyMinimumTarget){
    this.monthlyMinimumTarget = monthlyMinimumTarget;
}


public String getMonthlyOtHr(){
    return monthlyOtHr;
}


public void setCmpJoiningDate(String cmpJoiningDate){
    this.cmpJoiningDate = cmpJoiningDate;
}


public int getCurrentShiftid(){
    return currentShiftid;
}


public String getMonthlyMinimumTarget(){
    return monthlyMinimumTarget;
}


public int getEmpId(){
    return empId;
}


public void setEmpCategory(String empCategory){
    this.empCategory = empCategory;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getSalBasis(){
    return salBasis;
}


public void setDailyHr(String dailyHr){
    this.dailyHr = dailyHr;
}


public String getDailyHr(){
    return dailyHr;
}


public String getEmpCode(){
    return empCode;
}


public void setWeekEndCatId(int weekEndCatId){
    this.weekEndCatId = weekEndCatId;
}


public void setMonthlyHrTarget(String monthlyHrTarget){
    this.monthlyHrTarget = monthlyHrTarget;
}


public int getDepartId(){
    return departId;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public int getWeekEndCatId(){
    return weekEndCatId;
}


public void setDepartId(int departId){
    this.departId = departId;
}


public void setLocationId(int locationId){
    this.locationId = locationId;
}


public void setDesignationId(int designationId){
    this.designationId = designationId;
}


@Override
public String toString(){
    return "EmpJsonData [empId=" + empId + ", empCode=" + empCode + ", cmpCode=" + cmpCode + ", empType=" + empType + ", departId=" + departId + ", designationId=" + designationId + ", locationId=" + locationId + ", firstName=" + firstName + ", middleName=" + middleName + ", surname=" + surname + ", isEmp=" + isEmp + ", currentShiftid=" + currentShiftid + ", empCategory=" + empCategory + ", salaryTypeId=" + salaryTypeId + ", salBasis=" + salBasis + ", cmpJoiningDate=" + cmpJoiningDate + ", holidayCatId=" + holidayCatId + ", weekEndCatId=" + weekEndCatId + ", dailyHr=" + dailyHr + ", monthlyHrTarget=" + monthlyHrTarget + ", monthlyMinimumTarget=" + monthlyMinimumTarget + ", monthlyOtHr=" + monthlyOtHr + "]";
}


public void setHolidayCatId(int holidayCatId){
    this.holidayCatId = holidayCatId;
}


public void setMonthlyOtHr(String monthlyOtHr){
    this.monthlyOtHr = monthlyOtHr;
}


public String getCmpCode(){
    return cmpCode;
}


public String getFirstName(){
    return firstName;
}


public String getSurname(){
    return surname;
}


}