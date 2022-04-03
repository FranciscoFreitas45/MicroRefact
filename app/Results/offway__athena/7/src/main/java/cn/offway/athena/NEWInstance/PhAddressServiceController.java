package cn.offway.athena.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PhAddressServiceController {

 private PhAddressService phaddressservice;


@GetMapping
("/findOne")
public PhAddress findOne(@RequestParam(name = "id") Long id){
  return phaddressservice.findOne(id);
}


}