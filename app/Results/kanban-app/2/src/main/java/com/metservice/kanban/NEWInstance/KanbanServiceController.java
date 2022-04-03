package com.metservice.kanban.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class KanbanServiceController {

 private KanbanService kanbanservice;


@GetMapping
("/getKanbanProject")
public KanbanProject getKanbanProject(@RequestParam(name = "projectName") String projectName){
  return kanbanservice.getKanbanProject(projectName);
}


@GetMapping
("/getHome")
public File getHome(){
  return kanbanservice.getHome();
}


}