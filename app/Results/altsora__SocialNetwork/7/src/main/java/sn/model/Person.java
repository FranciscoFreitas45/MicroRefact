package sn.model;
 import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import sn.Request.PostRequest;
import sn.Request.Impl.PostRequestImpl;
import sn.DTO.Post;
import sn.Request.DialogRequest;
import sn.Request.Impl.DialogRequestImpl;
import sn.DTO.Dialog;
import sn.Request.Person2DialogRequest;
import sn.Request.Impl.Person2DialogRequestImpl;
import sn.DTO.Person2Dialog;
import sn.Request.MessageRequest;
import sn.Request.Impl.MessageRequestImpl;
import sn.DTO.Message;
import sn.Request.MessageRequest;
import sn.Request.Impl.MessageRequestImpl;
import sn.DTO.Message;
@Data
@NoArgsConstructor
@Entity
@Table(name = "person")
@EqualsAndHashCode(exclude = { "posts", "likes", "ownDialogs", "dialogs", "sentMessages", "receivedMessages" })
public class Person {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  long id;

@Column(name = "first_name")
 private  String firstName;

@Column(name = "last_name")
 private  String lastName;

@CreationTimestamp
@Column(name = "reg_date", columnDefinition = "timestamp with time zone")
 private  LocalDateTime regDate;

@Column(name = "birth_date")
 private  LocalDate birthDate;

@Column(name = "e_mail")
 private  String email;

 private  String phone;

 private  String password;

 private  String photo;

 private  String about;

 private  String city;

 private  String country;

@Column(name = "confirmation_code")
 private  String confirmationCode;

@Column(name = "is_approved")
 private  boolean approved;

@Column(name = "messages_permission")
 private  String messagesPermission;

@UpdateTimestamp
@Column(name = "last_online_time", columnDefinition = "timestamp with time zone")
 private  LocalDateTime lastOnlineTime;

@Column(name = "is_blocked")
 private  boolean blocked;

@Column(name = "is_online")
 private  boolean online;

@Column(name = "is_deleted")
 private  boolean deleted;

@Transient
 private  List<Post> posts;

@JsonManagedReference
@OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
 private  Set<Like> likes;

@Transient
 private  Set<Dialog> ownDialogs;

@Transient
 private  Set<Person2Dialog> dialogs;

@Transient
 private  Set<Message> sentMessages;

@Transient
 private  Set<Message> receivedMessages;

@Transient
 private PostRequest postrequest = new PostRequestImpl();;

@Transient
 private DialogRequest dialogrequest = new DialogRequestImpl();;

@Transient
 private Person2DialogRequest person2dialogrequest = new Person2DialogRequestImpl();;

@Transient
 private MessageRequest messagerequest = new MessageRequestImpl();;

@Transient
 private MessageRequest messagerequest = new MessageRequestImpl();;


@Override
public String toString(){
    return "Person{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + '}';
}


}