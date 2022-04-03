package com.sobey.cmop.mvc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IpPoolDaoCustomController {

 private IpPoolDaoCustom ippooldaocustom;


@GetMapping
("/updateIpPoolByStatus")
public int updateIpPoolByStatus(@RequestParam(name = "status") int status){
  return ippooldaocustom.updateIpPoolByStatus(status);
}


}