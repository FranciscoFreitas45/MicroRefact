package com.metservice.kanban.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class KanbanProjectController {

 private KanbanProject kanbanproject;


@GetMapping
("/getWorkItemTree")
public WorkItemTree getWorkItemTree(){
  return kanbanproject.getWorkItemTree();
}


@GetMapping
("/getColumns")
public KanbanBoardColumnList getColumns(@RequestParam(name = "boardType") BoardIdentifier boardType){
  return kanbanproject.getColumns(boardType);
}


}