package kielce.tu.weaii.telelearn.models;
 import lombok.Data;
import javax.persistence;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import kielce.tu.weaii.telelearn.Request.StudentRequest;
import kielce.tu.weaii.telelearn.Request.Impl.StudentRequestImpl;
import kielce.tu.weaii.telelearn.DTO.Student;
@Entity
@Data
@Table(name = "LEARNING_TIME")
public class LearningTime implements Serializable{

@EmbeddedId
 private  LearningTimeId id;

@Transient
 private  Student student;

@Column(nullable = false, insertable = false, updatable = false)
 private  LocalDate date;

@Column(nullable = false)
 private  Duration time;

@Column(name = "id")
 private Long id;

@Transient
 private StudentRequest studentrequest = new StudentRequestImpl();;


}