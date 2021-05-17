import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class LeaveStructureDetailsRepoController {

 private LeaveStructureDetailsRepo leavestructuredetailsrepo;


@GetMapping
("/findByLvsIdAndDelStatus")
public List<LeaveStructureDetails> findByLvsIdAndDelStatus(@RequestParam(name = "lvsId") int lvsId,@RequestParam(name = "i") int i){
  return leavestructuredetailsrepo.findByLvsIdAndDelStatus(lvsId,i);
}


@GetMapping
("/saveAll")
public Object saveAll(@RequestParam(name = "Object") Object Object){
  return leavestructuredetailsrepo.saveAll(Object);
}


}