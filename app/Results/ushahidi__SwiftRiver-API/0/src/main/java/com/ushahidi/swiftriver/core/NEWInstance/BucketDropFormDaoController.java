package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BucketDropFormDaoController {

 private BucketDropFormDao bucketdropformdao;


@GetMapping
("/create")
public Object create(@RequestParam(name = "Object") Object Object){
  return bucketdropformdao.create(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return bucketdropformdao.update(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return bucketdropformdao.delete(Object);
}


}