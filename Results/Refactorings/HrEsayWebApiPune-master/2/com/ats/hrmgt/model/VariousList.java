import java.util.List;
public class VariousList {

 private  List<ShiftMaster> shiftList;

 private  List<MstEmpType> mstWeeklyOffList;

 private  List<Holiday> holidayList;

 private  List<LvType> lvTypeList;

 private  List<LvmSumUp> LvmSumUpList;

 private  List<DailyAttendance> dailyAttendanceList;

 private  List<SummaryDailyAttendance> summaryDailyAttendanceList;


public List<ShiftMaster> getShiftList(){
    return shiftList;
}


public void setShiftList(List<ShiftMaster> shiftList){
    this.shiftList = shiftList;
}


public List<MstEmpType> getMstWeeklyOffList(){
    return mstWeeklyOffList;
}


public void setSummaryDailyAttendanceList(List<SummaryDailyAttendance> summaryDailyAttendanceList){
    this.summaryDailyAttendanceList = summaryDailyAttendanceList;
}


public List<SummaryDailyAttendance> getSummaryDailyAttendanceList(){
    return summaryDailyAttendanceList;
}


public void setHolidayList(List<Holiday> holidayList){
    this.holidayList = holidayList;
}


public List<DailyAttendance> getDailyAttendanceList(){
    return dailyAttendanceList;
}


public void setMstWeeklyOffList(List<MstEmpType> mstWeeklyOffList){
    this.mstWeeklyOffList = mstWeeklyOffList;
}


public List<Holiday> getHolidayList(){
    return holidayList;
}


public void setLvmSumUpList(List<LvmSumUp> lvmSumUpList){
    LvmSumUpList = lvmSumUpList;
}


public List<LvType> getLvTypeList(){
    return lvTypeList;
}


public void setLvTypeList(List<LvType> lvTypeList){
    this.lvTypeList = lvTypeList;
}


public void setDailyAttendanceList(List<DailyAttendance> dailyAttendanceList){
    this.dailyAttendanceList = dailyAttendanceList;
}


public List<LvmSumUp> getLvmSumUpList(){
    return LvmSumUpList;
}


@Override
public String toString(){
    return "VariousList [shiftList=" + shiftList + ", mstWeeklyOffList=" + mstWeeklyOffList + ", holidayList=" + holidayList + ", lvTypeList=" + lvTypeList + ", LvmSumUpList=" + LvmSumUpList + ", dailyAttendanceList=" + dailyAttendanceList + ", summaryDailyAttendanceList=" + summaryDailyAttendanceList + "]";
}


}