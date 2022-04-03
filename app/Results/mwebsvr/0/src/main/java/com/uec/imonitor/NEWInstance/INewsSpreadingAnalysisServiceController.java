package com.uec.imonitor.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class INewsSpreadingAnalysisServiceController {

 private INewsSpreadingAnalysisService inewsspreadinganalysisservice;


@GetMapping
("/listNewsSpreadingAnalysisDetailByIds")
public List<NewsSpreadingAnalysisDetail> listNewsSpreadingAnalysisDetailByIds(@RequestParam(name = "idList") List<Integer> idList){
  return inewsspreadinganalysisservice.listNewsSpreadingAnalysisDetailByIds(idList);
}


}