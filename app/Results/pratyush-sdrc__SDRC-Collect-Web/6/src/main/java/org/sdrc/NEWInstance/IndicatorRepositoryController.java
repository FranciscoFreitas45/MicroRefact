package org.sdrc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IndicatorRepositoryController {

 private IndicatorRepository indicatorrepository;


@GetMapping
("/findByIC_Type")
public List<Object[]> findByIC_Type(@RequestParam(name = "indicatorType") String indicatorType){
  return indicatorrepository.findByIC_Type(indicatorType);
}


@GetMapping
("/findByIndicator_NId")
public UtIndicatorEn findByIndicator_NId(@RequestParam(name = "indicator_NId") int indicator_NId){
  return indicatorrepository.findByIndicator_NId(indicator_NId);
}


}