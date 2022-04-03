package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Link;
@RestController
@CrossOrigin
public class LinkRiverDropLinkController {

@Autowired
 private LinkRiverDropLinkService linkriverdroplinkservice;


@PutMapping
("/RiverDropLink/{id}/Link/setLink")
public void setLink(@PathVariable(name="id") long idM175,@RequestBody Link link){
linkriverdroplinkservice.setLink(idM175,link);
}


@GetMapping
("/RiverDropLink/{id}/Link/getLink")
public Link getLink(@PathVariable(name="id") long idM175){
return linkriverdroplinkservice.getLink(idM175);
}


}