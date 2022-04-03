package org.sdrc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UtTimeperiodRepositoryController {

 private UtTimeperiodRepository uttimeperiodrepository;


@GetMapping
("/findBySource_Nid")
public List<UtTimeperiod> findBySource_Nid(@RequestParam(name = "iusNid") Integer iusNid,@RequestParam(name = "SourceNid") Integer SourceNid){
  return uttimeperiodrepository.findBySource_Nid(iusNid,SourceNid);
}


@GetMapping
("/findLatestTimePeriodNId")
public Object findLatestTimePeriodNId(@RequestParam(name = "iusNid") Integer iusNid,@RequestParam(name = "areaNid") Integer[] areaNid){
  return uttimeperiodrepository.findLatestTimePeriodNId(iusNid,areaNid);
}


@GetMapping
("/findByStartDateAndEndDateAndPeriodicity")
public UtTimeperiod findByStartDateAndEndDateAndPeriodicity(@RequestParam(name = "startDate") Date startDate,@RequestParam(name = "endDate") Date endDate,@RequestParam(name = "string") String string){
  return uttimeperiodrepository.findByStartDateAndEndDateAndPeriodicity(startDate,endDate,string);
}


@GetMapping
("/save")
public UtTimeperiod save(@RequestParam(name = "timeperiod") UtTimeperiod timeperiod){
  return uttimeperiodrepository.save(timeperiod);
}


@GetMapping
("/findByPeriodicity")
public List<UtTimeperiod> findByPeriodicity(@RequestParam(name = "string") String string){
  return uttimeperiodrepository.findByPeriodicity(string);
}


}