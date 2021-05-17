import lombok.Getter;
import lombok.Setter;
import pt.iscte.hospital.objects.utils.Calendar;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalTime;
public class Message {

 private  Long messageId;

 private  String subject;

 private  String message;

 private  LocalDate date;

 private  LocalTime time;

 private  boolean readMsg;

 private  User user;


public String getDateStr(){
    if (date == null) {
        return "";
    }
    return date.format(Calendar.FORMATTER);
}


}