package kielce.tu.weaii.telelearn.DTO;
 import kielce.tu.weaii.telelearn.models.courses.Course;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.CascadeType.ALL;
import javax.persistence.FetchType.LAZY;
public class Teacher extends User{

 private  String unit;

 private  String title;

 private  List<Course> courses;


}