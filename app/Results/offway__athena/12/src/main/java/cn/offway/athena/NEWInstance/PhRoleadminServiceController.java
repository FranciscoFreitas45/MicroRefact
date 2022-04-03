package cn.offway.athena.NEWInstance;
 import org.springframework.web.bind.annotation.*;
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