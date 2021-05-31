import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "holiday_master")
public class HolidayMaster implements Serializable{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "holiday_id")
 private  int holidayId;

@Column(name = "holiday_date")
 private  Date holidayDate;

@Column(name = "holiday_name")
 private  String holidayName;

@Column(name = "del_status")
 private  int delStatus;


public void setHolidayDate(Date holidayDate){
    this.holidayDate = holidayDate;
}


public int getHolidayId(){
    return holidayId;
}


public int getDelStatus(){
    return delStatus;
}


public void setHolidayName(String holidayName){
    this.holidayName = holidayName;
}


public String getHolidayName(){
    return holidayName;
}


@Override
public String toString(){
    return "HolidayMaster [holidayId=" + holidayId + ", holidayDate=" + holidayDate + ", holidayName=" + holidayName + ", delStatus=" + delStatus + "]";
}


public void setHolidayId(int holidayId){
    this.holidayId = holidayId;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM")
public Date getHolidayDate(){
    return holidayDate;
}


}