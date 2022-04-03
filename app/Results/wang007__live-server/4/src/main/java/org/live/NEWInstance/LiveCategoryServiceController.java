package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LiveCategoryServiceController {

 private LiveCategoryService livecategoryservice;


@GetMapping
("/get")
public Object get(@RequestParam(name = "Object") Object Object){
  return livecategoryservice.get(Object);
}


@GetMapping
("/findLiveCategory4app")
public List<LiveCategoryVo> findLiveCategory4app(){
  return livecategoryservice.findLiveCategory4app();
}


}