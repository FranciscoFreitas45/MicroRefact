package kielce.tu.weaii.telelearn.controllers;
 import kielce.tu.weaii.telelearn.requests.TeacherRegisterRequest;
import kielce.tu.weaii.telelearn.requests.TeacherUpdateRequest;
import kielce.tu.weaii.telelearn.services.ports.TeacherService;
import kielce.tu.weaii.telelearn.services.ports.UserService;
import kielce.tu.weaii.telelearn.views.TeacherView;
import kielce.tu.weaii.telelearn.views.courses.CourseBriefView;
import kielce.tu.weaii.telelearn.views.courses.CourseView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Collectors.toList;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user/teacher")
public class TeacherController {

 public  TeacherService teacherService;

 public  UserService userService;


@GetMapping
public ResponseEntity<List<TeacherView>> getAll(){
    return new ResponseEntity<>(teacherService.getAll().stream().map(teacher -> TeacherView.from(teacher, false)).collect(Collectors.toList()), HttpStatus.OK);
}


@GetMapping(path = "/{userId}/courses")
public ResponseEntity<List<Object>> getCourses(Long userId){
    if (userService.isCurrentUserOrAdmin(userId)) {
        return new ResponseEntity<>(teacherService.getCourses(userId).stream().map(CourseView::from).collect(toList()), HttpStatus.OK);
    }
    return new ResponseEntity<>(teacherService.getCourses(userId).stream().map(CourseBriefView::from).collect(toList()), HttpStatus.OK);
}


@GetMapping(path = "/{id}")
public ResponseEntity<TeacherView> getById(Long id){
    return new ResponseEntity<>(TeacherView.from(teacherService.getById(id), userService.isCurrentUserOrAdmin(id)), HttpStatus.OK);
}


@PutMapping(path = "/{id}")
public ResponseEntity<Object> update(Long id,TeacherUpdateRequest request){
    teacherService.update(id, request);
    return ResponseEntity.noContent().build();
}


@PostMapping
public ResponseEntity<Object> resisterTeacher(TeacherRegisterRequest request){
    URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/api/user/teacher/{id}").buildAndExpand(teacherService.add(request).getId()).toUri();
    return ResponseEntity.created(location).build();
}


@DeleteMapping(path = "/{id}")
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<Object> deleteTeacher(Long id){
    teacherService.delete(id);
    return ResponseEntity.noContent().build();
}


}