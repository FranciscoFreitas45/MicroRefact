package com.gbcom.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ZTreeNodeController {

 private ZTreeNode ztreenode;

 private ZTreeNode ztreenode;


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
ztreenode.setName(name);
}


@PutMapping
("/setIsLeaf")
public void setIsLeaf(@RequestParam(name = "isLeaf") Boolean isLeaf){
ztreenode.setIsLeaf(isLeaf);
}


@PutMapping
("/setIcon")
public void setIcon(@RequestParam(name = "icon") String icon){
ztreenode.setIcon(icon);
}


@PutMapping
("/setType")
public void setType(@RequestParam(name = "type") String type){
ztreenode.setType(type);
}


}