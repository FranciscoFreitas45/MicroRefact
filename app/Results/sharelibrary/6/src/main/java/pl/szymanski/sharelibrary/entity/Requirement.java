package pl.szymanski.sharelibrary.entity;
 import lombok.Data;
import javax.persistence;
import java.time.LocalDateTime;
import pl.szymanski.sharelibrary.Request.ExchangeRequest;
import pl.szymanski.sharelibrary.Request.Impl.ExchangeRequestImpl;
import pl.szymanski.sharelibrary.DTO.Exchange;
import pl.szymanski.sharelibrary.Request.UserRequest;
import pl.szymanski.sharelibrary.Request.Impl.UserRequestImpl;
import pl.szymanski.sharelibrary.DTO.User;
@Entity
@Data
@Table(name = "requirement")
public class Requirement {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Transient
 private  Exchange exchange;

@Transient
 private  User user;

 private  LocalDateTime createTime;

 private  boolean isActual;

@Column(name = "id")
 private Long id;

@Transient
 private ExchangeRequest exchangerequest = new ExchangeRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;


}