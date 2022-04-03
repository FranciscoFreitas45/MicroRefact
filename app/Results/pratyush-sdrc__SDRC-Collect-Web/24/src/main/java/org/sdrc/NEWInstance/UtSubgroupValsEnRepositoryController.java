package org.sdrc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UtSubgroupValsEnRepositoryController {

 private UtSubgroupValsEnRepository utsubgroupvalsenrepository;


@GetMapping
("/fetchIndicatorAndUnitBySectorNId")
public List<Object[]> fetchIndicatorAndUnitBySectorNId(@RequestParam(name = "SectorNid") Integer SectorNid){
  return utsubgroupvalsenrepository.fetchIndicatorAndUnitBySectorNId(SectorNid);
}


@GetMapping
("/fetchSubgroupByIndicatorAndUnit")
public List<Object[]> fetchSubgroupByIndicatorAndUnit(@RequestParam(name = "IndicatorNid") Integer IndicatorNid,@RequestParam(name = "UnitNId") Integer UnitNId){
  return utsubgroupvalsenrepository.fetchSubgroupByIndicatorAndUnit(IndicatorNid,UnitNId);
}


}