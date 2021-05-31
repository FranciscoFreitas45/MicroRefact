import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class PhRoleadminServiceController {

 private PhRoleadminService phroleadminservice;


@GetMapping
("/findRoleIdByAdminId")
public List<Long> findRoleIdByAdminId(@RequestParam(name = "adminId") Long adminId){
  return phroleadminservice.findRoleIdByAdminId(adminId);
}


}