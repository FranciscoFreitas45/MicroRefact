package org.sdrc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UtIndicatorUnitSubgroupRepositoryController {

 private UtIndicatorUnitSubgroupRepository utindicatorunitsubgrouprepository;


@GetMapping
("/getIUS")
public List<Object[]> getIUS(@RequestParam(name = "id") int id){
  return utindicatorunitsubgrouprepository.getIUS(id);
}


}