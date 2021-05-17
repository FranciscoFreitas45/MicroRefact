import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class GetWeekShiftChange {

@Id
 private  int id;

 private  int month;

 private  int year;

 private  Date weekofffromdate;

 private  Date weekoffshiftdate;

 private  int cmpId;

 private  String reason;

 private  String loginTime;

 private  int locationId;

 private  int delStatus;

 private  String locName;


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getWeekoffshiftdate(){
    return weekoffshiftdate;
}


public void setMonth(int month){
    this.month = month;
}


public void setWeekofffromdate(Date weekofffromdate){
    this.weekofffromdate = weekofffromdate;
}


public int getLocationId(){
    return locationId;
}


public String getLocName(){
    return locName;
}


public String getReason(){
    return reason;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getWeekofffromdate(){
    return weekofffromdate;
}


public void setLocName(String locName){
    this.locName = locName;
}


public int getCmpId(){
    return cmpId;
}


public int getId(){
    return id;
}


public void setYear(int year){
    this.year = year;
}


public void setCmpId(int cmpId){
    this.cmpId = cmpId;
}


public void setWeekoffshiftdate(Date weekoffshiftdate){
    this.weekoffshiftdate = weekoffshiftdate;
}


public String getLoginTime(){
    return loginTime;
}


public void setLoginTime(String loginTime){
    this.loginTime = loginTime;
}


public int getDelStatus(){
    return delStatus;
}


public int getYear(){
    return year;
}


public void setReason(String reason){
    this.reason = reason;
}


public void setId(int id){
    this.id = id;
}


public void setLocationId(int locationId){
    this.locationId = locationId;
}


@Override
public String toString(){
    return "GetWeekShiftChange [id=" + id + ", month=" + month + ", year=" + year + ", weekofffromdate=" + weekofffromdate + ", weekoffshiftdate=" + weekoffshiftdate + ", cmpId=" + cmpId + ", reason=" + reason + ", loginTime=" + loginTime + ", locationId=" + locationId + ", delStatus=" + delStatus + ", locName=" + locName + "]";
}


public int getMonth(){
    return month;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


}