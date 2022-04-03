package cn.offway.athena.NEWInstance;
 import org.springframework.web.bind.annotation.*;
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