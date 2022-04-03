package kielce.tu.weaii.telelearn.models.courses;
 import kielce.tu.weaii.telelearn.models.Student;
import lombok.Data;
import javax.persistence;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.FetchType.LAZY;
import javax.persistence.GenerationType.IDENTITY;
@Data
@Entity
@Table(name = "TASK_SCHEDULE")
public class TaskScheduleRecord implements Serializable{

@Id
@GeneratedValue(strategy = IDENTITY)
@Column(insertable = false, nullable = false)
 private  Long id;

@Column(nullable = false)
 private  LocalDate date;

 private  LocalTime scheduleTime;

@Column(nullable = false)
 private  Duration plannedTime;

@Column(nullable = false)
 private  Duration learningTime;

@ManyToOne(fetch = LAZY)
@JoinColumn(nullable = false, name = "STUDENT_ID")
 private  Student student;

@ManyToOne(fetch = LAZY)
@JoinColumn(nullable = false, name = "TASK_ID")
 private  Task task;


}