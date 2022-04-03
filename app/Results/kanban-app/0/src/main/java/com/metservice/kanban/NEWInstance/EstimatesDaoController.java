package com.metservice.kanban.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EstimatesDaoController {

 private EstimatesDao estimatesdao;


@GetMapping
("/loadProject")
public EstimatesProject loadProject(@RequestParam(name = "projectName") String projectName){
  return estimatesdao.loadProject(projectName);
}


}