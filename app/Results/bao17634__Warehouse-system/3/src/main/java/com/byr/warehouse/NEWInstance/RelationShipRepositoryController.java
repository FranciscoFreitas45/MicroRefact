package com.byr.warehouse.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RelationShipRepositoryController {

 private RelationShipRepository relationshiprepository;


@GetMapping
("/findRelationShipsBysupplyName")
public List<RelationShip> findRelationShipsBysupplyName(@RequestParam(name = "supplyName") String supplyName){
  return relationshiprepository.findRelationShipsBysupplyName(supplyName);
}


}