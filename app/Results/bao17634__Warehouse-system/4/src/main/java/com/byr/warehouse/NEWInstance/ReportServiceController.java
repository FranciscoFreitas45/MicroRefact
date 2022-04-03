package com.byr.warehouse.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ReportServiceController {

 private ReportService reportservice;


@GetMapping
("/generateStoreReoport")
public List<StockHUB> generateStoreReoport(@RequestParam(name = "date") Date date){
  return reportservice.generateStoreReoport(date);
}


}