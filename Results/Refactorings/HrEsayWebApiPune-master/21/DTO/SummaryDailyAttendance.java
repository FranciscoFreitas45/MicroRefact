import javax.persistence;
public class SummaryDailyAttendance {

 private  int id;

 private  int companyId;

 private  int empId;

 private  String empCode;

 private  String empName;

 private  int month;

 private  int year;

 private  float workingDays;

 private  float presentDays;

 private  float weeklyOff;

 private  float paidHoliday;

 private  float paidLeave;

 private  float legalStrike;

 private  float layOff;

 private  float unpaidHoliday;

 private  float unpaidLeave;

 private  int absentDays;

 private  float payableDays;

 private  float ncpDays;

 private  float totlateMins;

 private  float totlateDays;

 private  float totoutMins;

 private  float totworkingHrs;

 private  float tototHrs;

 private  float totOthr;

 private  int totLate;

 private  String recStatus;

 private  String loginName;

 private  String loginTime;

 private  int status;

 private  String importDate;

 private  int recStatusPaid;

 private  int totalDaysInmonth;

 private  int lateDedLeavePaid;

 private  float holidayPresent;

 private  float weeklyOffPresent;

 private  float fullNight;

 private  float halfNight;

 private  float holidayPresentHalf;

 private  float weeklyOffPresentHalf;

 private  float weeklyOffHoliday0ff;

 private  float weeklyOffHolidayOffPresent;

 private  float weeklyOffHolidayOffPresentHalfday;

 private  float hdpresentHdleave;

 private  int totEarlyGoing;

 private  String atsummUid;

 private  int calculationDone;


public int getRecStatusPaid(){
    return recStatusPaid;
}


public float getHdpresentHdleave(){
    return hdpresentHdleave;
}


public float getUnpaidLeave(){
    return unpaidLeave;
}


public int getCalculationDone(){
    return calculationDone;
}


public int getStatus(){
    return status;
}


public String getImportDate(){
    return importDate;
}


public String getAtsummUid(){
    return atsummUid;
}


public float getLegalStrike(){
    return legalStrike;
}


public String getRecStatus(){
    return recStatus;
}


public float getFullNight(){
    return fullNight;
}


public int getMonth(){
    return month;
}


public float getWeeklyOffHolidayOffPresentHalfday(){
    return weeklyOffHolidayOffPresentHalfday;
}


public float getWeeklyOffPresentHalf(){
    return weeklyOffPresentHalf;
}


public float getWeeklyOffHoliday0ff(){
    return weeklyOffHoliday0ff;
}


public int getEmpId(){
    return empId;
}


public String getLoginName(){
    return loginName;
}


public float getUnpaidHoliday(){
    return unpaidHoliday;
}


public float getPresentDays(){
    return presentDays;
}


public int getYear(){
    return year;
}


public float getPaidLeave(){
    return paidLeave;
}


public float getHolidayPresent(){
    return holidayPresent;
}


public float getTotworkingHrs(){
    return totworkingHrs;
}


public float getPaidHoliday(){
    return paidHoliday;
}


public float getTototHrs(){
    return tototHrs;
}


public float getPayableDays(){
    return payableDays;
}


public int getTotEarlyGoing(){
    return totEarlyGoing;
}


public int getId(){
    return id;
}


public String getEmpName(){
    return empName;
}


public float getWeeklyOffPresent(){
    return weeklyOffPresent;
}


public float getLayOff(){
    return layOff;
}


public float getWeeklyOff(){
    return weeklyOff;
}


public String getLoginTime(){
    return loginTime;
}


public float getHalfNight(){
    return halfNight;
}


public int getLateDedLeavePaid(){
    return lateDedLeavePaid;
}


public int getAbsentDays(){
    return absentDays;
}


public float getTotOthr(){
    return totOthr;
}


public float getNcpDays(){
    return ncpDays;
}


public float getTotlateDays(){
    return totlateDays;
}


public float getWorkingDays(){
    return workingDays;
}


public String getEmpCode(){
    return empCode;
}


public float getTotoutMins(){
    return totoutMins;
}


public int getCompanyId(){
    return companyId;
}


public float getTotlateMins(){
    return totlateMins;
}


public int getTotLate(){
    return totLate;
}


public int getTotalDaysInmonth(){
    return totalDaysInmonth;
}


public float getWeeklyOffHolidayOffPresent(){
    return weeklyOffHolidayOffPresent;
}


public float getHolidayPresentHalf(){
    return holidayPresentHalf;
}


}