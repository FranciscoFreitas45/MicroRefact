import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class PhBrandadminServiceController {

 private PhBrandadminService phbrandadminservice;


@GetMapping
("/findBrandIdByAdminId")
public List<Long> findBrandIdByAdminId(@RequestParam(name = "adminId") Long adminId){
  return phbrandadminservice.findBrandIdByAdminId(adminId);
}


}