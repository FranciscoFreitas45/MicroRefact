package cn.offway.athena.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PhBrandServiceController {

 private PhBrandService phbrandservice;


@GetMapping
("/findByIds")
public List<PhBrand> findByIds(@RequestParam(name = "ids") List<Long> ids){
  return phbrandservice.findByIds(ids);
}


@GetMapping
("/findOne")
public PhBrand findOne(@RequestParam(name = "id") Long id){
  return phbrandservice.findOne(id);
}


@GetMapping
("/findAll")
public List<PhBrand> findAll(@RequestParam(name = "prefix") String prefix){
  return phbrandservice.findAll(prefix);
}


}