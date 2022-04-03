package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersJudgementServiceController {

 private ICreepersJudgementService icreepersjudgementservice;


@GetMapping
("/findByMerName")
public List<TCreepersJudgement> findByMerName(@RequestParam(name = "merName") String merName){
  return icreepersjudgementservice.findByMerName(merName);
}


@GetMapping
("/queryJudgementList")
public Page<CreepersJudgementDTO> queryJudgementList(@RequestParam(name = "searchParams") Map<String,Object> searchParams,@RequestParam(name = "pageNumber") int pageNumber,@RequestParam(name = "pageSize") int pageSize,@RequestParam(name = "sortType") String sortType){
  return icreepersjudgementservice.queryJudgementList(searchParams,pageNumber,pageSize,sortType);
}


}