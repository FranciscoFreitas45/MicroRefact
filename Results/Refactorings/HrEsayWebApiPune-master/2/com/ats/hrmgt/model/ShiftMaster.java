import javax.persistence;
@Entity
@Table(name = "tbl_shift_timming")
public class ShiftMaster {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  int id;

@Column(name = "shiftname")
 private  String shiftname;

@Column(name = "fromtime")
 private  String fromtime;

@Column(name = "totime")
 private  String totime;

@Column(name = "changeable")
 private  int changeable;

@Column(name = "changewith")
 private  int changewith;

@Column(name = "company_id")
 private  int companyId;

@Column(name = "max_late_time_allowed")
 private  int maxLateTimeAllowed;

@Column(name = "shift_hr")
 private  String shiftHr;

@Column(name = "shift_halfday_hr")
 private  String shiftHalfdayHr;

@Column(name = "early_going_min")
 private  int earlyGoingMin;

@Column(name = "ot_calculated_time")
 private  String otCalculatedTime;

@Column(name = "ot_calculated_after_hr")
 private  int otCalculatedAfterHr;

@Column(name = "shift_ot_hour")
 private  float shiftOtHour;

@Column(name = "department_id")
 private  int departmentId;

@Column(name = "self_group_id")
 private  int selfGroupId;

@Column(name = "status")
 private  int status;

@Column(name = "location_id")
 private  int locationId;

@Column(name = "short_name")
 private  String shortName;


public int getSelfGroupId(){
    return selfGroupId;
}


public int getLocationId(){
    return locationId;
}


public int getOtCalculatedAfterHr(){
    return otCalculatedAfterHr;
}


public void setShiftname(String shiftname){
    this.shiftname = shiftname;
}


public void setFromtime(String fromtime){
    this.fromtime = fromtime;
}


public String getOtCalculatedTime(){
    return otCalculatedTime;
}


public void setDepartmentId(int departmentId){
    this.departmentId = departmentId;
}


public int getId(){
    return id;
}


public void setOtCalculatedAfterHr(int otCalculatedAfterHr){
    this.otCalculatedAfterHr = otCalculatedAfterHr;
}


public int getStatus(){
    return status;
}


public float getShiftOtHour(){
    return shiftOtHour;
}


public void setShiftHalfdayHr(String shiftHalfdayHr){
    this.shiftHalfdayHr = shiftHalfdayHr;
}


public int getChangewith(){
    return changewith;
}


public void setChangeable(int changeable){
    this.changeable = changeable;
}


public String getFromtime(){
    return fromtime;
}


public void setId(int id){
    this.id = id;
}


public void setOtCalculatedTime(String otCalculatedTime){
    this.otCalculatedTime = otCalculatedTime;
}


public void setChangewith(int changewith){
    this.changewith = changewith;
}


public void setTotime(String totime){
    this.totime = totime;
}


public void setMaxLateTimeAllowed(int maxLateTimeAllowed){
    this.maxLateTimeAllowed = maxLateTimeAllowed;
}


public String getShiftHr(){
    return shiftHr;
}


public String getShiftHalfdayHr(){
    return shiftHalfdayHr;
}


public int getMaxLateTimeAllowed(){
    return maxLateTimeAllowed;
}


public String getTotime(){
    return totime;
}


public void setEarlyGoingMin(int earlyGoingMin){
    this.earlyGoingMin = earlyGoingMin;
}


public void setStatus(int status){
    this.status = status;
}


public String getShiftname(){
    return shiftname;
}


public void setShiftHr(String shiftHr){
    this.shiftHr = shiftHr;
}


public void setSelfGroupId(int selfGroupId){
    this.selfGroupId = selfGroupId;
}


public int getChangeable(){
    return changeable;
}


public int getCompanyId(){
    return companyId;
}


public void setShiftOtHour(float shiftOtHour){
    this.shiftOtHour = shiftOtHour;
}


public String getShortName(){
    return shortName;
}


public void setShortName(String shortName){
    this.shortName = shortName;
}


public void setLocationId(int locationId){
    this.locationId = locationId;
}


@Override
public String toString(){
    return "ShiftMaster [id=" + id + ", shiftname=" + shiftname + ", fromtime=" + fromtime + ", totime=" + totime + ", changeable=" + changeable + ", changewith=" + changewith + ", companyId=" + companyId + ", maxLateTimeAllowed=" + maxLateTimeAllowed + ", shiftHr=" + shiftHr + ", shiftHalfdayHr=" + shiftHalfdayHr + ", earlyGoingMin=" + earlyGoingMin + ", otCalculatedTime=" + otCalculatedTime + ", otCalculatedAfterHr=" + otCalculatedAfterHr + ", shiftOtHour=" + shiftOtHour + ", departmentId=" + departmentId + ", selfGroupId=" + selfGroupId + ", status=" + status + ", locationId=" + locationId + ", shortName=" + shortName + "]";
}


public int getEarlyGoingMin(){
    return earlyGoingMin;
}


public void setCompanyId(int companyId){
    this.companyId = companyId;
}


public int getDepartmentId(){
    return departmentId;
}


}