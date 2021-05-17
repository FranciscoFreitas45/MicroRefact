import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class ServicingDashDetails {

@Id
 private  int tServicingId;

 private  int assetId;

 private  Date serviceDate;

 private  Date nextServiceDate;

 private  float billAmt;

 private  String assetCode;

 private  String assetName;

 private  String catName;

 private  String compName;

 private  String contactNo1;

 private  String conatctPersonName;

 private  String contactPersonNo;

 private  String contactPersonEmail;

 private  int servDueDays;

 private  int alarmDays;


public void setServiceDate(Date serviceDate){
    this.serviceDate = serviceDate;
}


public void setConatctPersonName(String conatctPersonName){
    this.conatctPersonName = conatctPersonName;
}


public String getContactPersonNo(){
    return contactPersonNo;
}


public void setServDueDays(int servDueDays){
    this.servDueDays = servDueDays;
}


public void setContactPersonEmail(String contactPersonEmail){
    this.contactPersonEmail = contactPersonEmail;
}


public float getBillAmt(){
    return billAmt;
}


public int gettServicingId(){
    return tServicingId;
}


public String getAssetCode(){
    return assetCode;
}


public void setBillAmt(float billAmt){
    this.billAmt = billAmt;
}


public String getContactNo1(){
    return contactNo1;
}


public void setNextServiceDate(Date nextServiceDate){
    this.nextServiceDate = nextServiceDate;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getServiceDate(){
    return serviceDate;
}


public void setAssetId(int assetId){
    this.assetId = assetId;
}


public void setAlarmDays(int alarmDays){
    this.alarmDays = alarmDays;
}


public void settServicingId(int tServicingId){
    this.tServicingId = tServicingId;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getNextServiceDate(){
    return nextServiceDate;
}


public String getCompName(){
    return compName;
}


public void setAssetCode(String assetCode){
    this.assetCode = assetCode;
}


public String getCatName(){
    return catName;
}


public String getContactPersonEmail(){
    return contactPersonEmail;
}


public void setContactNo1(String contactNo1){
    this.contactNo1 = contactNo1;
}


public void setContactPersonNo(String contactPersonNo){
    this.contactPersonNo = contactPersonNo;
}


public void setCatName(String catName){
    this.catName = catName;
}


public int getAlarmDays(){
    return alarmDays;
}


public int getServDueDays(){
    return servDueDays;
}


public String getAssetName(){
    return assetName;
}


public void setCompName(String compName){
    this.compName = compName;
}


@Override
public String toString(){
    return "ServicingDashDetails [tServicingId=" + tServicingId + ", assetId=" + assetId + ", serviceDate=" + serviceDate + ", nextServiceDate=" + nextServiceDate + ", billAmt=" + billAmt + ", assetCode=" + assetCode + ", assetName=" + assetName + ", catName=" + catName + ", compName=" + compName + ", contactNo1=" + contactNo1 + ", conatctPersonName=" + conatctPersonName + ", contactPersonNo=" + contactPersonNo + ", contactPersonEmail=" + contactPersonEmail + ", servDueDays=" + servDueDays + ", alarmDays=" + alarmDays + "]";
}


public String getConatctPersonName(){
    return conatctPersonName;
}


public void setAssetName(String assetName){
    this.assetName = assetName;
}


public int getAssetId(){
    return assetId;
}


}