import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class GetEmpDetailForFullPayslip {

@Id
 private  int empId;

 private  String pfNo;

 private  String esicNo;

 private  String aadharNo;

 private  String uan;

 private  Date cmpJoiningDate;

 private  String panCardNo;

 private  double remLoanAmt;

 private  String accNo;

 private  String bankName;

 private  String branchName;

 private  String locName;


public void setEsicNo(String esicNo){
    this.esicNo = esicNo;
}


// 
@JsonFormat(locale = "ENGLISH", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getCmpJoiningDate(){
    return cmpJoiningDate;
}


public String getLocName(){
    return locName;
}


public String getPanCardNo(){
    return panCardNo;
}


public void setRemLoanAmt(double remLoanAmt){
    this.remLoanAmt = remLoanAmt;
}


public void setBranchName(String branchName){
    this.branchName = branchName;
}


public void setLocName(String locName){
    this.locName = locName;
}


public String getPfNo(){
    return pfNo;
}


public String getAccNo(){
    return accNo;
}


public void setAccNo(String accNo){
    this.accNo = accNo;
}


public void setAadharNo(String aadharNo){
    this.aadharNo = aadharNo;
}


public double getRemLoanAmt(){
    return remLoanAmt;
}


public void setPfNo(String pfNo){
    this.pfNo = pfNo;
}


public String getAadharNo(){
    return aadharNo;
}


public String getBankName(){
    return bankName;
}


public void setUan(String uan){
    this.uan = uan;
}


public String getUan(){
    return uan;
}


public String getBranchName(){
    return branchName;
}


public String getEsicNo(){
    return esicNo;
}


public void setCmpJoiningDate(Date cmpJoiningDate){
    this.cmpJoiningDate = cmpJoiningDate;
}


public void setBankName(String bankName){
    this.bankName = bankName;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


@Override
public String toString(){
    return "GetEmpDetailForFullPayslip [empId=" + empId + ", pfNo=" + pfNo + ", esicNo=" + esicNo + ", aadharNo=" + aadharNo + ", uan=" + uan + ", cmpJoiningDate=" + cmpJoiningDate + ", panCardNo=" + panCardNo + ", remLoanAmt=" + remLoanAmt + ", accNo=" + accNo + ", bankName=" + bankName + ", branchName=" + branchName + ", locName=" + locName + "]";
}


public void setPanCardNo(String panCardNo){
    this.panCardNo = panCardNo;
}


}