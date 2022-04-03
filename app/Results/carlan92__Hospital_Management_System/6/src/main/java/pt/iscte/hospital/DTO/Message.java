package pt.iscte.hospital.DTO;
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

 private Long userId;

// Constructor
public Message() {
    readMsg = false;
}public Message(String subject, String message, User userReceiver) {
    this.subject = subject;
    this.message = message;
    this.date = LocalDate.now();
    this.time = LocalTime.now();
    this.user = userReceiver;
    readMsg = false;
}
public String getDateStr(){
    if (date == null) {
        return "";
    }
    return date.format(Calendar.FORMATTER);
}


}