package com.tech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICRLocationServiceController {

 private ICRLocationService icrlocationservice;


@PutMapping
("/add")
public void add(@RequestParam(name = "saveRecord") ChatroomLocation saveRecord){
icrlocationservice.add(saveRecord);
}


@GetMapping
("/checkIfStillInside")
public boolean checkIfStillInside(@RequestParam(name = "room_id") Long room_id,@RequestParam(name = "lng") float lng,@RequestParam(name = "lat") float lat){
  return icrlocationservice.checkIfStillInside(room_id,lng,lat);
}


@PutMapping
("/setNewMaxRange")
public void setNewMaxRange(@RequestParam(name = "room_max_range") int room_max_range,@RequestParam(name = "room_id") Long room_id){
icrlocationservice.setNewMaxRange(room_max_range,room_id);
}


@GetMapping
("/findIfNear")
public List<ChatroomLocation> findIfNear(@RequestParam(name = "lng") float lng,@RequestParam(name = "lat") float lat){
  return icrlocationservice.findIfNear(lng,lat);
}


@PutMapping
("/setNewLngLat")
public void setNewLngLat(@RequestParam(name = "lng") float lng,@RequestParam(name = "lat") float lat,@RequestParam(name = "room_id") Long room_id){
icrlocationservice.setNewLngLat(lng,lat,room_id);
}


}