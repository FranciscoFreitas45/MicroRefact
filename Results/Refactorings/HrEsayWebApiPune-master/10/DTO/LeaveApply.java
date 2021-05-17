import javax.persistence;
public class LeaveApply {

 private  int leaveId;

 private  int calYrId;

 private  int empId;

 private  int lvTypeId;

 private  String leaveDuration;

 private  String leaveFromdt;

 private  String leaveTodt;

 private  float leaveNumDays;

 private  String leaveEmpReason;

 private  int finalStatus;

 private  String circulatedTo;

 private  int delStatus;

 private  int isActive;

 private  int makerUserId;

 private  String makerEnterDatetime;

 private  int exInt1;

 private  int exInt2;

 private  int exInt3;

 private  String exVar1;

 private  String exVar2;

 private  String exVar3;

 private  int status;

 private  String leaveCancleRemark;

 private  int lvtApplicationIdParent;

 private  String recStatus;

 private  boolean error;


public String getExVar2(){
    return exVar2;
}


public String getExVar3(){
    return exVar3;
}


public String getExVar1(){
    return exVar1;
}


public int getStatus(){
    return status;
}


public String getRecStatus(){
    return recStatus;
}


public String getLeaveTodt(){
    return leaveTodt;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public String getLeaveCancleRemark(){
    return leaveCancleRemark;
}


public int getLvtApplicationIdParent(){
    return lvtApplicationIdParent;
}


public int getLeaveId(){
    return leaveId;
}


public int getEmpId(){
    return empId;
}


public int getCalYrId(){
    return calYrId;
}


public int getLvTypeId(){
    return lvTypeId;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt3(){
    return exInt3;
}


public int getExInt1(){
    return exInt1;
}


public String getCirculatedTo(){
    return circulatedTo;
}


public String getLeaveFromdt(){
    return leaveFromdt;
}


public int getMakerUserId(){
    return makerUserId;
}


public String getLeaveDuration(){
    return leaveDuration;
}


public String getLeaveEmpReason(){
    return leaveEmpReason;
}


public int getFinalStatus(){
    return finalStatus;
}


public int getIsActive(){
    return isActive;
}


public int getDelStatus(){
    return delStatus;
}


public float getLeaveNumDays(){
    return leaveNumDays;
}


}