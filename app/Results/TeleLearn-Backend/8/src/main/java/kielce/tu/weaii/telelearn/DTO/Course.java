package kielce.tu.weaii.telelearn.DTO;
 import kielce.tu.weaii.telelearn.models.Teacher;
import lombok.Data;
import javax.persistence;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType.ALL;
import javax.persistence.FetchType.LAZY;
import javax.persistence.GenerationType.IDENTITY;
import kielce.tu.weaii.telelearn.Request.TeacherRequest;
import kielce.tu.weaii.telelearn.Request.Impl.TeacherRequestImpl;
import kielce.tu.weaii.telelearn.DTO.Teacher;
import kielce.tu.weaii.telelearn.Request.PostRequest;
import kielce.tu.weaii.telelearn.Request.Impl.PostRequestImpl;
import kielce.tu.weaii.telelearn.DTO.Post;
public class Course implements Serializable{

 private  Long id;

 private  Teacher owner;

 private  String name;

 private  String welcomePageHtmlContent;

 private  boolean publicCourse;

 private  boolean autoAccept;

 private  boolean studentsAllowedToPost;

 private  List<CourseStudent> students;

 private  List<Post> posts;

 private  List<Task> tasks;

 private Long id;


}