package kielce.tu.weaii.telelearn.models.courses;
 import kielce.tu.weaii.telelearn.models.Student;
import lombok.Data;
import java.io.Serializable;
@Data
public class CourseStudentId implements Serializable{

 private  Course course;

 private  Student student;


}