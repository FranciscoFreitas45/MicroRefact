package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Place;
@RestController
@CrossOrigin
public class PlaceBucketDropPlaceController {

@Autowired
 private PlaceBucketDropPlaceService placebucketdropplaceservice;


@GetMapping
("/BucketDropPlace/{id}/Place/getPlace")
public Place getPlace(@PathVariable(name="id") long idEIIX){
return placebucketdropplaceservice.getPlace(idEIIX);
}


@PutMapping
("/BucketDropPlace/{id}/Place/setPlace")
public void setPlace(@PathVariable(name="id") long idEIIX,@RequestBody Place place){
placebucketdropplaceservice.setPlace(idEIIX,place);
}


}