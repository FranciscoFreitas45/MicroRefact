import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class LeaveAuthorityRepositoryController {

 private LeaveAuthorityRepository leaveauthorityrepository;


@GetMapping
("/chkAuth")
public List<LeaveAuthority> chkAuth(@RequestParam(name = "empId") int empId){
  return leaveauthorityrepository.chkAuth(empId);
}


}