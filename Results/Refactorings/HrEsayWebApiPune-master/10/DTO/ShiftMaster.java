import javax.persistence;
public class ShiftMaster {

 private  int id;

 private  String shiftname;

 private  String fromtime;

 private  String totime;

 private  int changeable;

 private  int changewith;

 private  int companyId;

 private  int maxLateTimeAllowed;

 private  String shiftHr;

 private  String shiftHalfdayHr;

 private  int earlyGoingMin;

 private  String otCalculatedTime;

 private  int otCalculatedAfterHr;

 private  float shiftOtHour;

 private  int departmentId;

 private  int selfGroupId;

 private  int status;

 private  int locationId;

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


public String getOtCalculatedTime(){
    return otCalculatedTime;
}


public int getId(){
    return id;
}


public int getStatus(){
    return status;
}


public float getShiftOtHour(){
    return shiftOtHour;
}


public int getChangewith(){
    return changewith;
}


public String getFromtime(){
    return fromtime;
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


public String getShiftname(){
    return shiftname;
}


public int getChangeable(){
    return changeable;
}


public int getCompanyId(){
    return companyId;
}


public String getShortName(){
    return shortName;
}


public int getEarlyGoingMin(){
    return earlyGoingMin;
}


public int getDepartmentId(){
    return departmentId;
}


}