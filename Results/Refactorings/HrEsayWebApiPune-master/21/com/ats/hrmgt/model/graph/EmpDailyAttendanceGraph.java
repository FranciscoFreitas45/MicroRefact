public class EmpDailyAttendanceGraph {

 private  double workingDays;

 private  double presentdays;

 private  double paidHoliday;

 private  double unpaidHoliday;

 private  double paidLeave;

 private  double unpaidLeave;

 private  double monthDays;

 private  double payableDaysDays;

 private  double weekOff;

 private  int lateMarks;

 private  int month;

 private  int year;

 private  String date;

 private  String empName;

 private  String empCode;


public double getUnpaidLeave(){
    return unpaidLeave;
}


public void setWeekOff(double weekOff){
    this.weekOff = weekOff;
}


public double getPresentdays(){
    return presentdays;
}


public double getWeekOff(){
    return weekOff;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public void setWorkingDays(double workingDays){
    this.workingDays = workingDays;
}


public String getEmpName(){
    return empName;
}


public double getPayableDaysDays(){
    return payableDaysDays;
}


public void setPresentdays(double presentdays){
    this.presentdays = presentdays;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public void setUnpaidLeave(double unpaidLeave){
    this.unpaidLeave = unpaidLeave;
}


public void setUnpaidHoliday(double unpaidHoliday){
    this.unpaidHoliday = unpaidHoliday;
}


public void setPaidHoliday(double paidHoliday){
    this.paidHoliday = paidHoliday;
}


public String getDate(){
    return date;
}


public int getMonth(){
    return month;
}


public void setMonth(int month){
    this.month = month;
}


public double getMonthDays(){
    return monthDays;
}


public void setYear(int year){
    this.year = year;
}


public void setPayableDaysDays(double payableDaysDays){
    this.payableDaysDays = payableDaysDays;
}


public double getUnpaidHoliday(){
    return unpaidHoliday;
}


public double getWorkingDays(){
    return workingDays;
}


public String getEmpCode(){
    return empCode;
}


public double getPaidLeave(){
    return paidLeave;
}


public int getYear(){
    return year;
}


public void setMonthDays(double monthDays){
    this.monthDays = monthDays;
}


public void setLateMarks(int lateMarks){
    this.lateMarks = lateMarks;
}


public void setDate(String date){
    this.date = date;
}


@Override
public String toString(){
    return "EmpDailyAttendanceGraph [workingDays=" + workingDays + ", presentdays=" + presentdays + ", paidHoliday=" + paidHoliday + ", unpaidHoliday=" + unpaidHoliday + ", paidLeave=" + paidLeave + ", unpaidLeave=" + unpaidLeave + ", monthDays=" + monthDays + ", payableDaysDays=" + payableDaysDays + ", weekOff=" + weekOff + ", lateMarks=" + lateMarks + ", month=" + month + ", year=" + year + ", date=" + date + ", empName=" + empName + ", empCode=" + empCode + "]";
}


public double getPaidHoliday(){
    return paidHoliday;
}


public void setPaidLeave(double paidLeave){
    this.paidLeave = paidLeave;
}


public int getLateMarks(){
    return lateMarks;
}


}