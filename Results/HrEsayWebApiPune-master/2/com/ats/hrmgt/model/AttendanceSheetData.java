import java.util.List;
public class AttendanceSheetData {

 private  List<String> dates;

 private  List<EmpInfoWithDateInfoList> infomationList;

 private  List<DateAndDay> dateAndDayList;


public List<DateAndDay> getDateAndDayList(){
    return dateAndDayList;
}


public List<String> getDates(){
    return dates;
}


public void setDates(List<String> dates){
    this.dates = dates;
}


public List<EmpInfoWithDateInfoList> getInfomationList(){
    return infomationList;
}


@Override
public String toString(){
    return "AttendanceSheetData [dates=" + dates + ", infomationList=" + infomationList + ", dateAndDayList=" + dateAndDayList + "]";
}


public void setInfomationList(List<EmpInfoWithDateInfoList> infomationList){
    this.infomationList = infomationList;
}


public void setDateAndDayList(List<DateAndDay> dateAndDayList){
    this.dateAndDayList = dateAndDayList;
}


}