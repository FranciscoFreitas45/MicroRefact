import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class GetHoliday {

@Id
 private  int holidayId;

 private  int calYrId;

 private  int companyId;

 private  String locId;

 private  Date holidayFromdt;

 private  Date holidayTodt;

 private  String holidayRemark;

 private  String companyName;

 private  String locName;

 private  Date calYrFromDate;

 private  Date calYrToDate;

 private  String exVar1;

 private  String exVar2;

 private  String hoCatName;

 private  int hoCatId;

 private  int hotype;


public String getExVar2(){
    return exVar2;
}


public int getHolidayId(){
    return holidayId;
}


public String getLocName(){
    return locName;
}


public String getLocId(){
    return locId;
}


public void setHoCatId(int hoCatId){
    this.hoCatId = hoCatId;
}


public String getExVar1(){
    return exVar1;
}


public void setHoCatName(String hoCatName){
    this.hoCatName = hoCatName;
}


public String getHolidayRemark(){
    return holidayRemark;
}


public void setLocName(String locName){
    this.locName = locName;
}


public int getHoCatId(){
    return hoCatId;
}


public void setCalYrFromDate(Date calYrFromDate){
    this.calYrFromDate = calYrFromDate;
}


public void setCompanyName(String companyName){
    this.companyName = companyName;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getCalYrFromDate(){
    return calYrFromDate;
}


public void setHotype(int hotype){
    this.hotype = hotype;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public int getHotype(){
    return hotype;
}


public void setHolidayRemark(String holidayRemark){
    this.holidayRemark = holidayRemark;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getHolidayTodt(){
    return holidayTodt;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getCalYrToDate(){
    return calYrToDate;
}


public void setLocId(String locId){
    this.locId = locId;
}


public String getHoCatName(){
    return hoCatName;
}


public void setCalYrId(int calYrId){
    this.calYrId = calYrId;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public void setCalYrToDate(Date calYrToDate){
    this.calYrToDate = calYrToDate;
}


public void setHolidayFromdt(Date holidayFromdt){
    this.holidayFromdt = holidayFromdt;
}


public int getCalYrId(){
    return calYrId;
}


public int getCompanyId(){
    return companyId;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getHolidayFromdt(){
    return holidayFromdt;
}


public String getCompanyName(){
    return companyName;
}


@Override
public String toString(){
    return "GetHoliday [holidayId=" + holidayId + ", calYrId=" + calYrId + ", companyId=" + companyId + ", locId=" + locId + ", holidayFromdt=" + holidayFromdt + ", holidayTodt=" + holidayTodt + ", holidayRemark=" + holidayRemark + ", companyName=" + companyName + ", locName=" + locName + ", calYrFromDate=" + calYrFromDate + ", calYrToDate=" + calYrToDate + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", hoCatName=" + hoCatName + ", hoCatId=" + hoCatId + ", hotype=" + hotype + "]";
}


public void setHolidayId(int holidayId){
    this.holidayId = holidayId;
}


public void setCompanyId(int companyId){
    this.companyId = companyId;
}


public void setHolidayTodt(Date holidayTodt){
    this.holidayTodt = holidayTodt;
}


}