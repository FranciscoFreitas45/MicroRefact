package kielce.tu.weaii.telelearn.models.courses;
 import kielce.tu.weaii.telelearn.models.User;
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
@Data
@Entity
@Table(name = "COMMENTS")
public class Comment implements Serializable{

@Id
@GeneratedValue(strategy = IDENTITY)
@Column(insertable = false, nullable = false)
 private  Long id;

@Column(nullable = false, columnDefinition = "TEXT")
 private  String content;

@JoinColumn(nullable = false)
 private  LocalDateTime publicationTime;

@Transient
 private  User author;

@ManyToOne(fetch = LAZY)
@JoinColumn(nullable = false, name = "postId")
 private  Post post;

@Column(name = "id")
 private Long id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;


}