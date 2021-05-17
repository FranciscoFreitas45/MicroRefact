public class WorkRecord {

 public  int totalLate;

 public  int totalleaveEarly;

 public  int totalCard;

 public  float exactlyTime;

 public  float attenceTotalTime;

 public  float worktime;

 public  float overtime;

 public  float leaveTimes;

 public  float travelAttence;


public int getTotalCard(){
    return totalCard;
}


public void setTotalCard(int totalCard){
    this.totalCard = totalCard;
}


public void setExactlyTime(float exactlyTime){
    this.exactlyTime = exactlyTime;
}


public float getOvertime(){
    return overtime;
}


public void setAttenceTotalTime(float attenceTotalTime){
    this.attenceTotalTime = attenceTotalTime;
}


public float getWorktime(){
    return worktime;
}


public float getTravelAttence(){
    return travelAttence;
}


public void setOvertime(float overtime){
    this.overtime = overtime;
}


public float getExactlyTime(){
    return exactlyTime;
}


public void setLeaveTimes(float leaveTimes){
    this.leaveTimes = leaveTimes;
}


public void setWorktime(float worktime){
    this.worktime = worktime;
}


public int getTotalLate(){
    return totalLate;
}


public void setTotalleaveEarly(int totalleaveEarly){
    this.totalleaveEarly = totalleaveEarly;
}


public int getTotalleaveEarly(){
    return totalleaveEarly;
}


public float getAttenceTotalTime(){
    return attenceTotalTime;
}


public void setTravelAttence(float travelAttence){
    this.travelAttence = travelAttence;
}


public void setTotalLate(int totalLate){
    this.totalLate = totalLate;
}


public float getLeaveTimes(){
    return leaveTimes;
}


}