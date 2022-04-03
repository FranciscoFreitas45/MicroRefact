package pl.szymanski.sharelibrary.entity;
 import lombok.Data;
import javax.persistence;
import java.time.LocalDateTime;
import pl.szymanski.sharelibrary.Request.UserRequest;
import pl.szymanski.sharelibrary.Request.Impl.UserRequestImpl;
import pl.szymanski.sharelibrary.DTO.User;
import pl.szymanski.sharelibrary.Request.UserRequest;
import pl.szymanski.sharelibrary.Request.Impl.UserRequestImpl;
import pl.szymanski.sharelibrary.DTO.User;
@Entity
@Data
@Table(name = "chat_message")
public class ChatMessage {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@ManyToOne
@JoinColumn(name = "roomId")
 private  ChatRoom chat;

@Transient
 private  User sender;

@Transient
 private  User recipient;

 private  String content;

 private  LocalDateTime timestamp;

@Column(name = "id")
 private Long id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;


}