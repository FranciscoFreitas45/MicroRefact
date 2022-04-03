package com.project.stockexchangeappbackend.NEWInstance;
 import org.springframework.web.bind.annotation.*;
  import com.project.stockexchangeappbackend.util.StockIndexTimeProperties;
 import com.project.stockexchangeappbackend.entity.Stock;
@RestController
@CrossOrigin
public class StockIndexTimePropertiesController {

 private StockIndexTimeProperties stockindextimeproperties;


@GetMapping
("/getSystemResourcesMonitorHistory")
public Object getSystemResourcesMonitorHistory(@RequestParam(name = "Object") Object Object){
  return stockindextimeproperties.getSystemResourcesMonitorHistory();
}


@GetMapping
("/getSystemResourcesMonitorInterval")
public Object getSystemResourcesMonitorInterval(@RequestParam(name = "Object") Object Object){
  return stockindextimeproperties.getSystemResourcesMonitorInterval();
}


}