package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CityDaoController {

 private CityDao citydao;


@GetMapping
("/findCityByProvinceId")
public List<AddressDto> findCityByProvinceId(@RequestParam(name = "provinceId") long provinceId){
  return citydao.findCityByProvinceId(provinceId);
}


}