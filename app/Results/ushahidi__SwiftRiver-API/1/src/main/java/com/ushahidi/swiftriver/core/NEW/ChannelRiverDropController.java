package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Channel;
@RestController
@CrossOrigin
public class ChannelRiverDropController {

@Autowired
 private ChannelRiverDropService channelriverdropservice;


@PutMapping
("/RiverDrop/{id}/Channel/setChannel")
public void setChannel(@PathVariable(name="id") long idQKZH,@RequestBody Channel channel){
channelriverdropservice.setChannel(idQKZH,channel);
}


@GetMapping
("/RiverDrop/{id}/Channel/getChannel")
public Channel getChannel(@PathVariable(name="id") long idQKZH){
return channelriverdropservice.getChannel(idQKZH);
}


}