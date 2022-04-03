package com.cg.sprint.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CityDaoController {

 private CityDao citydao;


@GetMapping
("/getCityNames")
public List<City> getCityNames(){
  return citydao.getCityNames();
}


}