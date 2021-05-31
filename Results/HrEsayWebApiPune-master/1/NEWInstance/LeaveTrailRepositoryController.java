import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class LeaveTrailRepositoryController {

 private LeaveTrailRepository leavetrailrepository;


@GetMapping
("/deleteByLeaveId")
public int deleteByLeaveId(@RequestParam(name = "leaveId") int leaveId){
  return leavetrailrepository.deleteByLeaveId(leaveId);
}


@GetMapping
("/saveAndFlush")
public Object saveAndFlush(@RequestParam(name = "Object") Object Object){
  return leavetrailrepository.saveAndFlush(Object);
}


}