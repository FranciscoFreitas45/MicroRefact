package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.RiverDrop;
@RestController
@CrossOrigin
public class RiverDropChannelController {

@Autowired
 private RiverDropChannelService riverdropchannelservice;


@PutMapping
("/Channel/{id}/RiverDrop/setDrops")
public void setDrops(@PathVariable(name="id") long id,@RequestBody List<RiverDrop> drops){
riverdropchannelservice.setDrops(id,drops);
}


@GetMapping
("/Channel/{id}/RiverDrop/getDrops")
public List<RiverDrop> getDrops(@PathVariable(name="id") long id){
return riverdropchannelservice.getDrops(id);
}


}