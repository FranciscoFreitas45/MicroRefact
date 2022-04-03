package kielce.tu.weaii.telelearn.controllers;
 import kielce.tu.weaii.telelearn.models.User;
import kielce.tu.weaii.telelearn.models.UserRole;
import kielce.tu.weaii.telelearn.models.courses.Task;
import kielce.tu.weaii.telelearn.requests.courses.CourseRequest;
import kielce.tu.weaii.telelearn.requests.courses.CourseStudentRequest;
import kielce.tu.weaii.telelearn.security.UserServiceDetailsImpl;
import kielce.tu.weaii.telelearn.services.ports.CourseService;
import kielce.tu.weaii.telelearn.services.ports.PostService;
import kielce.tu.weaii.telelearn.views.courses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {

 private  CourseService courseService;

 private  PostService postService;

 private  UserServiceDetailsImpl userServiceDetails;


@PostMapping
@PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
public ResponseEntity<Object> add(CourseRequest request){
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(courseService.add(request).getId()).toUri();
    return ResponseEntity.created(location).build();
}


@PutMapping(path = "/{courseId}/accept-student")
@PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
public ResponseEntity<Object> acceptStudent(Long courseId,CourseStudentRequest request){
    courseService.acceptStudent(courseId, request.getStudentId());
    return ResponseEntity.noContent().build();
}


@GetMapping(path = "/{id}/task")
public ResponseEntity<List<TaskView>> getCourseTasks(Long id){
    return new ResponseEntity<>(courseService.getById(id).getTasks().stream().map(getTaskViewConverter()).collect(Collectors.toList()), HttpStatus.OK);
}


@GetMapping("/{id}")
public ResponseEntity<CourseView> getById(Long id){
    return new ResponseEntity<>(CourseView.from(courseService.getById(id)), HttpStatus.OK);
}


@PutMapping(path = "/{courseId}/sign-out")
public ResponseEntity<Object> signOutStudent(Long courseId,CourseStudentRequest request){
    courseService.signOutStudent(courseId, request.getStudentId());
    return ResponseEntity.noContent().build();
}


@PutMapping("/{id}")
@PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
public ResponseEntity<Object> update(Long id,CourseRequest request){
    courseService.update(id, request);
    return ResponseEntity.noContent().build();
}


@GetMapping(path = "/{id}/brief")
public ResponseEntity<CourseBriefView> getBriefById(Long id){
    return new ResponseEntity<>(CourseBriefView.from(courseService.getCourse(id)), HttpStatus.OK);
}


@DeleteMapping("/{id}")
@PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
public ResponseEntity<Object> delete(Long id){
    courseService.delete(id);
    return ResponseEntity.noContent().build();
}


@PutMapping(path = "/{courseId}/sign-up")
public ResponseEntity<CourseSignUpResponse> signUpStudent(Long courseId,CourseStudentRequest request){
    return new ResponseEntity<>(new CourseSignUpResponse(courseService.signUpStudent(courseId, request.getStudentId())), HttpStatus.OK);
}


public Function<Task,TaskView> getTaskViewConverter(){
    User currentUser = userServiceDetails.getCurrentUser();
    if (currentUser.getUserRole().equals(UserRole.STUDENT)) {
        return model -> TaskViewForStudent.from(model, currentUser.getId());
    }
    return TaskView::from;
}


@GetMapping(path = "/{id}/post")
public ResponseEntity<List<PostView>> getCoursePosts(Long id){
    return new ResponseEntity<>(postService.getCoursePosts(id).stream().map(PostView::from).collect(Collectors.toList()), HttpStatus.OK);
}


}