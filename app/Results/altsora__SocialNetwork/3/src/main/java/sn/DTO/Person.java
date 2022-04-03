package sn.DTO;
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
public class Person {

 private  long id;

 private  String firstName;

 private  String lastName;

 private  LocalDateTime regDate;

 private  LocalDate birthDate;

 private  String email;

 private  String phone;

 private  String password;

 private  String photo;

 private  String about;

 private  String city;

 private  String country;

 private  String confirmationCode;

 private  boolean approved;

 private  String messagesPermission;

 private  LocalDateTime lastOnlineTime;

 private  boolean blocked;

 private  boolean online;

 private  boolean deleted;

 private  List<Post> posts;

 private  Set<Like> likes;

 private  Set<Dialog> ownDialogs;

 private  Set<Person2Dialog> dialogs;

 private  Set<Message> sentMessages;

 private  Set<Message> receivedMessages;


}