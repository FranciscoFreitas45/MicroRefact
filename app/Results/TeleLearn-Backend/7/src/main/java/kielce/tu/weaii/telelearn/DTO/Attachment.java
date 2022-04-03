package kielce.tu.weaii.telelearn.DTO;
 import kielce.tu.weaii.telelearn.models.courses.Post;
import kielce.tu.weaii.telelearn.models.courses.Task;
import lombok.Getter;
import lombok.Setter;
import javax.persistence;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType.ALL;
import javax.persistence.FetchType.LAZY;
import javax.persistence.GenerationType.IDENTITY;
public class Attachment implements Serializable{

 private  Long id;

 private  String fileName;

 private  String fileType;

 private  LocalDateTime uploadTime;

 private  List<AttachmentData> attachmentData;

 private  Post post;

 private  Task task;

 private Long id;


}