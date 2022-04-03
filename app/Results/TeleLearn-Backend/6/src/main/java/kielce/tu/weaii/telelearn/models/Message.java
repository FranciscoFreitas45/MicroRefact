package kielce.tu.weaii.telelearn.models;
 import lombok.Data;
import javax.persistence;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.FetchType.LAZY;
import javax.persistence.GenerationType.IDENTITY;
import kielce.tu.weaii.telelearn.Request.UserRequest;
import kielce.tu.weaii.telelearn.Request.Impl.UserRequestImpl;
import kielce.tu.weaii.telelearn.DTO.User;
import kielce.tu.weaii.telelearn.Request.UserRequest;
import kielce.tu.weaii.telelearn.Request.Impl.UserRequestImpl;
import kielce.tu.weaii.telelearn.DTO.User;
@Entity
@Data
@Table(name = "MESSAGES")
public class Message implements Serializable{

@Id
@GeneratedValue(strategy = IDENTITY)
@Column(insertable = false, nullable = false)
 private  Long id;

@Transient
 private  User sender;

@Transient
 private  User receiver;

@Column(nullable = false, columnDefinition = "TEXT")
 private  String content;

@Column(nullable = false)
 private  LocalDateTime sendTime;

 private  boolean read;

@Column(name = "id")
 private Long id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;


}