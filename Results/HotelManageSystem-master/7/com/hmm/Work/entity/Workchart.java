public class Workchart {

 private  Integer quarter;

 private  Long late;

 private  Long leaveEarly;

 private  Long lackcard;

 private  Long leave;

 private  Long travel;


public Long getLeaveEarly(){
    return leaveEarly;
}


public void setLate(Long late){
    this.late = late;
}


public Long getLackcard(){
    return lackcard;
}


public Integer getQuarter(){
    return quarter;
}


public void setQuarter(Integer quarter){
    this.quarter = quarter;
}


public void setLeave(Long leave){
    this.leave = leave;
}


public void setLeaveEarly(Long leaveEarly){
    this.leaveEarly = leaveEarly;
}


public Long getLate(){
    return late;
}


public Long getTravel(){
    return travel;
}


public void setTravel(Long travel){
    this.travel = travel;
}


public void setLackcard(Long lackcard){
    this.lackcard = lackcard;
}


public Long getLeave(){
    return leave;
}


}