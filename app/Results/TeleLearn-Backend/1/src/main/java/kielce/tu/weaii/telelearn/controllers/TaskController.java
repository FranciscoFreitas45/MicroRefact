package kielce.tu.weaii.telelearn.controllers;
 import kielce.tu.weaii.telelearn.models.User;
import kielce.tu.weaii.telelearn.models.UserRole;
import kielce.tu.weaii.telelearn.models.courses.Task;
import kielce.tu.weaii.telelearn.requests.courses.TaskProgressPatchRequest;
import kielce.tu.weaii.telelearn.requests.courses.TaskRepeatPatchRequest;
import kielce.tu.weaii.telelearn.requests.courses.TaskRequest;
import kielce.tu.weaii.telelearn.security.UserServiceDetailsImpl;
import kielce.tu.weaii.telelearn.services.ports.TaskScheduleService;
import kielce.tu.weaii.telelearn.services.ports.TaskService;
import kielce.tu.weaii.telelearn.views.courses.TaskScheduleView;
import kielce.tu.weaii.telelearn.views.courses.TaskView;
import kielce.tu.weaii.telelearn.views.courses.TaskViewForStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/task")
public class TaskController {

 private  TaskService taskService;

 private  UserServiceDetailsImpl userServiceDetails;

 private  TaskScheduleService taskScheduleService;


@PostMapping
@PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
public ResponseEntity<Object> add(TaskRequest request,List<MultipartFile> files){
    try {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(taskService.add(request, files).getId()).toUri();
        return ResponseEntity.created(location).build();
    } catch (IOException e) {
        return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


@GetMapping(path = "/{id}")
public ResponseEntity<TaskView> getById(Long id){
    return new ResponseEntity<>(getTaskViewConverter().apply(taskService.getById(id)), HttpStatus.OK);
}


@PatchMapping(path = "/{id}/repeat")
public ResponseEntity<Object> setRepeat(Long id,TaskRepeatPatchRequest request){
    taskService.updateTaskRepeat(id, request);
    return ResponseEntity.noContent().build();
}


@PatchMapping(path = "/{id}/progress")
public ResponseEntity<Object> setProgress(Long id,TaskProgressPatchRequest request){
    taskService.updateProgress(id, request);
    return ResponseEntity.noContent().build();
}


@PutMapping(path = "/{id}")
@PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
public ResponseEntity<Object> update(Long id,TaskRequest request,List<MultipartFile> files){
    try {
        taskService.update(id, request, files);
    } catch (IOException e) {
        return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return ResponseEntity.noContent().build();
}


@GetMapping(path = "/{id}/schedule")
@PreAuthorize("hasRole('STUDENT')")
public ResponseEntity<List<TaskScheduleView>> getSchedule(Long id){
    return new ResponseEntity<>(taskScheduleService.getListForTaskAndStudent(userServiceDetails.getCurrentUser().getId(), id).stream().map(TaskScheduleView::form).collect(Collectors.toList()), HttpStatus.OK);
}


@DeleteMapping(path = "/{id}")
@PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
public ResponseEntity<Object> remove(Long id){
    taskService.delete(id);
    return ResponseEntity.noContent().build();
}


public Function<Task,TaskView> getTaskViewConverter(){
    User currentUser = userServiceDetails.getCurrentUser();
    if (currentUser.getUserRole().equals(UserRole.STUDENT)) {
        return model -> TaskViewForStudent.from(model, currentUser.getId());
    }
    return TaskView::from;
}


}