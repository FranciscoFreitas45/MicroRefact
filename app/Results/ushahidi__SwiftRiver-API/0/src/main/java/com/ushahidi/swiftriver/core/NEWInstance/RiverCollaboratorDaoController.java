package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RiverCollaboratorDaoController {

 private RiverCollaboratorDao rivercollaboratordao;


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return rivercollaboratordao.delete(Object);
}


}