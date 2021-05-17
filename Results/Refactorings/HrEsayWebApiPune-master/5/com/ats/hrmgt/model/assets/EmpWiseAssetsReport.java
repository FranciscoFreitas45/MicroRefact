import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class EmpWiseAssetsReport {

@Id
 private  int assetTransId;

 private  int empId;

 private  int assetId;

 private  Date useFromDate;

 private  Date useToDate;

 private  String assignRemark;

 private  String returnRemark;

 private  String assignImgFile;

 private  String returnImgFile;

 private  String empCode;

 private  String firstName;

 private  String surname;

 private  String deptName;

 private  String empDesgn;

 private  String assetCode;

 private  String assetName;

 private  String locName;


public String getAssignRemark(){
    return assignRemark;
}


public String getEmpDesgn(){
    return empDesgn;
}


public String getLocName(){
    return locName;
}


public void setReturnImgFile(String returnImgFile){
    this.returnImgFile = returnImgFile;
}


public void setLocName(String locName){
    this.locName = locName;
}


public String getAssetCode(){
    return assetCode;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public void setReturnRemark(String returnRemark){
    this.returnRemark = returnRemark;
}


public void setAssetId(int assetId){
    this.assetId = assetId;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getUseFromDate(){
    return useFromDate;
}


public void setSurname(String surname){
    this.surname = surname;
}


public String getAssignImgFile(){
    return assignImgFile;
}


public void setAssignRemark(String assignRemark){
    this.assignRemark = assignRemark;
}


public void setAssetCode(String assetCode){
    this.assetCode = assetCode;
}


public int getAssetTransId(){
    return assetTransId;
}


public void setAssignImgFile(String assignImgFile){
    this.assignImgFile = assignImgFile;
}


public int getEmpId(){
    return empId;
}


public String getDeptName(){
    return deptName;
}


public void setEmpDesgn(String empDesgn){
    this.empDesgn = empDesgn;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public String getEmpCode(){
    return empCode;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public String getAssetName(){
    return assetName;
}


public void setUseToDate(Date useToDate){
    this.useToDate = useToDate;
}


public String getReturnImgFile(){
    return returnImgFile;
}


@Override
public String toString(){
    return "EmpWiseAssetsReport [assetTransId=" + assetTransId + ", empId=" + empId + ", assetId=" + assetId + ", useFromDate=" + useFromDate + ", useToDate=" + useToDate + ", assignRemark=" + assignRemark + ", returnRemark=" + returnRemark + ", assignImgFile=" + assignImgFile + ", returnImgFile=" + returnImgFile + ", empCode=" + empCode + ", firstName=" + firstName + ", surname=" + surname + ", deptName=" + deptName + ", empDesgn=" + empDesgn + ", assetCode=" + assetCode + ", assetName=" + assetName + ", locName=" + locName + "]";
}


public String getFirstName(){
    return firstName;
}


public void setAssetName(String assetName){
    this.assetName = assetName;
}


public void setAssetTransId(int assetTransId){
    this.assetTransId = assetTransId;
}


public int getAssetId(){
    return assetId;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getUseToDate(){
    return useToDate;
}


public void setUseFromDate(Date useFromDate){
    this.useFromDate = useFromDate;
}


public String getReturnRemark(){
    return returnRemark;
}


public String getSurname(){
    return surname;
}


}