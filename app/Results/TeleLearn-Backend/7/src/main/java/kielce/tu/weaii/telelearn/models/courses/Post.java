package kielce.tu.weaii.telelearn.models.courses;
 import kielce.tu.weaii.telelearn.models.Attachment;
import kielce.tu.weaii.telelearn.models.User;
import lombok.Data;
import javax.persistence;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType.ALL;
import javax.persistence.FetchType.EAGER;
import javax.persistence.FetchType.LAZY;
import javax.persistence.GenerationType.IDENTITY;
import kielce.tu.weaii.telelearn.Request.CourseRequest;
import kielce.tu.weaii.telelearn.Request.Impl.CourseRequestImpl;
import kielce.tu.weaii.telelearn.DTO.Course;
import kielce.tu.weaii.telelearn.Request.UserRequest;
import kielce.tu.weaii.telelearn.Request.Impl.UserRequestImpl;
import kielce.tu.weaii.telelearn.DTO.User;
@Data
@Entity
@Table(name = "POSTS")
public class Post implements Serializable{

@Id
@GeneratedValue(strategy = IDENTITY)
@Column(insertable = false, nullable = false)
 private  Long id;

@Column(columnDefinition = "TEXT")
 private  String content;

@JoinColumn(nullable = false)
 private  PostVisibility postVisibility;

@JoinColumn(nullable = false)
 private  LocalDateTime publicationTime;

@JoinColumn(nullable = false)
 private  boolean commentingAllowed;

@Transient
 private  Course course;

@Transient
 private  User author;

@OneToMany(fetch = LAZY, cascade = ALL, orphanRemoval = true, mappedBy = "post")
 private  List<Attachment> attachments;

@OneToMany(fetch = LAZY, mappedBy = "post", cascade = ALL)
 private  List<Comment> comments;

@Column(name = "id")
 private Long id;

@Transient
 private CourseRequest courserequest = new CourseRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;


}