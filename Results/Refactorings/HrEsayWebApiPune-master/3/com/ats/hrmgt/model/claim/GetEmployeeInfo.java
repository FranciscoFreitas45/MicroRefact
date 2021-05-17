import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class GetEmployeeInfo {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int empId;

 private  String empCode;

 private  String companyName;

 private  int companyId;

 private  int empCatId;

 private  String empCategory;

 private  int empTypeId;

 private  String empType;

 private  int empDeptId;

 private  String empDept;

 private  String empDeptShortName;

 private  String empTypeShortName;

 private  String empCatShortName;

 private  String empFname;

 private  String empMname;

 private  String empSname;

 private  String empMobile1;

 private  String empEmail;

 private  int empPrevExpYrs;

 private  float empRatePerhr;

 private  String exVar1;


public String getEmpMname(){
    return empMname;
}


public void setEmpCatId(int empCatId){
    this.empCatId = empCatId;
}


public void setEmpType(String empType){
    this.empType = empType;
}


public void setEmpTypeShortName(String empTypeShortName){
    this.empTypeShortName = empTypeShortName;
}


public String getExVar1(){
    return exVar1;
}


public String getEmpCatShortName(){
    return empCatShortName;
}


public void setEmpSname(String empSname){
    this.empSname = empSname;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public void setCompanyName(String companyName){
    this.companyName = companyName;
}


public void setEmpDeptId(int empDeptId){
    this.empDeptId = empDeptId;
}


public String getEmpDeptShortName(){
    return empDeptShortName;
}


public String getEmpSname(){
    return empSname;
}


public void setEmpCatShortName(String empCatShortName){
    this.empCatShortName = empCatShortName;
}


public int getEmpPrevExpYrs(){
    return empPrevExpYrs;
}


public String getEmpCategory(){
    return empCategory;
}


public void setEmpTypeId(int empTypeId){
    this.empTypeId = empTypeId;
}


public void setEmpRatePerhr(float empRatePerhr){
    this.empRatePerhr = empRatePerhr;
}


public void setEmpFname(String empFname){
    this.empFname = empFname;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public String getEmpType(){
    return empType;
}


public String getEmpDept(){
    return empDept;
}


public String getEmpFname(){
    return empFname;
}


public int getEmpTypeId(){
    return empTypeId;
}


public void setEmpPrevExpYrs(int empPrevExpYrs){
    this.empPrevExpYrs = empPrevExpYrs;
}


public void setEmpDeptShortName(String empDeptShortName){
    this.empDeptShortName = empDeptShortName;
}


public int getEmpDeptId(){
    return empDeptId;
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


public void setEmpCategory(String empCategory){
    this.empCategory = empCategory;
}


public void setEmpMname(String empMname){
    this.empMname = empMname;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getEmpCode(){
    return empCode;
}


public int getCompanyId(){
    return companyId;
}


public void setEmpDept(String empDept){
    this.empDept = empDept;
}


public String getCompanyName(){
    return companyName;
}


public void setEmpEmail(String empEmail){
    this.empEmail = empEmail;
}


@Override
public String toString(){
    return "GetEmployeeInfo [empId=" + empId + ", empCode=" + empCode + ", companyName=" + companyName + ", companyId=" + companyId + ", empCatId=" + empCatId + ", empCategory=" + empCategory + ", empTypeId=" + empTypeId + ", empType=" + empType + ", empDeptId=" + empDeptId + ", empDept=" + empDept + ", empDeptShortName=" + empDeptShortName + ", empTypeShortName=" + empTypeShortName + ", empCatShortName=" + empCatShortName + ", empFname=" + empFname + ", empMname=" + empMname + ", empSname=" + empSname + ", empMobile1=" + empMobile1 + ", empEmail=" + empEmail + ", empPrevExpYrs=" + empPrevExpYrs + ", empRatePerhr=" + empRatePerhr + ", exVar1=" + exVar1 + "]";
}


public String getEmpMobile1(){
    return empMobile1;
}


public String getEmpEmail(){
    return empEmail;
}


public void setCompanyId(int companyId){
    this.companyId = companyId;
}


public float getEmpRatePerhr(){
    return empRatePerhr;
}


public String getEmpTypeShortName(){
    return empTypeShortName;
}


}