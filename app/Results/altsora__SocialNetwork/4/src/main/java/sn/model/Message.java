package sn.model;
 import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import sn.model.enums.MessageStatus;
import javax.persistence;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

 private  long id;

 private  LocalDateTime time;

 private  Person author;

 private  Person recipient;

 private  String messageText;

 private  MessageStatus status;

 private  Dialog dialog;

 private  boolean isDeleted;


@Column(name = "is_deleted", nullable = false)
public boolean isDeleted(){
    return isDeleted;
}


@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "author_id")
public Person getAuthor(){
    return author;
}


@Column(name = "message_text", nullable = false)
public String getMessageText(){
    return messageText;
}


@Column(name = "time", nullable = false)
public LocalDateTime getTime(){
    return time;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public long getId(){
    return id;
}


@Column(name = "read_status", nullable = false)
@Enumerated(EnumType.STRING)
public MessageStatus getStatus(){
    return status;
}


@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "recipient_id")
public Person getRecipient(){
    return recipient;
}


@JsonManagedReference
@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
public Dialog getDialog(){
    return dialog;
}


}