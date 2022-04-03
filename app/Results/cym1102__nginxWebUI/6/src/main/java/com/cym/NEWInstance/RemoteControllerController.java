package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RemoteControllerController {

 private RemoteController remotecontroller;


@PutMapping
("/fillTree")
public void fillTree(@RequestParam(name = "groups") List<Group> groups,@RequestParam(name = "treeList") List<Tree> treeList){
remotecontroller.fillTree(groups,treeList);
}


}