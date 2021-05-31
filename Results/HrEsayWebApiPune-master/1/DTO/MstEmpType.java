import javax.persistence;
public class MstEmpType {

 private  int empTypeId;

 private  String name;

 private  String category;

 private  String attType;

 private  String lmApplicable;

 private  String halfDay;

 private  String whWork;

 private  String minWorkHr;

 private  String minworkApplicable;

 private  String otApplicable;

 private  String otTime;

 private  String details;

 private  String otType;

 private  int companyId;

 private  int weeklyHolidayLateAllowed;

 private  int weeklyHolidayLateAllowedMin;

 private  int earlyGoingAllowed;

 private  int earlyGoingMin;

 private  int maxLateTimeAllowed;

 private  int status;

 private  int delStatus;

 private  int exInt1;

 private  int exInt2;

 private  String exVar1;

 private  String exVar2;

 private  String prodIncentiveApp;


public String getExVar2(){
    return exVar2;
}


public String getName(){
    return name;
}


public String getExVar1(){
    return exVar1;
}


public int getStatus(){
    return status;
}


public String getMinWorkHr(){
    return minWorkHr;
}


public int getEmpTypeId(){
    return empTypeId;
}


public String getCategory(){
    return category;
}


public String getDetails(){
    return details;
}


public String getLmApplicable(){
    return lmApplicable;
}


public int getEarlyGoingAllowed(){
    return earlyGoingAllowed;
}


public String getOtType(){
    return otType;
}


public int getExInt2(){
    return exInt2;
}


public String getAttType(){
    return attType;
}


public String getOtTime(){
    return otTime;
}


public int getExInt1(){
    return exInt1;
}


public int getWeeklyHolidayLateAllowed(){
    return weeklyHolidayLateAllowed;
}


public String getHalfDay(){
    return halfDay;
}


public String getProdIncentiveApp(){
    return prodIncentiveApp;
}


public int getWeeklyHolidayLateAllowedMin(){
    return weeklyHolidayLateAllowedMin;
}


public int getMaxLateTimeAllowed(){
    return maxLateTimeAllowed;
}


public String getMinworkApplicable(){
    return minworkApplicable;
}


public String getWhWork(){
    return whWork;
}


public int getDelStatus(){
    return delStatus;
}


public int getCompanyId(){
    return companyId;
}


public String getOtApplicable(){
    return otApplicable;
}


public int getEarlyGoingMin(){
    return earlyGoingMin;
}


}