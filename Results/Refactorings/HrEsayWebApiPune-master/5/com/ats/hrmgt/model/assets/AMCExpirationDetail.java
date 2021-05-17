import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class AMCExpirationDetail {

@Id
 private  int amcId;

 private  int assetId;

 private  Date amcFrDate;

 private  Date amcToDate;

 private  float amcAmt;

 private  String assetCode;

 private  String assetName;

 private  String catName;

 private  String compName;

 private  String contactNo1;

 private  String conatctPersonName;

 private  String contactPersonNo;

 private  String contactPersonEmail;

 private  int amcDueDays;

 private  int alarmDays;


public void setAmcId(int amcId){
    this.amcId = amcId;
}


public void setConatctPersonName(String conatctPersonName){
    this.conatctPersonName = conatctPersonName;
}


public String getContactPersonNo(){
    return contactPersonNo;
}


public void setContactPersonEmail(String contactPersonEmail){
    this.contactPersonEmail = contactPersonEmail;
}


public float getAmcAmt(){
    return amcAmt;
}


public String getAssetCode(){
    return assetCode;
}


public String getContactNo1(){
    return contactNo1;
}


public void setAmcAmt(float amcAmt){
    this.amcAmt = amcAmt;
}


public void setAssetId(int assetId){
    this.assetId = assetId;
}


public void setAlarmDays(int alarmDays){
    this.alarmDays = alarmDays;
}


public void setAmcDueDays(int amcDueDays){
    this.amcDueDays = amcDueDays;
}


public int getAmcId(){
    return amcId;
}


public void setAmcFrDate(Date amcFrDate){
    this.amcFrDate = amcFrDate;
}


public int getAmcDueDays(){
    return amcDueDays;
}


public String getCompName(){
    return compName;
}


public void setAssetCode(String assetCode){
    this.assetCode = assetCode;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getAmcFrDate(){
    return amcFrDate;
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


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getAmcToDate(){
    return amcToDate;
}


public void setAmcToDate(Date amcToDate){
    this.amcToDate = amcToDate;
}


public String getAssetName(){
    return assetName;
}


public void setCompName(String compName){
    this.compName = compName;
}


@Override
public String toString(){
    return "AMCExpirationDetail [amcId=" + amcId + ", assetId=" + assetId + ", amcFrDate=" + amcFrDate + ", amcToDate=" + amcToDate + ", amcAmt=" + amcAmt + ", assetCode=" + assetCode + ", assetName=" + assetName + ", catName=" + catName + ", compName=" + compName + ", contactNo1=" + contactNo1 + ", conatctPersonName=" + conatctPersonName + ", contactPersonNo=" + contactPersonNo + ", contactPersonEmail=" + contactPersonEmail + ", amcDueDays=" + amcDueDays + ", alarmDays=" + alarmDays + "]";
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