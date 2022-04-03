package com.gbcom.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ZTreeBranchController {

 private ZTreeBranch ztreebranch;

 private ZTreeBranch ztreebranch;


@PutMapping
("/addTreeNode")
public void addTreeNode(@RequestParam(name = "treeNode") Node treeNode){
ztreebranch.addTreeNode(treeNode);
}


@GetMapping
("/toJsonString")
public String toJsonString(@RequestParam(name = "hasCheckBox") boolean hasCheckBox){
  return ztreebranch.toJsonString(hasCheckBox);
}


}