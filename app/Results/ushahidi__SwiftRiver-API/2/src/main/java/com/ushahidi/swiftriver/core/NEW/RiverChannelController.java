package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.River;
@RestController
@CrossOrigin
public class RiverChannelController {

@Autowired
 private RiverChannelService riverchannelservice;


@GetMapping
("/Channel/{id}/River/getRiver")
public River getRiver(@PathVariable(name="id") Long idATXO){
return riverchannelservice.getRiver(idATXO);
}


@PutMapping
("/Channel/{id}/River/setRiver")
public void setRiver(@PathVariable(name="id") Long idATXO,@RequestBody River river){
riverchannelservice.setRiver(idATXO,river);
}


}