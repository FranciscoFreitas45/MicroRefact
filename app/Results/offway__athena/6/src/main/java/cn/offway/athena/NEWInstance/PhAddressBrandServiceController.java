package cn.offway.athena.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PhAddressBrandServiceController {

 private PhAddressBrandService phaddressbrandservice;


@GetMapping
("/findOne")
public PhAddressBrand findOne(@RequestParam(name = "id") Long id){
  return phaddressbrandservice.findOne(id);
}


}