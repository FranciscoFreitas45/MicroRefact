package kielce.tu.weaii.telelearn.models;
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
import kielce.tu.weaii.telelearn.Request.TaskRequest;
import kielce.tu.weaii.telelearn.Request.Impl.TaskRequestImpl;
import kielce.tu.weaii.telelearn.DTO.Task;
@Entity
@Table(name = "ATTACHMENTS")
@Getter
@Setter
public class Attachment implements Serializable{

@Id
@GeneratedValue(strategy = IDENTITY)
@Column(insertable = false, nullable = false)
 private  Long id;

@Column(nullable = false, columnDefinition = "TEXT")
 private  String fileName;

@Column(columnDefinition = "TEXT")
 private  String fileType;

@Column(nullable = false)
 private  LocalDateTime uploadTime;

@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "attachment")
@Size(max = 1, min = 1)
 private  List<AttachmentData> attachmentData;

@ManyToOne(fetch = LAZY)
@JoinColumn(name = "POST_ID")
 private  Post post;

@Transient
 private  Task task;

@Column(name = "id")
 private Long id;

@Transient
 private TaskRequest taskrequest = new TaskRequestImpl();;


}