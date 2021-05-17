import javax.persistence;
public class WeeklyOffShit {

 private  int id;

 private  int month;

 private  int year;

 private  String weekofffromdate;

 private  String weekoffshiftdate;

 private  int cmpId;

 private  String reason;

 private  String loginTime;

 private  int locationId;

 private  int delStatus;

 private  int empId;


public String getWeekoffshiftdate(){
    return weekoffshiftdate;
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


public int getEmpId(){
    return empId;
}


public String getLoginTime(){
    return loginTime;
}


public int getDelStatus(){
    return delStatus;
}


public int getYear(){
    return year;
}


public int getMonth(){
    return month;
}


}