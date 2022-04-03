package com.metservice.kanban.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WorkItemTypeCollectionController {

 private WorkItemTypeCollection workitemtypecollection;


@GetMapping
("/getRoot")
public TreeNode<WorkItemType> getRoot(){
  return workitemtypecollection.getRoot();
}


}