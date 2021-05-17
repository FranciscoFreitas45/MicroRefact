public class EmpAdvanceGraph {

 private  double advanceAmt;

 private  int month;

 private  int year;

 private  String date;


public void setMonth(int month){
    this.month = month;
}


public double getAdvanceAmt(){
    return advanceAmt;
}


public int getYear(){
    return year;
}


public void setDate(String date){
    this.date = date;
}


public void setAdvanceAmt(double advanceAmt){
    this.advanceAmt = advanceAmt;
}


public String getDate(){
    return date;
}


@Override
public String toString(){
    return "EmpAdvanceGraph [advanceAmt=" + advanceAmt + ", month=" + month + ", year=" + year + ", date=" + date + "]";
}


public int getMonth(){
    return month;
}


public void setYear(int year){
    this.year = year;
}


}