package com.metservice.kanban.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TreeNodeController {

 private TreeNode treenode;

 private TreeNode treenode;


@GetMapping
("/hasChildren")
public boolean hasChildren(){
  return treenode.hasChildren();
}


}