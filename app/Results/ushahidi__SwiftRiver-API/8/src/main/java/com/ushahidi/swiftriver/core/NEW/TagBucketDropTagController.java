package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Tag;
@RestController
@CrossOrigin
public class TagBucketDropTagController {

@Autowired
 private TagBucketDropTagService tagbucketdroptagservice;


@PutMapping
("/BucketDropTag/{id}/Tag/setTag")
public void setTag(@PathVariable(name="id") long idQ98E,@RequestBody Tag tag){
tagbucketdroptagservice.setTag(idQ98E,tag);
}


@GetMapping
("/BucketDropTag/{id}/Tag/getTag")
public Tag getTag(@PathVariable(name="id") long idQ98E){
return tagbucketdroptagservice.getTag(idQ98E);
}


}