package kielce.tu.weaii.telelearn.DTO;
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
public class Post implements Serializable{

 private  Long id;

 private  String content;

 private  PostVisibility postVisibility;

 private  LocalDateTime publicationTime;

 private  boolean commentingAllowed;

 private  Course course;

 private  User author;

 private  List<Attachment> attachments;

 private  List<Comment> comments;

 private Long id;

 private CourseRequest courserequest = new CourseRequestImpl();;

 private Long id;

 private UserRequest userrequest = new UserRequestImpl();;


}