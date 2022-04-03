package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PlaceDaoController {

 private PlaceDao placedao;


@GetMapping
("/findByHash")
public Place findByHash(@RequestParam(name = "hash") String hash){
  return placedao.findByHash(hash);
}


@GetMapping
("/create")
public Object create(@RequestParam(name = "Object") Object Object){
  return placedao.create(Object);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return placedao.findById(Object);
}


@PutMapping
("/getPlaces")
public void getPlaces(@RequestParam(name = "drops") List<Drop> drops){
placedao.getPlaces(drops);
}


}