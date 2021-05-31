import javax.persistence.Entity;
import javax.persistence.Id;
public class GetLeaveApplyAuthwise {

 private  int leaveId;

 private  int calYrId;

 private  String leaveTitle;

 private  int empId;

 private  String empName;

 private  String iniAuthEmpId;

 private  String finAuthEmpId;

 private  int exInt1;

 private  String empCode;

 private  int lvTypeId;

 private  String leaveTypeName;

 private  String leaveDuration;

 private  String leaveFromdt;

 private  String leaveTodt;

 private  float leaveNumDays;

 private  String leaveEmpReason;

 private  String circulatedTo;

 private  String empPhoto;

 private  String exVar2;


public String getLeaveTypeName(){
    return leaveTypeName;
}


public String getExVar2(){
    return exVar2;
}


public int getExInt1(){
    return exInt1;
}


public String getFinAuthEmpId(){
    return finAuthEmpId;
}


public String getLeaveTitle(){
    return leaveTitle;
}


public String getEmpName(){
    return empName;
}


public String getCirculatedTo(){
    return circulatedTo;
}


public String getLeaveFromdt(){
    return leaveFromdt;
}


public String getLeaveTodt(){
    return leaveTodt;
}


public String getLeaveDuration(){
    return leaveDuration;
}


public int getLeaveId(){
    return leaveId;
}


public String getIniAuthEmpId(){
    return iniAuthEmpId;
}


public int getEmpId(){
    return empId;
}


public String getEmpPhoto(){
    return empPhoto;
}


public String getLeaveEmpReason(){
    return leaveEmpReason;
}


public String getEmpCode(){
    return empCode;
}


public int getCalYrId(){
    return calYrId;
}


public int getLvTypeId(){
    return lvTypeId;
}


public float getLeaveNumDays(){
    return leaveNumDays;
}


}