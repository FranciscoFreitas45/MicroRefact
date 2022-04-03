package com.byr.warehouse.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ApplyOutPutRepositoryController {

 private ApplyOutPutRepository applyoutputrepository;


@GetMapping
("/findBetweenDays")
public List<ApplyOutPut> findBetweenDays(@RequestParam(name = "startDate") Date startDate,@RequestParam(name = "endDate") Date endDate){
  return applyoutputrepository.findBetweenDays(startDate,endDate);
}


@GetMapping
("/findAllByOutCode")
public List<ApplyOutPut> findAllByOutCode(@RequestParam(name = "outCode") String outCode){
  return applyoutputrepository.findAllByOutCode(outCode);
}


}