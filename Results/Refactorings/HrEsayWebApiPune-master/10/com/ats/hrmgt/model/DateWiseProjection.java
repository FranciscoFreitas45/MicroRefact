public class DateWiseProjection {

 private  String date;

 private  int shiftId;

 private  String shiftName;


public void setShiftId(int shiftId){
    this.shiftId = shiftId;
}


public void setShiftName(String shiftName){
    this.shiftName = shiftName;
}


public void setDate(String date){
    this.date = date;
}


public String getDate(){
    return date;
}


@Override
public String toString(){
    return "DateWiseProjection [date=" + date + ", shiftId=" + shiftId + ", shiftName=" + shiftName + "]";
}


public int getShiftId(){
    return shiftId;
}


public String getShiftName(){
    return shiftName;
}


}