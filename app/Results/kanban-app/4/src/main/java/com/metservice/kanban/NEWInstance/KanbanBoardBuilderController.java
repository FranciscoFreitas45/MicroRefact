package com.metservice.kanban.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class KanbanBoardBuilderController {

 private KanbanBoardBuilder kanbanboardbuilder;

 private KanbanBoardBuilder kanbanboardbuilder;


@GetMapping
("/buildKanbanBacklog")
public KanbanBacklog buildKanbanBacklog(@RequestParam(name = "workStream") String workStream){
  return kanbanboardbuilder.buildKanbanBacklog(workStream);
}


}