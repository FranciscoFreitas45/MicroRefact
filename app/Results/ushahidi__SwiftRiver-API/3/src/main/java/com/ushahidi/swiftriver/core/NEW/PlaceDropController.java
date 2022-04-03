package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Place;
@RestController
@CrossOrigin
public class PlaceDropController {

@Autowired
 private PlaceDropService placedropservice;


@GetMapping
("/Drop/{id}/Place/getPlaces")
public List<Place> getPlaces(@PathVariable(name="id") long id){
return placedropservice.getPlaces(id);
}


@PutMapping
("/Drop/{id}/Place/setPlaces")
public void setPlaces(@PathVariable(name="id") long id,@RequestBody List<Place> places){
placedropservice.setPlaces(id,places);
}


}