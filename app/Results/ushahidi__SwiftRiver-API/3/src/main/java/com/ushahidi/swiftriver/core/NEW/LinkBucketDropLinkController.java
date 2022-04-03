package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Link;
@RestController
@CrossOrigin
public class LinkBucketDropLinkController {

@Autowired
 private LinkBucketDropLinkService linkbucketdroplinkservice;


@PutMapping
("/BucketDropLink/{id}/Link/setLink")
public void setLink(@PathVariable(name="id") long idOZP4,@RequestBody Link link){
linkbucketdroplinkservice.setLink(idOZP4,link);
}


@GetMapping
("/BucketDropLink/{id}/Link/getLink")
public Link getLink(@PathVariable(name="id") long idOZP4){
return linkbucketdroplinkservice.getLink(idOZP4);
}


}