package kielce.tu.weaii.telelearn.models.courses;
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
@Entity
@Data
@Table(name = "COURSES")
public class Course implements Serializable{

@Id
@GeneratedValue(strategy = IDENTITY)
@Column(insertable = false, nullable = false)
 private  Long id;

@Transient
 private  Teacher owner;

@Column(columnDefinition = "TEXT")
 private  String name;

@Column(columnDefinition = "TEXT")
@Basic(fetch = LAZY)
 private  String welcomePageHtmlContent;

@Column(nullable = false)
 private  boolean publicCourse;

@Column(nullable = false)
 private  boolean autoAccept;

@Column(nullable = false)
 private  boolean studentsAllowedToPost;

@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "course")
 private  List<CourseStudent> students;

@Transient
 private  List<Post> posts;

@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "course")
 private  List<Task> tasks;

@Column(name = "id")
 private Long id;

@Transient
 private TeacherRequest teacherrequest = new TeacherRequestImpl();;

@Transient
 private PostRequest postrequest = new PostRequestImpl();;


}