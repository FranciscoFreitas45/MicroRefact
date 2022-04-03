package com.byr.warehouse.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EntrepotStatusServiceController {

 private EntrepotStatusService entrepotstatusservice;


@GetMapping
("/getAllEntrepotCount")
public int getAllEntrepotCount(){
  return entrepotstatusservice.getAllEntrepotCount();
}


}