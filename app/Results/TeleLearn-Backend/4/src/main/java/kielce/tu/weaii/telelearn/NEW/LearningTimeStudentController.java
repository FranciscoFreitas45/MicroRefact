package kielce.tu.weaii.telelearn.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import kielce.tu.weaii.telelearn.models.LearningTime;
@RestController
@CrossOrigin
public class LearningTimeStudentController {

@Autowired
 private LearningTimeStudentService learningtimestudentservice;


}