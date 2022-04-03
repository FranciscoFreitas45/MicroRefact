package org.sdrc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UtDataRepositoryController {

 private UtDataRepository utdatarepository;


@GetMapping
("/getAreaNid")
public UtAreaEn[] getAreaNid(@RequestParam(name = "areaId") String areaId,@RequestParam(name = "childLevel") Integer childLevel){
  return utdatarepository.getAreaNid(areaId,childLevel);
}


@GetMapping
("/findDataByTimePeriod")
public List<Object[]> findDataByTimePeriod(@RequestParam(name = "indicatorId") Integer indicatorId,@RequestParam(name = "timePeriodNid") Integer timePeriodNid,@RequestParam(name = "sourceNid") Integer sourceNid,@RequestParam(name = "areaNid") Integer[] areaNid){
  return utdatarepository.findDataByTimePeriod(indicatorId,timePeriodNid,sourceNid,areaNid);
}


@GetMapping
("/findTimePeriodNIdByDecadeTimePeriod")
public List<UtTimeperiod> findTimePeriodNIdByDecadeTimePeriod(@RequestParam(name = "timePeriodNid") List<String> timePeriodNid){
  return utdatarepository.findTimePeriodNIdByDecadeTimePeriod(timePeriodNid);
}


@GetMapping
("/findData")
public List<Object[]> findData(@RequestParam(name = "indicatorId") Integer indicatorId,@RequestParam(name = "areaNid") Integer areaNid){
  return utdatarepository.findData(indicatorId,areaNid);
}


@GetMapping
("/findDataForCompositeIndex")
public List<Object[]> findDataForCompositeIndex(@RequestParam(name = "iusNId") Integer iusNId,@RequestParam(name = "timePeriodNid") Integer timePeriodNid,@RequestParam(name = "areaNid") Integer[] areaNid){
  return utdatarepository.findDataForCompositeIndex(iusNId,timePeriodNid,areaNid);
}


@PutMapping
("/save")
public void save(@RequestParam(name = "utDatas") Iterable<UtData> utDatas){
utdatarepository.save(utDatas);
}


@GetMapping
("/getData")
public List<UtData> getData(@RequestParam(name = "subSector") int subSector,@RequestParam(name = "timePeriod") int timePeriod,@RequestParam(name = "indicator") int indicator,@RequestParam(name = "unit") int unit,@RequestParam(name = "subgroup") int subgroup){
  return utdatarepository.getData(subSector,timePeriod,indicator,unit,subgroup);
}


@GetMapping
("/getByTimePeriod")
public List<Object[]> getByTimePeriod(@RequestParam(name = "timeperiodId") int timeperiodId){
  return utdatarepository.getByTimePeriod(timeperiodId);
}


@GetMapping
("/findDataByTimePeriodAndArea")
public List<Object[]> findDataByTimePeriodAndArea(@RequestParam(name = "timeperiodId") Integer timeperiodId,@RequestParam(name = "areaList") List<Integer> areaList,@RequestParam(name = "iusIds") Integer[] iusIds){
  return utdatarepository.findDataByTimePeriodAndArea(timeperiodId,areaList,iusIds);
}


@GetMapping
("/getABRCCData")
public List<Object[]> getABRCCData(@RequestParam(name = "areaNames") List<String> areaNames){
  return utdatarepository.getABRCCData(areaNames);
}


}