import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class CountData {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  String id;

 private  int present;

 private  int absent;

 private  int leavecount;

 private  int latemark;

 private  int holiday;

 private  int weeklyoff;


public int getLeavecount(){
    return leavecount;
}


public int getHoliday(){
    return holiday;
}


public int getLatemark(){
    return latemark;
}


public void setHoliday(int holiday){
    this.holiday = holiday;
}


public void setLeavecount(int leavecount){
    this.leavecount = leavecount;
}


public String getId(){
    return id;
}


public int getPresent(){
    return present;
}


public int getWeeklyoff(){
    return weeklyoff;
}


public void setWeeklyoff(int weeklyoff){
    this.weeklyoff = weeklyoff;
}


public void setAbsent(int absent){
    this.absent = absent;
}


public int getAbsent(){
    return absent;
}


public void setLatemark(int latemark){
    this.latemark = latemark;
}


public void setId(String id){
    this.id = id;
}


@Override
public String toString(){
    return "CountData [id=" + id + ", present=" + present + ", absent=" + absent + ", leavecount=" + leavecount + ", latemark=" + latemark + ", holiday=" + holiday + ", weeklyoff=" + weeklyoff + "]";
}


public void setPresent(int present){
    this.present = present;
}


}