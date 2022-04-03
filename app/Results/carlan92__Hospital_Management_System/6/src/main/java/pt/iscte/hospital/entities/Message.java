package pt.iscte.hospital.entities;
 import lombok.Getter;
import lombok.Setter;
import pt.iscte.hospital.objects.utils.Calendar;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalTime;
import pt.iscte.hospital.Request.UserRequest;
import pt.iscte.hospital.Request.Impl.UserRequestImpl;
import pt.iscte.hospital.DTO.User;
@Entity
@Getter
@Setter
public class Message {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long messageId;

 private  String subject;

@Column(length = 2000)
 private  String message;

 private  LocalDate date;

 private  LocalTime time;

 private  boolean readMsg;

@Transient
 private  User user;

@Column(name = "userId")
 private Long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

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