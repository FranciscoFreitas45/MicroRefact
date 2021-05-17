import javax.persistence;
@Entity
@Table(name = "tbl_attt_daily_daily")
public class DailyAttendance {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  int id;

@Column(name = "company_id")
 private  int companyId;

@Column(name = "emp_code")
 private  String empCode;

@Column(name = "emp_name")
 private  String empName;

@Column(name = "att_date")
 private  String attDate;

@Column(name = "att_status")
 private  String attStatus;

@Column(name = "lv_sumup_id")
 private  int lvSumupId;

@Column(name = "working_hrs")
 private  float workingHrs;

@Column(name = "in_time")
 private  String inTime;

@Column(name = "rec_status")
 private  String recStatus;

@Column(name = "login_name")
 private  String loginName;

@Column(name = "login_time")
 private  String loginTime;

@Column(name = "import_date")
 private  String importDate;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "ot_hr")
 private  String otHr;

@Column(name = "current_shiftid")
 private  int currentShiftid;

@Column(name = "late_mark")
 private  String lateMark;

@Column(name = "late_min")
 private  int lateMin;

@Column(name = "reason")
 private  String reason;

@Column(name = "current_shiftname")
 private  String currentShiftname;

@Column(name = "freeze_by_supervisor")
 private  int freezeBySupervisor;

@Column(name = "comments_supervisor")
 private  String commentsSupervisor;

@Column(name = "get_pass_used_count")
 private  int getPassUsedCount;

@Column(name = "get_pass_used_hour")
 private  int getPassUsedHour;

@Column(name = "get_pass_used_hour_reason")
 private  String getPassUsedHourReason;

@Column(name = "raw_data_inout")
 private  String rawDataInout;

@Column(name = "manual_ot_hr")
 private  int manualOtHr;

@Column(name = "full_night")
 private  int fullNight;

@Column(name = "half_night")
 private  int halfNight;

@Column(name = "out_time")
 private  String outTime;

@Column(name = "early_going_mark")
 private  int earlyGoingMark;

@Column(name = "early_going_min")
 private  int earlyGoingMin;

@Column(name = "multiple_entries")
 private  String multipleEntries;

@Column(name = "casetype")
 private  String casetype;

@Column(name = "is_fixed")
 private  int isFixed;

@Column(name = "by_file_updated")
 private  int byFileUpdated;

@Column(name = "location_id")
 private  int locationId;

@Column(name = "emp_type")
 private  int empType;

@Column(name = "emp_json")
 private  String empJson;

@Column(name = "atsumm_uid")
 private  String atsummUid;

@Column(name = "file_name")
 private  String fileName;

@Column(name = "row_id")
 private  int rowId;

@Column(name = "atts_sd_show")
 private  String attsSdShow;


public int getLocationId(){
    return locationId;
}


public void setFreezeBySupervisor(int freezeBySupervisor){
    this.freezeBySupervisor = freezeBySupervisor;
}


public void setEmpType(int empType){
    this.empType = empType;
}


public void setManualOtHr(int manualOtHr){
    this.manualOtHr = manualOtHr;
}


public void setRawDataInout(String rawDataInout){
    this.rawDataInout = rawDataInout;
}


public void setCasetype(String casetype){
    this.casetype = casetype;
}


public void setAttStatus(String attStatus){
    this.attStatus = attStatus;
}


public String getCurrentShiftname(){
    return currentShiftname;
}


public int getByFileUpdated(){
    return byFileUpdated;
}


public String getImportDate(){
    return importDate;
}


public String getAtsummUid(){
    return atsummUid;
}


public int getLateMin(){
    return lateMin;
}


public void setFileName(String fileName){
    this.fileName = fileName;
}


public String getRecStatus(){
    return recStatus;
}


public void setLoginName(String loginName){
    this.loginName = loginName;
}


public void setCurrentShiftname(String currentShiftname){
    this.currentShiftname = currentShiftname;
}


public int getFullNight(){
    return fullNight;
}


public void setId(int id){
    this.id = id;
}


public int getEmpType(){
    return empType;
}


public void setWorkingHrs(float workingHrs){
    this.workingHrs = workingHrs;
}


public String getFileName(){
    return fileName;
}


public void setOtHr(String otHr){
    this.otHr = otHr;
}


public int getManualOtHr(){
    return manualOtHr;
}


public String getAttsSdShow(){
    return attsSdShow;
}


public int getEarlyGoingMark(){
    return earlyGoingMark;
}


public void setRecStatus(String recStatus){
    this.recStatus = recStatus;
}


public void setCommentsSupervisor(String commentsSupervisor){
    this.commentsSupervisor = commentsSupervisor;
}


public void setEarlyGoingMin(int earlyGoingMin){
    this.earlyGoingMin = earlyGoingMin;
}


public int getEmpId(){
    return empId;
}


public String getLoginName(){
    return loginName;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public int getGetPassUsedHour(){
    return getPassUsedHour;
}


public int getIsFixed(){
    return isFixed;
}


public String getLateMark(){
    return lateMark;
}


public void setGetPassUsedHourReason(String getPassUsedHourReason){
    this.getPassUsedHourReason = getPassUsedHourReason;
}


public void setOutTime(String outTime){
    this.outTime = outTime;
}


public void setInTime(String inTime){
    this.inTime = inTime;
}


public void setCompanyId(int companyId){
    this.companyId = companyId;
}


public String getGetPassUsedHourReason(){
    return getPassUsedHourReason;
}


public void setCurrentShiftid(int currentShiftid){
    this.currentShiftid = currentShiftid;
}


public void setGetPassUsedCount(int getPassUsedCount){
    this.getPassUsedCount = getPassUsedCount;
}


public String getOtHr(){
    return otHr;
}


public String getAttDate(){
    return attDate;
}


public String getRawDataInout(){
    return rawDataInout;
}


public int getRowId(){
    return rowId;
}


public int getId(){
    return id;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public String getCasetype(){
    return casetype;
}


public String getEmpName(){
    return empName;
}


public void setFullNight(int fullNight){
    this.fullNight = fullNight;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public float getWorkingHrs(){
    return workingHrs;
}


public int getGetPassUsedCount(){
    return getPassUsedCount;
}


public void setGetPassUsedHour(int getPassUsedHour){
    this.getPassUsedHour = getPassUsedHour;
}


public String getLoginTime(){
    return loginTime;
}


public void setLoginTime(String loginTime){
    this.loginTime = loginTime;
}


public String getAttStatus(){
    return attStatus;
}


public String getCommentsSupervisor(){
    return commentsSupervisor;
}


public void setEarlyGoingMark(int earlyGoingMark){
    this.earlyGoingMark = earlyGoingMark;
}


public void setReason(String reason){
    this.reason = reason;
}


public int getHalfNight(){
    return halfNight;
}


public String getOutTime(){
    return outTime;
}


public void setLvSumupId(int lvSumupId){
    this.lvSumupId = lvSumupId;
}


public void setMultipleEntries(String multipleEntries){
    this.multipleEntries = multipleEntries;
}


public String getMultipleEntries(){
    return multipleEntries;
}


public int getLvSumupId(){
    return lvSumupId;
}


public void setAttDate(String attDate){
    this.attDate = attDate;
}


public void setIsFixed(int isFixed){
    this.isFixed = isFixed;
}


public int getFreezeBySupervisor(){
    return freezeBySupervisor;
}


public void setLateMark(String lateMark){
    this.lateMark = lateMark;
}


public String getReason(){
    return reason;
}


public void setAtsummUid(String atsummUid){
    this.atsummUid = atsummUid;
}


public int getCurrentShiftid(){
    return currentShiftid;
}


public String getEmpJson(){
    return empJson;
}


public void setHalfNight(int halfNight){
    this.halfNight = halfNight;
}


public void setEmpJson(String empJson){
    this.empJson = empJson;
}


public String getInTime(){
    return inTime;
}


public String getEmpCode(){
    return empCode;
}


public int getCompanyId(){
    return companyId;
}


public void setByFileUpdated(int byFileUpdated){
    this.byFileUpdated = byFileUpdated;
}


public void setRowId(int rowId){
    this.rowId = rowId;
}


public void setAttsSdShow(String attsSdShow){
    this.attsSdShow = attsSdShow;
}


public void setLocationId(int locationId){
    this.locationId = locationId;
}


@Override
public String toString(){
    return "DailyAttendance [id=" + id + ", companyId=" + companyId + ", empCode=" + empCode + ", empName=" + empName + ", attDate=" + attDate + ", attStatus=" + attStatus + ", lvSumupId=" + lvSumupId + ", workingHrs=" + workingHrs + ", inTime=" + inTime + ", recStatus=" + recStatus + ", loginName=" + loginName + ", loginTime=" + loginTime + ", importDate=" + importDate + ", empId=" + empId + ", otHr=" + otHr + ", currentShiftid=" + currentShiftid + ", lateMark=" + lateMark + ", lateMin=" + lateMin + ", reason=" + reason + ", currentShiftname=" + currentShiftname + ", freezeBySupervisor=" + freezeBySupervisor + ", commentsSupervisor=" + commentsSupervisor + ", getPassUsedCount=" + getPassUsedCount + ", getPassUsedHour=" + getPassUsedHour + ", getPassUsedHourReason=" + getPassUsedHourReason + ", rawDataInout=" + rawDataInout + ", manualOtHr=" + manualOtHr + ", fullNight=" + fullNight + ", halfNight=" + halfNight + ", outTime=" + outTime + ", earlyGoingMark=" + earlyGoingMark + ", earlyGoingMin=" + earlyGoingMin + ", multipleEntries=" + multipleEntries + ", casetype=" + casetype + ", isFixed=" + isFixed + ", byFileUpdated=" + byFileUpdated + ", locationId=" + locationId + ", empType=" + empType + ", empJson=" + empJson + ", atsummUid=" + atsummUid + ", fileName=" + fileName + ", rowId=" + rowId + ", attsSdShow=" + attsSdShow + "]";
}


public int getEarlyGoingMin(){
    return earlyGoingMin;
}


public void setLateMin(int lateMin){
    this.lateMin = lateMin;
}


public void setImportDate(String importDate){
    this.importDate = importDate;
}


}