package com.metservice.kanban.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class KanbanCommentsFileController {

 private KanbanCommentsFile kanbancommentsfile;


@PutMapping
("/readAllComments")
public void readAllComments(){
kanbancommentsfile.readAllComments();
}


@GetMapping
("/getCommentsFor")
public List<WorkItemComment> getCommentsFor(@RequestParam(name = "id") int id){
  return kanbancommentsfile.getCommentsFor(id);
}


@PutMapping
("/writeAllComments")
public void writeAllComments(@RequestParam(name = "workItems") WorkItemTree workItems){
kanbancommentsfile.writeAllComments(workItems);
}


}