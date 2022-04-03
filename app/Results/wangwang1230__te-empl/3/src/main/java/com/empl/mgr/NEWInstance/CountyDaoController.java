package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CountyDaoController {

 private CountyDao countydao;


@GetMapping
("/findCountyByCityId")
public List<AddressDto> findCountyByCityId(@RequestParam(name = "cityId") long cityId){
  return countydao.findCountyByCityId(cityId);
}


}