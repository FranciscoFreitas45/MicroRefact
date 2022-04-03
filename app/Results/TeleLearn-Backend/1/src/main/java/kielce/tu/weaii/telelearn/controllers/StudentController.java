package kielce.tu.weaii.telelearn.controllers;
 import kielce.tu.weaii.telelearn.requests.StudentRegisterRequest;
import kielce.tu.weaii.telelearn.requests.StudentUpdateRequest;
import kielce.tu.weaii.telelearn.services.ports;
import kielce.tu.weaii.telelearn.views.StudentView;
import kielce.tu.weaii.telelearn.views.courses.CourseView;
import kielce.tu.weaii.telelearn.views.courses.TaskScheduleView;
import kielce.tu.weaii.telelearn.views.courses.TaskToScheduleRecordView;
import kielce.tu.weaii.telelearn.views.courses.TasksToScheduleView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors.toList;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user/student")
public class StudentController {

 private  StudentService studentService;

 private  UserService userService;

 private  TaskService taskService;

 private  TaskScheduleService taskScheduleService;

 private  StudentStatJsonCreator studentStatJsonCreator;


@GetMapping(path = "/{id}/stat", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("hasAnyRole('STUDENT')")
public ResponseEntity<String> getStudentStat(Long id){
    return new ResponseEntity<>(studentStatJsonCreator.getStudentStatJson(id, LocalDate.now()), HttpStatus.OK);
}


@GetMapping(path = "/{userId}/courses")
@PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')")
public ResponseEntity<List<CourseView>> getCourses(Long userId){
    return new ResponseEntity<>(studentService.getCourses(userId).stream().map(CourseView::from).collect(toList()), HttpStatus.OK);
}


@GetMapping(path = "/{id}")
public ResponseEntity<StudentView> getById(Long id){
    return new ResponseEntity<>(StudentView.from(studentService.getById(id), userService.isCurrentUserOrAdmin(id)), HttpStatus.OK);
}


@PutMapping(path = "/{id}")
public ResponseEntity<Object> update(Long id,StudentUpdateRequest request){
    studentService.update(id, request);
    return ResponseEntity.noContent().build();
}


@DeleteMapping(path = "/{id}")
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<Object> deleteStudent(Long id){
    studentService.delete(id);
    return ResponseEntity.noContent().build();
}


@GetMapping(path = "/{id}/tasks")
public ResponseEntity<Map<String,List<TaskToScheduleRecordView>>> getStudentTasks(Long id){
    return new ResponseEntity<>(TasksToScheduleView.from(taskService.getStudentByTasksFromCurse(id, LocalDate.now()), id), HttpStatus.OK);
}


@PostMapping
public ResponseEntity<Object> resisterStudent(StudentRegisterRequest request){
    URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/api/user/student/{id}").buildAndExpand(studentService.add(request).getId()).toUri();
    return ResponseEntity.created(location).build();
}


@GetMapping(path = "/{id}/schedule")
public ResponseEntity<Map<String,List<TaskScheduleView>>> getSchedule(Long id){
    return new ResponseEntity<>(TaskScheduleView.form(taskScheduleService.getListForStudent(id)), HttpStatus.OK);
}


}