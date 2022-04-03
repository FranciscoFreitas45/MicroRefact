package kielce.tu.weaii.telelearn.models.courses;
 import kielce.tu.weaii.telelearn.models.Student;
import lombok.Data;
import java.io.Serializable;
@Data
public class TaskStudentId implements Serializable{

 private  Task task;

 private  Student student;


}