import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class UserRepoController {

 private UserRepo userrepo;


@GetMapping
("/updateIsVistStatus")
public int updateIsVistStatus(@RequestParam(name = "empId") int empId,@RequestParam(name = "password") String password){
  return userrepo.updateIsVistStatus(empId,password);
}


}