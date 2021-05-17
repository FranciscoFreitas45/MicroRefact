import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class PhResourceServiceController {

 private PhResourceService phresourceservice;


@GetMapping
("/findByAdminId")
public List<PhResource> findByAdminId(@RequestParam(name = "adminId") Long adminId){
  return phresourceservice.findByAdminId(adminId);
}


}