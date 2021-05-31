import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.util.Date;
public class GetDailyDailyRecord {

 private  int id;

 private  int companyId;

 private  String empCode;

 private  String empName;

 private  Date attDate;

 private  String attStatus;

 private  int lvSumupId;

 private  String workingHrs;

 private  String inTime;

 private  String recStatus;

 private  String loginName;

 private  String loginTime;

 private  String importDate;

 private  int empId;

 private  String otHr;

 private  int currentShiftid;

 private  String lateMark;

 private  int lateMin;

 private  String reason;

 private  String currentShiftname;

 private  int freezeBySupervisor;

 private  String commentsSupervisor;

 private  int getPassUsedCount;

 private  int getPassUsedHour;

 private  String getPassUsedHourReason;

 private  String rawDataInout;

 private  int manualOtHr;

 private  int fullNight;

 private  int halfNight;

 private  String outTime;

 private  int earlyGoingMark;

 private  int earlyGoingMin;

 private  String multipleEntries;

 private  String casetype;

 private  int isFixed;

 private  int byFileUpdated;

 private  int locationId;

 private  int empType;

 private  String empJson;

 private  String atsummUid;

 private  String fileName;

 private  int rowId;

 private  String attsSdShow;


public int getLocationId(){
    return locationId;
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


public String getRecStatus(){
    return recStatus;
}


public int getFullNight(){
    return fullNight;
}


public int getEmpType(){
    return empType;
}


public String getFileName(){
    return fileName;
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


public int getEmpId(){
    return empId;
}


public String getLoginName(){
    return loginName;
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


public String getGetPassUsedHourReason(){
    return getPassUsedHourReason;
}


public String getOtHr(){
    return otHr;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getAttDate(){
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


public String getCasetype(){
    return casetype;
}


public String getEmpName(){
    return empName;
}


public String getWorkingHrs(){
    return workingHrs;
}


public int getGetPassUsedCount(){
    return getPassUsedCount;
}


public String getLoginTime(){
    return loginTime;
}


public String getAttStatus(){
    return attStatus;
}


public String getCommentsSupervisor(){
    return commentsSupervisor;
}


public int getHalfNight(){
    return halfNight;
}


public String getOutTime(){
    return outTime;
}


public String getMultipleEntries(){
    return multipleEntries;
}


public int getLvSumupId(){
    return lvSumupId;
}


public int getFreezeBySupervisor(){
    return freezeBySupervisor;
}


public String getReason(){
    return reason;
}


public int getCurrentShiftid(){
    return currentShiftid;
}


public String getEmpJson(){
    return empJson;
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


public int getEarlyGoingMin(){
    return earlyGoingMin;
}


}