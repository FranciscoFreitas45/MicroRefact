package kielce.tu.weaii.telelearn.models;
 import lombok.Data;
import javax.persistence;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.FetchType.EAGER;
import javax.persistence.FetchType.LAZY;
import javax.persistence.GenerationType.IDENTITY;
import kielce.tu.weaii.telelearn.Request.UserRequest;
import kielce.tu.weaii.telelearn.Request.Impl.UserRequestImpl;
import kielce.tu.weaii.telelearn.DTO.User;
@Entity
@Data
@Table(name = "GLOBAL_NEWS")
public class GlobalNews implements Serializable{

@Id
@GeneratedValue(strategy = IDENTITY)
@Column(insertable = false, nullable = false)
 private  Long id;

@Column(nullable = false, columnDefinition = "TEXT")
 private  String title;

@Transient
 private  User author;

@Column(columnDefinition = "TEXT")
@Basic(fetch = LAZY)
 private  String brief;

@Column(columnDefinition = "TEXT")
@Basic(fetch = LAZY)
 private  String htmlContent;

@Column(nullable = false)
 private  LocalDateTime publicationDate;

@Column(name = "id")
 private Long id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;


}