package com.metservice.kanban.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WorkItemTreeController {

 private WorkItemTree workitemtree;


@GetMapping
("/getChildrenWithType")
public List<WorkItem> getChildrenWithType(@RequestParam(name = "parentId") int parentId,@RequestParam(name = "childType") WorkItemType childType,@RequestParam(name = "workStream") String workStream){
  return workitemtree.getChildrenWithType(parentId,childType,workStream);
}


}