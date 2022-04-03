package kielce.tu.weaii.telelearn.views.courses;
 import com.fasterxml.jackson.annotation.JsonProperty;
import kielce.tu.weaii.telelearn.models.courses.Course;
import kielce.tu.weaii.telelearn.models.courses.CourseStudent;
import kielce.tu.weaii.telelearn.views.StudentView;
import kielce.tu.weaii.telelearn.views.TeacherView;
import lombok.Getter;
import java.util.List;
import java.util.stream.Collectors;
@Getter
public class CourseView extends CourseBriefView{

 private  String welcomePageHtmlContent;

@JsonProperty("areStudentsAllowedToPost")
 private  boolean studentsAllowedToPost;

 private  List<StudentView> allowedStudents;

 private  List<StudentView> requestedStudents;

@JsonProperty("isPublicCourse")
 private  boolean publicCourse;

private CourseView(Long id, String name, TeacherView owner, boolean autoAccept, String welcomePageHtmlContent, boolean studentsAllowedToPost, List<StudentView> allowedStudents, List<StudentView> requestedStudents, boolean publicCourse) {
    super(id, name, owner, autoAccept);
    this.welcomePageHtmlContent = welcomePageHtmlContent;
    this.studentsAllowedToPost = studentsAllowedToPost;
    this.allowedStudents = allowedStudents;
    this.requestedStudents = requestedStudents;
    this.publicCourse = publicCourse;
}
public CourseView from(Course model){
    return new CourseView(model.getId(), model.getName(), TeacherView.from(model.getOwner(), false), model.isAutoAccept(), model.getWelcomePageHtmlContent(), model.isStudentsAllowedToPost(), model.getStudents().stream().filter(CourseStudent::isAccepted).map(CourseStudent::getStudent).map(student -> StudentView.from(student, false)).collect(Collectors.toList()), model.getStudents().stream().filter(entry -> !entry.isAccepted()).map(CourseStudent::getStudent).map(student -> StudentView.from(student, false)).collect(Collectors.toList()), model.isPublicCourse());
}


}