package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.River;
@RestController
@CrossOrigin
public class RiverRiverActivityController {

@Autowired
 private RiverRiverActivityService riverriveractivityservice;


@GetMapping
("/RiverActivity/{id}/River/getActionOnObj")
public River getActionOnObj(@PathVariable(name="id") Long id37M8){
return riverriveractivityservice.getActionOnObj(id37M8);
}


@PutMapping
("/RiverActivity/{id}/River/setActionOnObj")
public void setActionOnObj(@PathVariable(name="id") Long id37M8,@RequestBody River actionOnObj){
riverriveractivityservice.setActionOnObj(id37M8,actionOnObj);
}


}