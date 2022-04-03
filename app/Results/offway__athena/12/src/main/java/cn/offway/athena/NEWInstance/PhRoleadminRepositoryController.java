package cn.offway.athena.NEWInstance;
 import org.springframework.web.bind.annotation.*;
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