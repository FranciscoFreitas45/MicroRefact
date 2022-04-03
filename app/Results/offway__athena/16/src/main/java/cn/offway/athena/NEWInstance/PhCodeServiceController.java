package cn.offway.athena.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PhCodeServiceController {

 private PhCodeService phcodeservice;


@GetMapping
("/findOne")
public PhCode findOne(@RequestParam(name = "id") Long id){
  return phcodeservice.findOne(id);
}


@GetMapping
("/save")
public PhCode save(@RequestParam(name = "phCode") PhCode phCode){
  return phcodeservice.save(phCode);
}


}