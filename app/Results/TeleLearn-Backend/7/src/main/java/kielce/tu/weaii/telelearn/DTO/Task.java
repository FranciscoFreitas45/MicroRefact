package kielce.tu.weaii.telelearn.DTO;
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
public class Task implements Serializable{

 private  Long id;

 private  String name;

 private  String description;

 private  Duration learningTime;

 private  LocalDate dueDate;

 private  Course course;

 private  List<Task> previousTasks;

 private  List<Task> nextTasks;

 private  List<Attachment> attachments;

 private  List<TaskStudent> students;

 private  List<TaskScheduleRecord> planRecords;


public TaskStudent getStudentRecordOrNull(Long studentId){
    return students.stream().filter(entry -> entry.getStudent().getId().equals(studentId)).findAny().orElse(null);
}


}