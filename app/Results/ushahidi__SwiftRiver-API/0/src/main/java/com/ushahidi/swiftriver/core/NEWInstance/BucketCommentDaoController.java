package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BucketCommentDaoController {

 private BucketCommentDao bucketcommentdao;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return bucketcommentdao.findById(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return bucketcommentdao.delete(Object);
}


}