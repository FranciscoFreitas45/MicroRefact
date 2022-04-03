package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JobDetailController {

 private JobDetail jobdetail;

 private JobDetail jobdetail;


@PutMapping
("/setTaskstatus")
public void setTaskstatus(@RequestParam(name = "taskstatus") String taskstatus){
jobdetail.setTaskstatus(taskstatus);
}


@PutMapping
("/setAssigned")
public void setAssigned(@RequestParam(name = "assigned") int assigned){
jobdetail.setAssigned(assigned);
}


@PutMapping
("/setNotassigned")
public void setNotassigned(@RequestParam(name = "notassigned") int notassigned){
jobdetail.setNotassigned(notassigned);
}


}