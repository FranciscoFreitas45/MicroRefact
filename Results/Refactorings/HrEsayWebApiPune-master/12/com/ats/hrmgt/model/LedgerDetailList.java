public class LedgerDetailList {

 private  String captionName;

 private  String monthYear;

 private  String monthName;

 private  float paid;

 private  float returnAmt;


public void setPaid(float paid){
    this.paid = paid;
}


public String getMonthYear(){
    return monthYear;
}


public void setMonthYear(String monthYear){
    this.monthYear = monthYear;
}


public float getPaid(){
    return paid;
}


public float getReturnAmt(){
    return returnAmt;
}


public void setReturnAmt(float returnAmt){
    this.returnAmt = returnAmt;
}


public String getMonthName(){
    return monthName;
}


public String getCaptionName(){
    return captionName;
}


@Override
public String toString(){
    return "LedgerDetailList [captionName=" + captionName + ", monthYear=" + monthYear + ", monthName=" + monthName + ", paid=" + paid + ", returnAmt=" + returnAmt + "]";
}


public void setCaptionName(String captionName){
    this.captionName = captionName;
}


public void setMonthName(String monthName){
    this.monthName = monthName;
}


}