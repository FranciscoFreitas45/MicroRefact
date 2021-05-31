import javax.persistence;
@Entity
@Table(name = "tbl_weekoffshift")
public class WeeklyOffShit {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  int id;

@Column(name = "month ")
 private  int month;

@Column(name = "year")
 private  int year;

@Column(name = "weekofffromdate")
 private  String weekofffromdate;

@Column(name = "weekoffshiftdate")
 private  String weekoffshiftdate;

@Column(name = "cmp_id")
 private  int cmpId;

@Column(name = "reason")
 private  String reason;

@Column(name = "login_time")
 private  String loginTime;

@Column(name = "location_id")
 private  int locationId;

@Column(name = "del_status")
 private  int delStatus;

@Column(name = "emp_id")
 private  int empId;


public String getWeekoffshiftdate(){
    return weekoffshiftdate;
}


public void setMonth(int month){
    this.month = month;
}


public void setWeekofffromdate(String weekofffromdate){
    this.weekofffromdate = weekofffromdate;
}


public int getLocationId(){
    return locationId;
}


public String getReason(){
    return reason;
}


public String getWeekofffromdate(){
    return weekofffromdate;
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


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public void setWeekoffshiftdate(String weekoffshiftdate){
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
    return "WeeklyOffShit [id=" + id + ", month=" + month + ", year=" + year + ", weekofffromdate=" + weekofffromdate + ", weekoffshiftdate=" + weekoffshiftdate + ", cmpId=" + cmpId + ", reason=" + reason + ", loginTime=" + loginTime + ", locationId=" + locationId + ", delStatus=" + delStatus + ", empId=" + empId + "]";
}


public int getMonth(){
    return month;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


}