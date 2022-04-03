package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Place;
@RestController
@CrossOrigin
public class PlaceRiverDropPlaceController {

@Autowired
 private PlaceRiverDropPlaceService placeriverdropplaceservice;


@GetMapping
("/RiverDropPlace/{id}/Place/getPlace")
public Place getPlace(@PathVariable(name="id") long id6ANM){
return placeriverdropplaceservice.getPlace(id6ANM);
}


@PutMapping
("/RiverDropPlace/{id}/Place/setPlace")
public void setPlace(@PathVariable(name="id") long id6ANM,@RequestBody Place place){
placeriverdropplaceservice.setPlace(id6ANM,place);
}


}