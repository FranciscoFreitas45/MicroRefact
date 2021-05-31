public class DailyDailyInfomationForChart {

 private  String date;

 private  String inTime;

 private  String outTime;

 private  String lateMin;

 private  String workingMin;

 private  String otMin;

 private  String status;

 private  String statusShwo;


public void setWorkingMin(String workingMin){
    this.workingMin = workingMin;
}


public String getWorkingMin(){
    return workingMin;
}


public String getStatus(){
    return status;
}


public void setStatusShwo(String statusShwo){
    this.statusShwo = statusShwo;
}


public void setStatus(String status){
    this.status = status;
}


public String getLateMin(){
    return lateMin;
}


public String getInTime(){
    return inTime;
}


public void setOtMin(String otMin){
    this.otMin = otMin;
}


public String getOutTime(){
    return outTime;
}


public String getOtMin(){
    return otMin;
}


public void setDate(String date){
    this.date = date;
}


public String getDate(){
    return date;
}


public void setOutTime(String outTime){
    this.outTime = outTime;
}


@Override
public String toString(){
    return "DailyDailyInfomationForChart [date=" + date + ", inTime=" + inTime + ", outTime=" + outTime + ", lateMin=" + lateMin + ", workingMin=" + workingMin + ", otMin=" + otMin + ", status=" + status + ", statusShwo=" + statusShwo + "]";
}


public void setInTime(String inTime){
    this.inTime = inTime;
}


public void setLateMin(String lateMin){
    this.lateMin = lateMin;
}


public String getStatusShwo(){
    return statusShwo;
}


}