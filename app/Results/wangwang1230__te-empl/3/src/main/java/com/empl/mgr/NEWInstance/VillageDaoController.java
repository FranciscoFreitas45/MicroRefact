package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class VillageDaoController {

 private VillageDao villagedao;


@GetMapping
("/findVillageByTownshipId")
public List<AddressDto> findVillageByTownshipId(@RequestParam(name = "towhshipId") long towhshipId){
  return villagedao.findVillageByTownshipId(towhshipId);
}


}