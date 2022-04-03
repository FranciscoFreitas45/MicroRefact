package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.River;
@RestController
@CrossOrigin
public class RiverRuleController {

@Autowired
 private RiverRuleService riverruleservice;


@GetMapping
("/Rule/{id}/River/getRiver")
public River getRiver(@PathVariable(name="id") Long idNMZI){
return riverruleservice.getRiver(idNMZI);
}


@PutMapping
("/Rule/{id}/River/setRiver")
public void setRiver(@PathVariable(name="id") Long idNMZI,@RequestBody River river){
riverruleservice.setRiver(idNMZI,river);
}


}