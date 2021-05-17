import lombok.Getter;
import lombok.Setter;
import pt.iscte.hospital.objects.utils.Calendar;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalTime;
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

@Column(name = userId)
 private Long userId;

@Transient
 private UserRequestImpl userrequestimpl;


public String getDateStr(){
    if (date == null) {
        return "";
    }
    return date.format(Calendar.FORMATTER);
}


}