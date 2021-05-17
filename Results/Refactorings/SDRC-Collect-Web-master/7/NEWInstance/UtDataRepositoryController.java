import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class UtDataRepositoryController {

 private UtDataRepository utdatarepository;


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