package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BucketCollaboratorDaoController {

 private BucketCollaboratorDao bucketcollaboratordao;


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return bucketcollaboratordao.delete(Object);
}


}