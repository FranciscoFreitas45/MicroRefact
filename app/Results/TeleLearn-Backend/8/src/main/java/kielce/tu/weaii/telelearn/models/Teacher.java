package kielce.tu.weaii.telelearn.models;
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
import kielce.tu.weaii.telelearn.Request.CourseRequest;
import kielce.tu.weaii.telelearn.Request.Impl.CourseRequestImpl;
import kielce.tu.weaii.telelearn.DTO.Course;
@Entity
@Data
@Table(name = "TEACHERS")
@EqualsAndHashCode(callSuper = true)
public class Teacher extends User{

@Column(columnDefinition = "TEXT")
 private  String unit;

@Column(columnDefinition = "TEXT")
 private  String title;

@Transient
 private  List<Course> courses;

@Transient
 private CourseRequest courserequest = new CourseRequestImpl();;


}