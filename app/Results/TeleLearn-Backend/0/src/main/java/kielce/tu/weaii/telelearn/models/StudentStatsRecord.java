package kielce.tu.weaii.telelearn.models;
 import lombok.Data;
import javax.persistence;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.FetchType.LAZY;
import javax.persistence.GenerationType.IDENTITY;
import kielce.tu.weaii.telelearn.Request.StudentRequest;
import kielce.tu.weaii.telelearn.Request.Impl.StudentRequestImpl;
import kielce.tu.weaii.telelearn.DTO.Student;
@Entity
@Data
@Table(name = "STUDENT_STATS")
public class StudentStatsRecord implements Serializable{

@Id
@GeneratedValue(strategy = IDENTITY)
 private  Long id;

@Column(nullable = false)
 private  Long scheduleId;

@Column(nullable = false)
 private  Long courseId;

@Column(nullable = false)
 private  LocalDate date;

@Column(nullable = false)
 private  LocalTime startTime;

@Column(nullable = false)
 private  Duration learningTime;

@Transient
 private  Student student;

@Column(name = "id")
 private Long id;

@Transient
 private StudentRequest studentrequest = new StudentRequestImpl();;


}