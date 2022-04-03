package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.Stadium;
@RestController
@CrossOrigin
public class StadiumMatchController {

@Autowired
 private StadiumMatchService stadiummatchservice;


@PutMapping
("/Match/{id}/Stadium/setStadiumId")
public void setStadiumId(@PathVariable(name="id") Integer stadiumIdv2,@RequestBody Stadium stadiumId){
stadiummatchservice.setStadiumId(stadiumIdv2,stadiumId);
}


@GetMapping
("/Match/{id}/Stadium/getStadiumId")
public Stadium getStadiumId(@PathVariable(name="id") Integer stadiumIdv2){
return stadiummatchservice.getStadiumId(stadiumIdv2);
}


}