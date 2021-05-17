public class LeaveStsAndLeaveId {

 private  int sts;

 private  int duration;

 private  int leaveId;

 private  String msg;

 private  int leaveTyId;

 private  float noOfLeave;

 private  int lvTypeId;

 private  String stsshortname;


public void setDuration(int duration){
    this.duration = duration;
}


public String getStsshortname(){
    return stsshortname;
}


public String getMsg(){
    return msg;
}


public void setLvTypeId(int lvTypeId){
    this.lvTypeId = lvTypeId;
}


public void setStsshortname(String stsshortname){
    this.stsshortname = stsshortname;
}


public int getLeaveId(){
    return leaveId;
}


public void setSts(int sts){
    this.sts = sts;
}


public int getLeaveTyId(){
    return leaveTyId;
}


public void setLeaveTyId(int leaveTyId){
    this.leaveTyId = leaveTyId;
}


public int getSts(){
    return sts;
}


public int getDuration(){
    return duration;
}


public void setLeaveId(int leaveId){
    this.leaveId = leaveId;
}


public int getLvTypeId(){
    return lvTypeId;
}


public float getNoOfLeave(){
    return noOfLeave;
}


public void setNoOfLeave(float noOfLeave){
    this.noOfLeave = noOfLeave;
}


@Override
public String toString(){
    return "LeaveStsAndLeaveId [sts=" + sts + ", duration=" + duration + ", leaveId=" + leaveId + ", msg=" + msg + ", leaveTyId=" + leaveTyId + ", noOfLeave=" + noOfLeave + ", lvTypeId=" + lvTypeId + ", stsshortname=" + stsshortname + "]";
}


public void setMsg(String msg){
    this.msg = msg;
}


}