package kielce.tu.weaii.telelearn.DTO;
 import lombok.Data;
import javax.persistence;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.FetchType.LAZY;
import javax.persistence.GenerationType.IDENTITY;
import kielce.tu.weaii.telelearn.Request.UserRequest;
import kielce.tu.weaii.telelearn.Request.Impl.UserRequestImpl;
import kielce.tu.weaii.telelearn.DTO.User;
public class Message implements Serializable{

 private  Long id;

 private  User sender;

 private  User receiver;

 private  String content;

 private  LocalDateTime sendTime;

 private  boolean read;

 private Long id;

 private UserRequest userrequest = new UserRequestImpl();;

 private Long id;


}