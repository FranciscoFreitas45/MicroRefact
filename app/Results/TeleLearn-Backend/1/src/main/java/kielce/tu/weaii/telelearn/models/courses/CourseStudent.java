package kielce.tu.weaii.telelearn.models.courses;
 import kielce.tu.weaii.telelearn.models.Student;
import lombok.Data;
import javax.persistence;
import java.io.Serializable;
@Entity
@Data
@Table(name = "COURSE_STUDENT")
@IdClass(CourseStudentId.class)
public class CourseStudent implements Serializable{

@Id
@ManyToOne
@JoinColumn(name = "COURSE_ID", referencedColumnName = "id")
 private  Course course;

@Id
@ManyToOne
@JoinColumn(name = "STUDENT_ID", referencedColumnName = "id")
 private  Student student;

@Column(nullable = false)
 private  boolean accepted;


}