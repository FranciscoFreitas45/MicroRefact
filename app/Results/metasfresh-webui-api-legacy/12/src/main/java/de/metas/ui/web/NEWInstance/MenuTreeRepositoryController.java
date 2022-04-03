package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MenuTreeRepositoryController {

 private MenuTreeRepository menutreerepository;


@GetMapping
("/getUserSessionMenuTree")
public MenuTree getUserSessionMenuTree(){
  return menutreerepository.getUserSessionMenuTree();
}


@GetMapping
("/getNewRecordNodeForWindowId")
public Object getNewRecordNodeForWindowId(@RequestParam(name = "Object") Object Object){
  return menutreerepository.getNewRecordNodeForWindowId(Object);
}


@GetMapping
("/map")
public Object map(@RequestParam(name = "Object") Object Object){
  return menutreerepository.map(Object);
}


}