package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.River;
@RestController
@CrossOrigin
public class RiverAccountController {

@Autowired
 private RiverAccountService riveraccountservice;


@GetMapping
("/Account/{id}/River/getRivers")
public List<River> getRivers(@PathVariable(name="id") long id){
return riveraccountservice.getRivers(id);
}


@PutMapping
("/Account/{id}/River/setFollowingRivers")
public void setFollowingRivers(@PathVariable(name="id") long id,@RequestBody List<River> followingRivers){
riveraccountservice.setFollowingRivers(id,followingRivers);
}


@PutMapping
("/Account/{id}/River/setCollaboratingRivers")
public void setCollaboratingRivers(@PathVariable(name="id") long id,@RequestBody List<River> collaboratingRivers){
riveraccountservice.setCollaboratingRivers(id,collaboratingRivers);
}


@PutMapping
("/Account/{id}/River/setRivers")
public void setRivers(@PathVariable(name="id") long id,@RequestBody List<River> rivers){
riveraccountservice.setRivers(id,rivers);
}


@GetMapping
("/Account/{id}/River/getCollaboratingRivers")
public List<River> getCollaboratingRivers(@PathVariable(name="id") long id){
return riveraccountservice.getCollaboratingRivers(id);
}


@GetMapping
("/Account/{id}/River/getFollowingRivers")
public List<River> getFollowingRivers(@PathVariable(name="id") long id){
return riveraccountservice.getFollowingRivers(id);
}


}