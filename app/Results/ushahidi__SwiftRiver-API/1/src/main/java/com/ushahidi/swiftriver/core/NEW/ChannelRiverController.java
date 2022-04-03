package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Channel;
@RestController
@CrossOrigin
public class ChannelRiverController {

@Autowired
 private ChannelRiverService channelriverservice;


@PutMapping
("/River/{id}/Channel/setChannels")
public void setChannels(@PathVariable(name="id") Long id,@RequestBody List<Channel> channels){
channelriverservice.setChannels(id,channels);
}


@GetMapping
("/River/{id}/Channel/getChannels")
public List<Channel> getChannels(@PathVariable(name="id") Long id){
return channelriverservice.getChannels(id);
}


}