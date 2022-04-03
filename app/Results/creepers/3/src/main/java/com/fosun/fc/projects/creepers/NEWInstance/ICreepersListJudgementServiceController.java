package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersListJudgementServiceController {

 private ICreepersListJudgementService icreeperslistjudgementservice;


@GetMapping
("/findListJudgementList")
public Page<CreepersListJudgementDTO> findListJudgementList(@RequestParam(name = "searchParams") Map<String,Object> searchParams,@RequestParam(name = "pageNumber") int pageNumber,@RequestParam(name = "pageSize") int pageSize,@RequestParam(name = "sortType") String sortType){
  return icreeperslistjudgementservice.findListJudgementList(searchParams,pageNumber,pageSize,sortType);
}


}