package kielce.tu.weaii.telelearn.models.courses;
 import kielce.tu.weaii.telelearn.models.Attachment;
import lombok.Data;
import javax.persistence;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType.ALL;
import javax.persistence.FetchType.LAZY;
import javax.persistence.GenerationType.IDENTITY;
import kielce.tu.weaii.telelearn.Request.AttachmentRequest;
import kielce.tu.weaii.telelearn.Request.Impl.AttachmentRequestImpl;
import kielce.tu.weaii.telelearn.DTO.Attachment;
@Data
@Entity
@Table(name = "TASKS")
public class Task implements Serializable{

@Id
@GeneratedValue(strategy = IDENTITY)
@Column(insertable = false, nullable = false)
 private  Long id;

@Column(nullable = false, columnDefinition = "TEXT")
 private  String name;

@Column(columnDefinition = "TEXT")
 private  String description;

@Column(nullable = false)
 private  Duration learningTime;

@Column(nullable = false)
 private  LocalDate dueDate;

@ManyToOne(fetch = LAZY)
@JoinColumn(nullable = false, name = "COURSE_ID")
 private  Course course;

@ManyToMany()
@JoinTable(name = "TASK_LINKS", joinColumns = @JoinColumn(name = "TASK_ID"), inverseJoinColumns = @JoinColumn(name = "PREVIOUS_TASK_ID"))
 private  List<Task> previousTasks;

@ManyToMany(mappedBy = "previousTasks")
 private  List<Task> nextTasks;

@Transient
 private  List<Attachment> attachments;

@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "task")
 private  List<TaskStudent> students;

@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "task")
 private  List<TaskScheduleRecord> planRecords;

@Transient
 private AttachmentRequest attachmentrequest = new AttachmentRequestImpl();;


public TaskStudent getStudentRecordOrNull(Long studentId){
    return students.stream().filter(entry -> entry.getStudent().getId().equals(studentId)).findAny().orElse(null);
}


}