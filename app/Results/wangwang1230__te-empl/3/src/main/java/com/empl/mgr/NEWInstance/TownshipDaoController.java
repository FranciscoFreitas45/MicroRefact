package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TownshipDaoController {

 private TownshipDao townshipdao;


@GetMapping
("/findTownshipByCountyId")
public List<AddressDto> findTownshipByCountyId(@RequestParam(name = "countyId") long countyId){
  return townshipdao.findTownshipByCountyId(countyId);
}


}