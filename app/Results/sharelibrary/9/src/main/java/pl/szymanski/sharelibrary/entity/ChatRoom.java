package pl.szymanski.sharelibrary.entity;
 import lombok.Data;
import javax.persistence;
import java.util.List;
import javax.persistence.CascadeType;
import pl.szymanski.sharelibrary.Request.UserRequest;
import pl.szymanski.sharelibrary.Request.Impl.UserRequestImpl;
import pl.szymanski.sharelibrary.DTO.User;
import pl.szymanski.sharelibrary.Request.UserRequest;
import pl.szymanski.sharelibrary.Request.Impl.UserRequestImpl;
import pl.szymanski.sharelibrary.DTO.User;
@Entity
@Data
@Table(name = "chat_room")
public class ChatRoom {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@OneToMany(mappedBy = "chat", cascade = { MERGE, PERSIST, REFRESH })
 private  List<ChatMessage> messages;

@Transient
 private  User sender;

@Transient
 private  User recipient;

@Column(name = "id")
 private Long id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;


}