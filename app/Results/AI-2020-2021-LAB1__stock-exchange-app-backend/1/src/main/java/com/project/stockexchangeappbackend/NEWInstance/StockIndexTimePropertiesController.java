package com.project.stockexchangeappbackend.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StockIndexTimePropertiesController {

 private StockIndexTimeProperties stockindextimeproperties;


@GetMapping
("/getSystemResourcesMonitorHistory")
public Object getSystemResourcesMonitorHistory(@RequestParam(name = "Object") Object Object){
  return stockindextimeproperties.getSystemResourcesMonitorHistory(Object);
}


@GetMapping
("/getSystemResourcesMonitorInterval")
public Object getSystemResourcesMonitorInterval(@RequestParam(name = "Object") Object Object){
  return stockindextimeproperties.getSystemResourcesMonitorInterval(Object);
}


}