package org.sdrc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DashboardServiceController {

 private DashboardService dashboardservice;


@GetMapping
("/fetchPdfData")
public List<UtDataModel> fetchPdfData(@RequestParam(name = "indicatorId") String indicatorId,@RequestParam(name = "sourceId") String sourceId,@RequestParam(name = "areaId") String areaId,@RequestParam(name = "timePeriodId") String timePeriodId,@RequestParam(name = "childLevel") Integer childLevel){
  return dashboardservice.fetchPdfData(indicatorId,sourceId,areaId,timePeriodId,childLevel);
}


}