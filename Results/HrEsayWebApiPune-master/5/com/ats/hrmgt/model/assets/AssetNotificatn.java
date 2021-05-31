import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class AssetNotificatn {

@Id
 private  int assetId;

 private  String assetCode;

 private  String assetTransId;

 private  String assetCatId;

 private  String catName;

 private  String assetName;

 private  Date useToDate;

 private  Date useFromDate;

 private  int returnNotifctnDays;

 private  int returtnInDays;

 private  String empCode;

 private  int alarmDays;

 private  String firstName;

 private  String surname;

 private  String deptName;

 private  String empDesgn;

@Column(name = "mobile_no_1")
 private  String mobileNo1;

 private  String emailId;


public void setAssetCatId(String assetCatId){
    this.assetCatId = assetCatId;
}


public void setReturnNotifctnDays(int returnNotifctnDays){
    this.returnNotifctnDays = returnNotifctnDays;
}


public String getEmpDesgn(){
    return empDesgn;
}


public void setEmailId(String emailId){
    this.emailId = emailId;
}


public String getEmailId(){
    return emailId;
}


public String getAssetCode(){
    return assetCode;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public int getReturtnInDays(){
    return returtnInDays;
}


public void setAssetId(int assetId){
    this.assetId = assetId;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getUseFromDate(){
    return useFromDate;
}


public void setAlarmDays(int alarmDays){
    this.alarmDays = alarmDays;
}


public void setSurname(String surname){
    this.surname = surname;
}


public void setAssetCode(String assetCode){
    this.assetCode = assetCode;
}


public int getReturnNotifctnDays(){
    return returnNotifctnDays;
}


public String getAssetCatId(){
    return assetCatId;
}


public String getCatName(){
    return catName;
}


public String getAssetTransId(){
    return assetTransId;
}


public void setCatName(String catName){
    this.catName = catName;
}


public int getAlarmDays(){
    return alarmDays;
}


public String getDeptName(){
    return deptName;
}


public void setEmpDesgn(String empDesgn){
    this.empDesgn = empDesgn;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public String getEmpCode(){
    return empCode;
}


public void setReturtnInDays(int returtnInDays){
    this.returtnInDays = returtnInDays;
}


public String getAssetName(){
    return assetName;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public void setUseToDate(Date useToDate){
    this.useToDate = useToDate;
}


@Override
public String toString(){
    return "AssetNotificatn [assetId=" + assetId + ", assetCode=" + assetCode + ", assetTransId=" + assetTransId + ", assetCatId=" + assetCatId + ", catName=" + catName + ", assetName=" + assetName + ", useToDate=" + useToDate + ", useFromDate=" + useFromDate + ", returnNotifctnDays=" + returnNotifctnDays + ", returtnInDays=" + returtnInDays + ", empCode=" + empCode + ", alarmDays=" + alarmDays + ", firstName=" + firstName + ", surname=" + surname + ", deptName=" + deptName + ", empDesgn=" + empDesgn + ", mobileNo1=" + mobileNo1 + ", emailId=" + emailId + "]";
}


public String getMobileNo1(){
    return mobileNo1;
}


public void setMobileNo1(String mobileNo1){
    this.mobileNo1 = mobileNo1;
}


public void setAssetName(String assetName){
    this.assetName = assetName;
}


public String getFirstName(){
    return firstName;
}


public int getAssetId(){
    return assetId;
}


public void setAssetTransId(String assetTransId){
    this.assetTransId = assetTransId;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getUseToDate(){
    return useToDate;
}


public void setUseFromDate(Date useFromDate){
    this.useFromDate = useFromDate;
}


public String getSurname(){
    return surname;
}


}