import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.io.Serializable;
import java.util.Date;
public class HolidayMaster implements Serializable{

 private  int holidayId;

 private  Date holidayDate;

 private  String holidayName;

 private  int delStatus;


public int getHolidayId(){
    return holidayId;
}


public int getDelStatus(){
    return delStatus;
}


public String getHolidayName(){
    return holidayName;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM")
public Date getHolidayDate(){
    return holidayDate;
}


}