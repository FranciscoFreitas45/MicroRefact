package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LiveRoomRepositoryController {

 private LiveRoomRepository liveroomrepository;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return liveroomrepository.save(Object);
}


@GetMapping
("/countLiveRoomByLiveCategory")
public long countLiveRoomByLiveCategory(@RequestParam(name = "liveCategoryId") String liveCategoryId){
  return liveroomrepository.countLiveRoomByLiveCategory(liveCategoryId);
}


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return liveroomrepository.findOne(Object);
}


}