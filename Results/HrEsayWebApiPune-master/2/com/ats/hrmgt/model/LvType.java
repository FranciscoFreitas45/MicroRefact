import javax.persistence;
@Entity
@Table(name = "tbl_lvm_lvtype")
public class LvType {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  int id;

@Column(name = "name")
 private  String name;

@Column(name = "name_sd")
 private  String nameSd;

@Column(name = "lv_sumup_id")
 private  int lvSumupId;

@Column(name = "pay_status")
 private  String payStatus;

@Column(name = "present_day")
 private  float present_day;

@Column(name = "leave_day")
 private  float leaveDay;

@Column(name = "working_hrs")
 private  float workingHrs;

@Column(name = "ot_hrs")
 private  float otHrs;

@Column(name = "late_mins ")
 private  float lateMins;

@Column(name = "out_mins")
 private  float outMins;

@Column(name = "is_structured")
 private  int isStructured;

@Column(name = "lv_colour")
 private  String lvColour;

@Column(name = "is_used")
 private  int isUsed;

@Column(name = "login_name")
 private  String loginName;

@Column(name = "login_time")
 private  String loginTime;

@Column(name = "primary")
 private  int primary;

@Column(name = "company_id")
 private  int companyId;

@Column(name = "to_show_drowpdown")
 private  int toShowDrowpdown;

@Column(name = "name_sd_show")
 private  String nameSdShow;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public int getPrimary(){
    return primary;
}


public int getId(){
    return id;
}


public void setOutMins(float outMins){
    this.outMins = outMins;
}


public void setToShowDrowpdown(int toShowDrowpdown){
    this.toShowDrowpdown = toShowDrowpdown;
}


public float getWorkingHrs(){
    return workingHrs;
}


public String getLoginTime(){
    return loginTime;
}


public void setLoginTime(String loginTime){
    this.loginTime = loginTime;
}


public float getOutMins(){
    return outMins;
}


public void setLoginName(String loginName){
    this.loginName = loginName;
}


public void setLvSumupId(int lvSumupId){
    this.lvSumupId = lvSumupId;
}


public void setId(int id){
    this.id = id;
}


public void setWorkingHrs(float workingHrs){
    this.workingHrs = workingHrs;
}


public int getLvSumupId(){
    return lvSumupId;
}


public void setLateMins(float lateMins){
    this.lateMins = lateMins;
}


public float getOtHrs(){
    return otHrs;
}


public String getNameSd(){
    return nameSd;
}


public float getLateMins(){
    return lateMins;
}


public void setPresent_day(float present_day){
    this.present_day = present_day;
}


public void setLvColour(String lvColour){
    this.lvColour = lvColour;
}


public String getLoginName(){
    return loginName;
}


public String getPayStatus(){
    return payStatus;
}


public void setPayStatus(String payStatus){
    this.payStatus = payStatus;
}


public String getLvColour(){
    return lvColour;
}


public void setIsStructured(int isStructured){
    this.isStructured = isStructured;
}


public String getNameSdShow(){
    return nameSdShow;
}


public int getToShowDrowpdown(){
    return toShowDrowpdown;
}


public void setNameSd(String nameSd){
    this.nameSd = nameSd;
}


public int getCompanyId(){
    return companyId;
}


public int getIsStructured(){
    return isStructured;
}


public float getLeaveDay(){
    return leaveDay;
}


public void setNameSdShow(String nameSdShow){
    this.nameSdShow = nameSdShow;
}


@Override
public String toString(){
    return "LvType [id=" + id + ", name=" + name + ", nameSd=" + nameSd + ", lvSumupId=" + lvSumupId + ", payStatus=" + payStatus + ", present_day=" + present_day + ", leaveDay=" + leaveDay + ", workingHrs=" + workingHrs + ", otHrs=" + otHrs + ", lateMins=" + lateMins + ", outMins=" + outMins + ", isStructured=" + isStructured + ", lvColour=" + lvColour + ", isUsed=" + isUsed + ", loginName=" + loginName + ", loginTime=" + loginTime + ", primary=" + primary + ", companyId=" + companyId + ", toShowDrowpdown=" + toShowDrowpdown + ", nameSdShow=" + nameSdShow + "]";
}


public float getPresent_day(){
    return present_day;
}


public void setPrimary(int primary){
    this.primary = primary;
}


public void setLeaveDay(float leaveDay){
    this.leaveDay = leaveDay;
}


public int getIsUsed(){
    return isUsed;
}


public void setCompanyId(int companyId){
    this.companyId = companyId;
}


public void setOtHrs(float otHrs){
    this.otHrs = otHrs;
}


public void setIsUsed(int isUsed){
    this.isUsed = isUsed;
}


}