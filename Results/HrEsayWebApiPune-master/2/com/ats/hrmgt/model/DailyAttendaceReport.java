import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.util.Date;
@Entity
public class DailyAttendaceReport {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  String id;

@Column(name = "emp_code")
 private  String empCode;

@Column(name = "emp_name")
 private  String empName;

@Column(name = "att_date")
 private  Date attDate;

@Column(name = "att_status")
 private  String attStatus;

@Column(name = "working_hrs")
 private  String workingHrs;

@Column(name = "in_time")
 private  String inTime;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "current_shiftid")
 private  int currentShiftid;

@Column(name = "late_min")
 private  int lateMin;

@Column(name = "current_shiftname")
 private  String currentShiftname;

@Column(name = "out_time")
 private  String outTime;

@Column(name = "atts_sd_show")
 private  String attsSdShow;

@Column(name = "ot_hr")
 private  String otHr;

@Column(name = "name")
 private  String name;


public void setName(String name){
    this.name = name;
}


public void setCurrentShiftid(int currentShiftid){
    this.currentShiftid = currentShiftid;
}


public String getName(){
    return name;
}


public String getOtHr(){
    return otHr;
}


@JsonFormat(locale = "English", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getAttDate(){
    return attDate;
}


public String getId(){
    return id;
}


public void setAttStatus(String attStatus){
    this.attStatus = attStatus;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public String getCurrentShiftname(){
    return currentShiftname;
}


public String getEmpName(){
    return empName;
}


public int getLateMin(){
    return lateMin;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public String getWorkingHrs(){
    return workingHrs;
}


public String getAttStatus(){
    return attStatus;
}


public void setCurrentShiftname(String currentShiftname){
    this.currentShiftname = currentShiftname;
}


public String getOutTime(){
    return outTime;
}


public void setId(String id){
    this.id = id;
}


public void setWorkingHrs(String workingHrs){
    this.workingHrs = workingHrs;
}


public void setAttDate(Date attDate){
    this.attDate = attDate;
}


public void setOtHr(String otHr){
    this.otHr = otHr;
}


public String getAttsSdShow(){
    return attsSdShow;
}


public int getCurrentShiftid(){
    return currentShiftid;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getInTime(){
    return inTime;
}


public String getEmpCode(){
    return empCode;
}


public void setAttsSdShow(String attsSdShow){
    this.attsSdShow = attsSdShow;
}


public void setOutTime(String outTime){
    this.outTime = outTime;
}


@Override
public String toString(){
    return "DailyAttendaceReport [id=" + id + ", empCode=" + empCode + ", empName=" + empName + ", attDate=" + attDate + ", attStatus=" + attStatus + ", workingHrs=" + workingHrs + ", inTime=" + inTime + ", empId=" + empId + ", currentShiftid=" + currentShiftid + ", lateMin=" + lateMin + ", currentShiftname=" + currentShiftname + ", outTime=" + outTime + ", attsSdShow=" + attsSdShow + ", otHr=" + otHr + ", name=" + name + "]";
}


public void setInTime(String inTime){
    this.inTime = inTime;
}


public void setLateMin(int lateMin){
    this.lateMin = lateMin;
}


}