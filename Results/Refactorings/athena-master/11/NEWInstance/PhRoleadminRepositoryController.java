import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class PhRoleadminRepositoryController {

 private PhRoleadminRepository phroleadminrepository;


@GetMapping
("/deleteByRoleIds")
public int deleteByRoleIds(@RequestParam(name = "ids") List<Long> ids){
  return phroleadminrepository.deleteByRoleIds(ids);
}


}