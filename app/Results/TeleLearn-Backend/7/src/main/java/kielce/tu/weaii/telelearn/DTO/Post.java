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


}