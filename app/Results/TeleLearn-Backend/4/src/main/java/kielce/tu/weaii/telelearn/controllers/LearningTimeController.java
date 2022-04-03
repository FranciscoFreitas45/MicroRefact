package kielce.tu.weaii.telelearn.controllers;
 import kielce.tu.weaii.telelearn.requests.LearningTimeRequest;
import kielce.tu.weaii.telelearn.services.ports.LearningTimeService;
import kielce.tu.weaii.telelearn.views.LearningTimeView;
import kielce.tu.weaii.telelearn.views.TimeVew;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation;
import javax.validation.Valid;
import java.util.Map;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/learning-time")
public class LearningTimeController {

 private  LearningTimeService learningTimeService;


@GetMapping(path = "/{student-id}")
@PreAuthorize("hasAnyRole('STUDENT')")
public ResponseEntity<Map<String,TimeVew>> getLearningTime(Long id){
    return new ResponseEntity<>(LearningTimeView.from(learningTimeService.getForStudent(id)), HttpStatus.OK);
}


@PutMapping
@PreAuthorize("hasAnyRole('STUDENT')")
public ResponseEntity<Object> setDate(LearningTimeRequest request){
    learningTimeService.setLearningTime(request);
    return ResponseEntity.noContent().build();
}


}