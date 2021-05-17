import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class CommonUtility {

 private  String DATE_AND_TIME_FORMAT_01;

 private  String DATE_ONLY_FORMAT_01;


public String currentDate(){
    DateFormat df = new SimpleDateFormat(DATE_ONLY_FORMAT_01);
    Calendar calobj = Calendar.getInstance();
    return df.format(calobj.getTime());
}


public void main(String[] args){
    System.out.println(currentDateAndTime());
    System.out.println(currentDate());
}


public String currentDateAndTime(){
    DateFormat df = new SimpleDateFormat(DATE_AND_TIME_FORMAT_01);
    Calendar calobj = Calendar.getInstance();
    return df.format(calobj.getTime());
}


}