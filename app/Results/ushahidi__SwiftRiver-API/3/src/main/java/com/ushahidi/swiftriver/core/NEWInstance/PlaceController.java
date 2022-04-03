package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PlaceController {

 private JpaPlaceDao jpaplacedao;


@PutMapping
("/setPlaceName/{id}")
public void setPlaceName(@PathVariable(name = "id") long id,@RequestParam(name = "placeName") String placeName){
 jpaplacedao.setPlaceName(id,placeName);
}


@PutMapping
("/setLatitude/{id}")
public void setLatitude(@PathVariable(name = "id") long id,@RequestParam(name = "latitude") Float latitude){
 jpaplacedao.setLatitude(id,latitude);
}


@PutMapping
("/setLongitude/{id}")
public void setLongitude(@PathVariable(name = "id") long id,@RequestParam(name = "longitude") Float longitude){
 jpaplacedao.setLongitude(id,longitude);
}


@PutMapping
("/setPlaceNameCanonical/{id}")
public void setPlaceNameCanonical(@PathVariable(name = "id") long id,@RequestParam(name = "placeNameCanonical") String placeNameCanonical){
 jpaplacedao.setPlaceNameCanonical(id,placeNameCanonical);
}


@PutMapping
("/setHash/{id}")
public void setHash(@PathVariable(name = "id") long id,@RequestParam(name = "hash") String hash){
 jpaplacedao.setHash(id,hash);
}


@PutMapping
("/setId/{id}")
public void setId(@PathVariable(name = "id") long id,@RequestParam(name = "id") long id){
 jpaplacedao.setId(id,id);
}


}